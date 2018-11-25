package com.mezmeraiz.anonym.ui.activities

import android.app.Activity
import android.content.Intent
import android.support.transition.Transition
import android.support.transition.TransitionListenerAdapter
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import com.mezmeraiz.anonym.R
import com.mezmeraiz.anonym.databinding.ActivityPostInfoBinding
import com.mezmeraiz.anonym.ui.common.DataBindingActivity
import com.mezmeraiz.anonym.viewmodel.PostInfoViewModel
import kotlinx.android.synthetic.main.activity_post_info.*
import android.view.ViewAnimationUtils
import android.animation.Animator
import android.os.Handler
import android.os.Looper


class PostInfoActivity : DataBindingActivity<ActivityPostInfoBinding, PostInfoViewModel>(){

    var loaded = false
    var closed = false

    override fun getLayoutId(): Int {
        return R.layout.activity_post_info
    }

    override fun onInit() {
        supportPostponeEnterTransition()
        var postId = intent.getIntExtra("postId", 0)
        viewModel = PostInfoViewModel(postId)
        binding.viewModel = viewModel
        binding.context = this
    }

    override fun onBackPressed() {
        if (!closed){
            hideCardBackground()
        }else{
            super.onBackPressed()
        }

    }

    var listener = object : android.transition.Transition.TransitionListener{
        override fun onTransitionEnd(transition: android.transition.Transition?) {
            if (!loaded){
                Handler(Looper.getMainLooper())
                        .postDelayed({showCardBackground()},100)
                loaded = true
            }
        }

        override fun onTransitionResume(transition: android.transition.Transition?) {
        }

        override fun onTransitionPause(transition: android.transition.Transition?) {
        }

        override fun onTransitionCancel(transition: android.transition.Transition?) {
        }

        override fun onTransitionStart(transition: android.transition.Transition?) {
        }

    }

    private fun showCardBackground(){
        val cx = card_background.measuredWidth / 2
        val cy = card_background.measuredHeight / 2
        val finalRadius = Math.max(card_background.width, card_background.height) / 2
        val animator = ViewAnimationUtils.createCircularReveal(card_background,
                cx, cy, 0f, finalRadius.toFloat())
        card_background.visibility = View.VISIBLE
        animator.start()
    }

    private fun hideCardBackground(){
        val cx = card_background.measuredWidth / 2
        val cy = card_background.measuredHeight / 2
        val finalRadius = Math.max(card_background.width, card_background.height) / 2
        val anim = ViewAnimationUtils.createCircularReveal(card_background, cx, cy,
                finalRadius.toFloat(), 0f)
        anim.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                card_background.visibility = View.INVISIBLE
                closed = true
                this@PostInfoActivity.onBackPressed()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        card_background.setVisibility(View.VISIBLE)
        anim.start()
    }

    override fun onBinding() {
        super.onBinding()
        val sharedElementEnterTransition = window.sharedElementEnterTransition
        sharedElementEnterTransition.addListener(listener)
        viewModel.onImageReady.subscribe{
            supportStartPostponedEnterTransition()
        }
    }


    companion object {
        fun start(activity: Activity, postId: Int, options: ActivityOptionsCompat){
            val intent = Intent(activity, PostInfoActivity::class.java)
            intent.putExtra("postId", postId)
            activity.startActivity(intent, options.toBundle())
        }
    }

}
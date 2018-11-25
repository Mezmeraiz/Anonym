package com.mezmeraiz.anonym.ui.fragments

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.mezmeraiz.anonym.R
import com.mezmeraiz.anonym.common.DataBindingFragment
import com.mezmeraiz.anonym.databinding.FragmentPostBinding
import com.mezmeraiz.anonym.model.Post
import com.mezmeraiz.anonym.model.common.ItemClickValue
import com.mezmeraiz.anonym.ui.activities.PostInfoActivity
import com.mezmeraiz.anonym.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : DataBindingFragment<FragmentPostBinding, PostViewModel>() {

    override fun onInit(savedInstanceState: Bundle?) {
        viewModel = PostViewModel(arguments?.getInt("type") ?: 1)
        binding.viewModel = viewModel
        binding.context = this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_post
    }

    override fun onBinding(binding: ViewDataBinding) {
        super.onBinding(binding)
        viewModel.onClickPost.subscribe{onClickPost(it)}
    }

    private fun onClickPost(itemClickValue: ItemClickValue<Post>){
        if (activity == null) return
        val view = recyclerView.manager?.findViewByPosition(itemClickValue.position)

        val pair1 = Pair.create(view?.findViewById<View>(R.id.image), "image")
        val pair2 = Pair.create(view?.findViewById<View>(R.id.like_container), "like_container")
        val pair3 = Pair.create(view?.findViewById<View>(R.id.message_container), "message_container")
        val pair4 = Pair.create(view?.findViewById<View>(R.id.view_container), "view_container")

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!,
                pair1, pair2, pair3, pair4)
        PostInfoActivity.start(activity!!, itemClickValue.model.id ?: 0, options)
    }


    companion object {

        fun getInstance(type: Int): PostFragment{
            val fragment = PostFragment()
            val args = Bundle()
            args.putInt("type", type)
            fragment.arguments = args
            return fragment
        }

    }


}
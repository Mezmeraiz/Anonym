package com.mezmeraiz.anonym.ui.common

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mezmeraiz.anonym.common.RootViewModel
import com.mezmeraiz.anonym.toast

abstract class DataBindingActivity<B: ViewDataBinding, VM: RootViewModel> : AppCompatActivity(){

    lateinit protected var viewModel: VM
    lateinit protected var binding: B
    private var isStarted = true

    abstract fun getLayoutId(): Int

    abstract fun onInit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        onInit()
        viewModel.error.subscribe{toast(it)}
        viewModel.onCreate(this)
        onBinding()
    }

    open fun onBinding(){

    }

    open fun onLoad(){
        viewModel.onLoad()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onStop()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
        if(isStarted){
            onLoad()
            isStarted = false
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
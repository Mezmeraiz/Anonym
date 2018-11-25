package com.mezmeraiz.anonym.common

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mezmeraiz.anonym.toast

abstract class DataBindingFragment <B: ViewDataBinding, VM: RootViewModel> : Fragment() {

    lateinit protected var viewModel: VM

    lateinit protected var binding: B

    private var isStarted = true

    var isViewCreated = false

    abstract fun onInit(savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        onInit(savedInstanceState)
        viewModel.error.subscribe{ if (context != null){ context!!.toast(it) }}
        viewModel.onCreate(context!!)
        onBinding(this.binding)
        isViewCreated = true
        return binding.root
    }

    open fun onBinding(binding: ViewDataBinding){

    }

    open fun onLoad(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onStop()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
        if(isStarted){
            onLoad()
            isStarted = false
        }
    }

}
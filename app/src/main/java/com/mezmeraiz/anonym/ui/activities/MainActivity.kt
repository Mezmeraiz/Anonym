package com.mezmeraiz.anonym.ui.activities

import com.mezmeraiz.anonym.R
import com.mezmeraiz.anonym.databinding.ActivityMainBinding
import com.mezmeraiz.anonym.ui.common.DataBindingActivity
import com.mezmeraiz.anonym.viewmodel.MainViewModel

class MainActivity : DataBindingActivity<ActivityMainBinding, MainViewModel>(){

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onInit() {
        viewModel = MainViewModel()
        binding.viewModel = viewModel
        binding.context = this
    }

}
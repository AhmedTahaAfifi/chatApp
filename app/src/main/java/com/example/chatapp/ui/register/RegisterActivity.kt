package com.example.chatapp.ui.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityRegisterBinding


class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this)[RegisterViewModel::class.java]
    }

}
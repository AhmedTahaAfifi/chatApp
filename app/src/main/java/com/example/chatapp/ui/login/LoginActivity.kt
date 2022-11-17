package com.example.chatapp.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

//    fun onClick(view: View){
//        val intent = Intent(this, RegisterActivity::class.java)
//        startActivity(intent)
//    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

}
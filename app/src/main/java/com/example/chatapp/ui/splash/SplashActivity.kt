package com.example.chatapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivitySplashBinding
import com.example.chatapp.ui.home.HomeActivity
import com.example.chatapp.ui.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        Handler(Looper.getMainLooper()).postDelayed({
                  viewModel.checkLoggedInUser()
        },2000)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initViewModel(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun openLoginActivity() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    override fun openHomeActivity() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}
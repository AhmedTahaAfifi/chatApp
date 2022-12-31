package com.example.chatapp.ui.splash

import com.example.chatapp.DataUtils
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.database.logIn
import com.example.chatapp.model.AppUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashViewModel: BaseViewModel<Navigator>() {


    fun checkLoggedInUser(){
        val firebaseUser = Firebase.auth.currentUser
        if(firebaseUser==null){
            navigator?.openLoginActivity()
        }else {
            logIn(firebaseUser.uid,
                onSuccessListener = {
                    val user = it.toObject(AppUser::class.java)
                    DataUtils.user = user
                },{
                    navigator?.openLoginActivity()
                })
            navigator?.openHomeActivity()

        }
    }

}
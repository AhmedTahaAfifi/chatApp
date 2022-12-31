package com.example.chatapp.ui.home

import androidx.databinding.ObservableField
import com.example.chatapp.base.BaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeViewModel :BaseViewModel<Navigator>() {

    val progressBar = ObservableField<Boolean>()

    fun logOut(){
        Firebase.auth.signOut()
        navigator?.openLoginActivity()
    }

    fun createRoom() {
        navigator?.openAddRoom()
    }

}
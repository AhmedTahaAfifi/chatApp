package com.example.chatapp.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chatapp.base.BaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginViewModel : BaseViewModel() {
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()

    fun getAccount() {
        if (validate()) {
            getAccountFromFirebase()
        }
    }
    private fun validate():Boolean {
        var valid = true
        if (email.get().isNullOrBlank()) {
            emailError.set("please enter your E-mail Adress")
            valid = false
        }else {
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()) {
            passwordError.set("please enter your password")
            valid = false
        }else {
            passwordError.set(null)
        }
        return valid
    }
    private val auth = Firebase.auth
    private fun getAccountFromFirebase() {
        showLoadeing.value = true
        auth.signInWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener { task->
                showLoadeing.value = false
                if (task.isSuccessful) {
                    //login in success
                    messageLiveData.value = "login Successful"
                    Log.e("firebase","signInWithEmail:success")
                }else {
                    messageLiveData.value = task.exception?.localizedMessage
                    Log.e("firebase","signInWithEmail:success"
                    +task.exception?.localizedMessage)
                }
            }
    }

}


package com.example.chatapp.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chatapp.DataUtils
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.database.logIn
import com.example.chatapp.model.AppUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginViewModel : BaseViewModel<Navigator>() {
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()
    var progressBar = ObservableField<Boolean>()

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
        //showLoadeing.value = true
        progressBar.set(true)
        auth.signInWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener { task->
                //showLoadeing.value = false
                progressBar.set(false)
                if (task.isSuccessful) {
                    //login in success
//                    navigator?.openHomeScreen()
                    getUserFromFirestore(task.result.user?.uid)
                }else {
                    messageLiveData.value = task.exception?.localizedMessage
                    Log.e("firebase","signInWithEmail:success"
                    +task.exception?.localizedMessage)
                }
            }
    }

    private fun getUserFromFirestore(uid: String?) {
        //showLoadeing.value = true
        progressBar.set(true)
        logIn(uid!!, {
            //showLoadeing.value = false
            progressBar.set(false)
            val user = it.toObject(AppUser::class.java)
            if (user==null){
                messageLiveData.value = "Invalid email or password"
                return@logIn
            }
            DataUtils.user = user
            navigator?.openHomeScreen()
        }, {
            //showLoadeing.value = false
            progressBar.set(false)
            messageLiveData.value = it.localizedMessage
        })
    }

    fun createAccount(){
        navigator?.openRegisterScreen()
    }

}


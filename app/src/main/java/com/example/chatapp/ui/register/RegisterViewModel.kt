package com.example.chatapp.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.base.BaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class RegisterViewModel: BaseViewModel() {
    val firstName = ObservableField<String>()
    val firstNameError = ObservableField<String>()
    val lastName = ObservableField<String>()
    val lastNameError = ObservableField<String>()
    val userName = ObservableField<String>()
    val userNameError = ObservableField<String>()
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()



    val auth = Firebase.auth
    fun createAccount(){
        if (validate()){
            addAccountToFirebase()
        }
    }
    fun validate():Boolean{
        var valid = true
        if (firstName.get().isNullOrBlank()){
            firstNameError.set("please enter first name")
            valid = false
        }else{
            firstNameError.set(null)
        }
        if (lastName.get().isNullOrBlank()){
            lastNameError.set("please enter last name")
            valid = false
        }else{
            lastNameError.set(null)
        }
        if (userName.get().isNullOrBlank()){
            userNameError.set("please enter user name")
            valid = false
        }else{
            userNameError.set(null)
        }
        if (email.get().isNullOrBlank()){
            emailError.set("please enter E-mail Adress")
            valid = false
        }else{
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()){
            passwordError.set("please enter password")
            valid = false
        }else{
            passwordError.set(null)
        }
        return valid
    }
    fun addAccountToFirebase(){
        showLoadeing.value = true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task->
                showLoadeing.value = false
                if (task.isSuccessful){
                    //sign in success
                    messageLiveData.value = "Successful"
                    Log.e("firebase","createUserWithEmail:Success")
                }else{
                    messageLiveData.value = task.exception?.localizedMessage
                    Log.e("firebase","createUserWithEmail:Failure"
                    +task.exception?.localizedMessage)
                }
            }
    }
}
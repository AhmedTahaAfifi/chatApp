package com.example.chatapp.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.database.addUserToFirestore
import com.example.chatapp.model.AppUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel: BaseViewModel<Navigator>() {
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
    var progressBar = ObservableField<Boolean>()



    private val auth = Firebase.auth
    fun createAccount(){
        if (validate()){
            addAccountToFirebase()
        }
    }
    private fun validate():Boolean{
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
    private fun addAccountToFirebase(){
        //showLoadeing.value = true
        progressBar.set(true)
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task->
                //showLoadeing.value = false
                progressBar.set(false)
                if (task.isSuccessful){
                    //sign in success
                createFirestoreUser(task.result.user?.uid)

                }else{
                    messageLiveData.value = task.exception?.localizedMessage
                    Log.e("firebase","createUserWithEmail:Failure"
                    +task.exception?.localizedMessage)
                }
            }
    }

    private fun createFirestoreUser(uid: String?) {
        //showLoadeing.value = true
        progressBar.set(true)
        val user = AppUser(
            id = uid,
            firstName = firstName.get(),
            lastName = lastName.get(),
            userName = userName.get(),
            email = email.get()
        )
        addUserToFirestore(user,
            {
                //showLoadeing.value = false
                progressBar.set(false)
                navigator?.openHomeScreen()
        }, {
                //showLoadeing.value = false
                progressBar.set(false)
                messageLiveData.value = it.localizedMessage
        })
    }

}
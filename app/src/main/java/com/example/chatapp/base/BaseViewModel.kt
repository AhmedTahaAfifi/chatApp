package com.example.chatapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val showLoadeing = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
}
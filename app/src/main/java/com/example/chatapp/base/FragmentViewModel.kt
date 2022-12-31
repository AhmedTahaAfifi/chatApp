package com.example.chatapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class FragmentViewModel :ViewModel(){
    val messageLiveData = MutableLiveData<String>()
}
package com.example.chatapp

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun setError(textInputLayout: TextInputLayout,error: String?){
    textInputLayout.error = error
}

@BindingAdapter("app:visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if(visible) {
        View.INVISIBLE
    }else {
        View.VISIBLE
    }
}





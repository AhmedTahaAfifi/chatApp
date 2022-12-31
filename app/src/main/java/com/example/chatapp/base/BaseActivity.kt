package com.example.chatapp.base

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB:ViewDataBinding,VM:BaseViewModel<*>> :AppCompatActivity() {

    lateinit var viewModel:VM
    lateinit var viewDataBinding:DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = initViewModel()
        subscribetoLiveData()
    }

    private fun subscribetoLiveData() {
        viewModel.messageLiveData.observe(this) { message ->
            showDialog(message,"ok")
        }
        viewModel.toastLiveData.observe(this){toast->
            makeToast(toast)
        }
    }
    private var alertDialog:AlertDialog?=null
    private fun showDialog(message:String,
                           posActionName:String?=null,
                           posAction:DialogInterface.OnClickListener?=null,
                           negActionName:String?=null,
                           negAction:DialogInterface.OnClickListener?=null,
                           cancelable:Boolean=true
                   ){
        val defAction = DialogInterface.OnClickListener { dialog, p1 -> dialog?.dismiss() }
        val builder = AlertDialog.Builder(this).setMessage(message)
        if (posActionName!=null){
            builder.setPositiveButton(posActionName,posAction ?: defAction)
        }
        if (negActionName!=null){
            builder.setPositiveButton(negActionName,negAction ?: defAction)
        }
        builder.setCancelable(cancelable)

        alertDialog = builder.show()
    }
    fun hideAlertDialog() {
        alertDialog?.dismiss()
        alertDialog = null
    }

    fun makeToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }


    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM

}
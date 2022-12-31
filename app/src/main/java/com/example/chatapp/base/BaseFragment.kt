package com.example.chatapp.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB: ViewDataBinding,VM:FragmentViewModel>:Fragment() {
    lateinit var viewModel:VM
    lateinit var viewDataBinding:DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        return viewDataBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribetoLiveData()
    }

    private fun subscribetoLiveData() {
        viewModel.messageLiveData.observe(viewLifecycleOwner) { message ->
            showDialog(message,"ok")
        }
    }
    private var alertDialog: AlertDialog?=null
    private fun showDialog(message:String,
                           posActionName:String?=null,
                           posAction: DialogInterface.OnClickListener?=null,
                           negActionName:String?=null,
                           negAction: DialogInterface.OnClickListener?=null,
                           cancelable:Boolean=true
    ){
        val defAction = DialogInterface.OnClickListener { dialog, p1 -> dialog?.dismiss() }
        val builder = AlertDialog.Builder(requireContext()).setMessage(message)
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
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM
}


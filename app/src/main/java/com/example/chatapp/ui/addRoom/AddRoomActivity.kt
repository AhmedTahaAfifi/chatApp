package com.example.chatapp.ui.addRoom


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityAddRoomBinding

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,AddRoomViewModel>(), Navigator{
    private lateinit var adapter: CategoriesSpinnerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        adapter = CategoriesSpinnerAdapter(viewModel.categoryList)
        viewDataBinding.spinner.adapter = adapter
        viewDataBinding.spinner.onItemSelectedListener = object :OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.selectedCategory = viewModel.categoryList[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        viewModel.roomAdded.observe(this) {
            if (it) {
                makeToast("Room Added SUCCESSFULLY")
                finish()
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
        return ViewModelProvider(this)[AddRoomViewModel::class.java]
    }
}
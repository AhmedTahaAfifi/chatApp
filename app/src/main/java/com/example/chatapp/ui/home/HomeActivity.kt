package com.example.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.Constants
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.database.getRoom
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.model.Room
import com.example.chatapp.ui.addRoom.AddRoomActivity
import com.example.chatapp.ui.chat.ChatActivity
import com.example.chatapp.ui.login.LoginActivity

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(), Navigator{
    val adapter = RoomAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        viewDataBinding.recyclerView.adapter = adapter
        initRoomView()

    }

    fun initRoomView(){
        adapter.onItemClickListener = object :RoomAdapter.OnItemClickListener{
            override fun onItemClick(pos: Int, room: Room) {
                startChatActivity(room)
            }

        }
    }
    fun startChatActivity(room: Room) {
        val intent = Intent(this,ChatActivity::class.java)
        intent.putExtra(Constants.EXTRA_ROOM,room)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.progressBar.set(true)
        getRoom(
            onSuccessListener = {
                viewModel.progressBar.set(false)
                val room = it.toObjects(Room::class.java)
                adapter.changeData(room)
            },{
                viewModel.progressBar.set(false)
                makeToast("can't Create rooms")
            }

        )
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun openAddRoom() {
        val intent = Intent(this,AddRoomActivity::class.java)
        startActivity(intent)
    }

    override fun openLoginActivity() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}
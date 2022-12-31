package com.example.chatapp.ui.chat

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.Constants
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.database.getMessagesRef
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.model.Message
import com.example.chatapp.model.Room
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class ChatActivity : BaseActivity<ActivityChatBinding,ChatViewModel>(), Navigator{
    lateinit var room: Room
    val adapter = MessagesAdapter()
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        room = intent.getParcelableExtra(Constants.EXTRA_ROOM)!!
        viewModel.room = room
        layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        viewDataBinding.recyclerView.layoutManager = layoutManager
        viewDataBinding.recyclerView.adapter = adapter
        listenMessageUpdate()
    }

    fun listenMessageUpdate(){
        getMessagesRef(room.id!!).orderBy("dateTime",Query.Direction.ASCENDING)
            .addSnapshotListener{snapshots,exception->
                if (exception!=null){
                    makeToast("can't retrieve messages")
                }else{
                    val newMessageList = mutableListOf<Message>()
                    for(dc in  snapshots!!.documentChanges) {
                        when (dc.type) {
                            DocumentChange.Type.ADDED ->{val message = dc.document.toObject(Message::class.java)
                            newMessageList.add(message)
                            }

                            else -> {}
                        }
                    }
                    adapter.addMessages(newMessageList)
                    viewDataBinding.recyclerView.smoothScrollToPosition(adapter.items.size-1)
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this)[ChatViewModel::class.java]
    }
}
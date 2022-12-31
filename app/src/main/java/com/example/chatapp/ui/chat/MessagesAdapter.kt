package com.example.chatapp.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatapp.DataUtils
import com.example.chatapp.R
import com.example.chatapp.databinding.ItemMessageRecievedBinding
import com.example.chatapp.databinding.ItemMessageSendBinding
import com.example.chatapp.model.Message

class MessagesAdapter():RecyclerView.Adapter<ViewHolder>() {

    var items = mutableListOf <Message?>()

    class SendMessageViewHolder(val viewDataBinding: ItemMessageSendBinding)
        :ViewHolder(viewDataBinding.root){
        fun bind(message: Message){
            viewDataBinding.item = message
            viewDataBinding.invalidateAll()
        }
    }

    class RecievedMessagesViewHolder(val viewDataBinding: ItemMessageRecievedBinding)
        :ViewHolder(viewDataBinding.root){
        fun bind(message: Message){
            viewDataBinding.item = message
            viewDataBinding.invalidateAll()
        }
    }

    val RECIEVED = 1
    val SEND = 2
    override fun getItemViewType(position: Int): Int {
        val message = items[position]
        if(message?.senderId==DataUtils.user?.id){
            return SEND
        }
        return RECIEVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (RECIEVED==viewType){
            val itemBinding:ItemMessageRecievedBinding=DataBindingUtil
                .inflate(LayoutInflater.from(parent.context),R.layout.item_message_recieved,parent,false)
            return RecievedMessagesViewHolder(itemBinding)
        }
        val itemBinding:ItemMessageSendBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context),R.layout.item_message_send,parent,false)
        return SendMessageViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is RecievedMessagesViewHolder){
            holder.bind(items[position]!!)
        }else if(holder is SendMessageViewHolder){
            holder.bind(items[position]!!)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun addMessages(newMessageList: MutableList<Message>) {
        items.addAll(newMessageList)
        notifyItemRangeInserted(items.size+1,newMessageList.size)
    }

}
package com.example.chatapp.ui.chat

import androidx.databinding.ObservableField
import com.example.chatapp.DataUtils
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.database.addMessage
import com.example.chatapp.model.Message
import com.example.chatapp.model.Room
import java.util.Date

class ChatViewModel:BaseViewModel<Navigator>() {


    val messageField = ObservableField<String>()

    var room:Room?=null
    fun sendMessage(){
        if(!validate()){
            return
        }
        addMessageToFirestore()
    }

    fun validate():Boolean {
        var valid = true
        if (messageField.get().isNullOrBlank()){
            valid = false
        }
        return valid
    }
    private fun addMessageToFirestore(){
        val message = Message(
            messagecontent = messageField.get(),
            roomId = room?.id,
            senderId = DataUtils.user?.id,
            senderName = DataUtils.user?.userName,
            dateTime = Date().time,
        )
        addMessage(message,
            onSuccessListener = {
                messageField.set("")
            },{
                toastLiveData.value = "Failed to send message,Try again later"
            })
    }

}
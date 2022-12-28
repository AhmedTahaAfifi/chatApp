package com.example.chatapp.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.database.addRoom
import com.example.chatapp.model.Category
import com.example.chatapp.model.Room

class AddRoomViewModel : BaseViewModel<Navigator>() {

    val roomName = ObservableField<String>()
    val roomNameError = ObservableField<String>()
    val roomDescreption = ObservableField<String>()
    val roomDescreptionError = ObservableField<String>()
    val categoryList = Category.getCategoryList()
    val progressBar = ObservableField<Boolean>()
    var selectedCategory =categoryList[0]
    val roomAdded = MutableLiveData<Boolean>()

    fun createRoom() {
        if(validate()) {
            createRoomToFirestore()
        }
    }

    private fun validate():Boolean {
        var valid = true
        if (roomName.get().isNullOrBlank()) {
            roomNameError.set("Please enter Room Name")
            valid = false
        }else {
            roomNameError.set(null)
        }
        if (roomDescreption.get().isNullOrBlank()) {
            roomDescreptionError.set("please enter Room Descreption")
        }else {
            roomDescreptionError.set(null)
        }
        return valid
    }

    private fun createRoomToFirestore() {
        progressBar.set(true)
        val room =Room(
            roomName = roomName.get(),
            roomDesc = roomDescreption.get(),
            categoryId = selectedCategory.id
        )
        addRoom(room, onSuccessListener = {
            progressBar.set(false)
            roomAdded.value=true
        },{
            progressBar.set(false)
            messageLiveData.value = it.localizedMessage
        })

    }

}
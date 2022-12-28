package com.example.chatapp.model

data class Room(
    var id:String?=null,
    var roomName:String?=null,
    var roomDesc:String?=null,
    val categoryId:String?=null
){
    companion object{
        const val COLLECTION_NAME = "Room"
    }
}

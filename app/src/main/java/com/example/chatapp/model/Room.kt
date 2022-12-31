package com.example.chatapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var id:String?=null,
    var roomName:String?=null,
    var roomDesc:String?=null,
    val categoryId:String?=null
):Parcelable{
    fun getCategoryImageId(): Int? {
        return Category.fromId(categoryId?:Category.ISLAM).imageId
    }
    companion object{
        const val COLLECTION_NAME = "Room"
    }
}

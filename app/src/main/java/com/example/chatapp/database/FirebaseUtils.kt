package com.example.chatapp.database

import com.example.chatapp.model.AppUser
import com.example.chatapp.model.Message
import com.example.chatapp.model.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun addUserToFirestore(user: AppUser, onSuccessListener: OnSuccessListener<Void>,
                       onFailureListener: OnFailureListener) {
    val userCollection = getCollection(AppUser.COLLECTION_NAME)
    val userDoc = userCollection.document(user.id!!)
    userDoc.set(user)
        .addOnFailureListener(onFailureListener)
        .addOnSuccessListener(onSuccessListener)


}

fun logIn(uid:String,onSuccessListener: OnSuccessListener<DocumentSnapshot>,
          onFailureListener: OnFailureListener){
    val collectionRef = getCollection(AppUser.COLLECTION_NAME)
    collectionRef.document(uid).get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getCollection(collectionName:String):CollectionReference{
    val db = Firebase.firestore
    return db.collection(collectionName)
}

fun addRoom(room:Room,onSuccessListener: OnSuccessListener<Void>,
            onFailureListener: OnFailureListener){
    val roomCollection = getCollection(Room.COLLECTION_NAME)
    val roomDoc = roomCollection.document()
    room.id = roomDoc.id
    roomDoc.set(room)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getRoom(onSuccessListener: OnSuccessListener<QuerySnapshot>,onFailureListener: OnFailureListener){
    val roomRef = getCollection(Room.COLLECTION_NAME)
    roomRef.get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getMessagesRef(roomId: String):CollectionReference{
    val collectionRef = getCollection(Room.COLLECTION_NAME)
    val roomRef = collectionRef.document(roomId)
    return roomRef.collection(Message.COLLECTION_NAME)
}

fun addMessage(message:Message,onSuccessListener: OnSuccessListener<Void>,
               onFailureListener: OnFailureListener
               ){
    val messagesRef = getMessagesRef(message.roomId!!)
    val messageDoc = messagesRef.document()
    message.id = messageDoc.id
    messageDoc.set(message)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}



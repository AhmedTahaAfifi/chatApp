package com.example.chatapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.databinding.ItemRoomBinding
import com.example.chatapp.model.Room

class RoomAdapter(var item:List<Room?>?):RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    class ViewHolder(val viewDataBinding: ItemRoomBinding):RecyclerView.ViewHolder(viewDataBinding.root){
        fun bind(room:Room?) {
            viewDataBinding.item = room
            viewDataBinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding:ItemRoomBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_room,
            parent,false
        )
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item!![position])
        onItemClickListener?.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position,item!![position]!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return item?.size ?: 0
    }

    fun changeData(room: List<Room>) {
        item = room
        notifyDataSetChanged()
    }

    var onItemClickListener:OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(pos:Int,room: Room)
    }

}
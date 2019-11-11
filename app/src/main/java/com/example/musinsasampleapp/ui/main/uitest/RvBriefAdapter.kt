package com.example.musinsasampleapp.ui.main.uitest

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseViewHolder
import com.example.musinsasampleapp.databinding.ItemMeetingRoomBriefBinding

class RvBriefAdapter : RecyclerView.Adapter<RvBriefAdapter.BriefViewHolder>() {

    private val data = mutableListOf<String>()

    fun setData(newData: List<String>?) {
        if (newData != null) {
            data.clear()
            data.addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BriefViewHolder =
        BriefViewHolder(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BriefViewHolder, position: Int) =
        holder.bind(data[position])

    class BriefViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemMeetingRoomBriefBinding>(R.layout.item_meeting_room_brief, parent) {

        fun bind(item: String) {
            binding.name = item
        }
    }
}
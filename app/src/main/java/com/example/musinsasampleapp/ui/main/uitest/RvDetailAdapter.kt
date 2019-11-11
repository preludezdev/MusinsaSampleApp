package com.example.musinsasampleapp.ui.main.uitest

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseViewHolder
import com.example.musinsasampleapp.data.vo.MeetingRoomInfo
import com.example.musinsasampleapp.databinding.ItemMeetingRoomDetailBinding

class RvDetailAdapter : RecyclerView.Adapter<RvDetailAdapter.DetailViewHolder>() {

    private val data = mutableListOf<MeetingRoomInfo>()

    fun setData(newData: List<MeetingRoomInfo>?) {
        if (newData != null) {
            data.clear()
            data.addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
        DetailViewHolder(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) =
        holder.bind(data[position])

    class DetailViewHolder(val parent: ViewGroup) :
        BaseViewHolder<ItemMeetingRoomDetailBinding>(R.layout.item_meeting_room_detail, parent) {

        fun bind(item: MeetingRoomInfo) {
            binding.item = item

            with(binding) {
                current11.visibility = View.VISIBLE //현재 시간은 11시로 임의 설정

                //예약시간 파란색으로 마킹
                item.reservations.map {
                    val startTIme = it.startTime.toInt() / 100
                    val endTime = it.endTime.toInt() / 100

                    val length = endTime - startTIme

                    for (i in 0 until length) {
                        drawReservedTime(startTIme + i)
                    }

                    when (it.startTime) {
                        "9" -> timeline09.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        "10" -> timeline10.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        "11" -> timeline11.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        "12" -> timeline12.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        "13" -> timeline13.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        "14" -> timeline14.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        "15" -> timeline15.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        "16" -> timeline16.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        "17" -> timeline17.setBackgroundColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.deep_blue_sky
                            )
                        )
                        else -> Unit
                    }
                }

            }
        }

        private fun drawReservedTime(time: Int) {
            with(binding) {
                when (time) {
                    9 -> timeline09.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    10 -> timeline10.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    11 -> timeline11.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    12 -> timeline12.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    13 -> timeline13.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    14 -> timeline14.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    15 -> timeline15.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    16 -> timeline16.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    17 -> timeline17.setBackgroundColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.deep_blue_sky
                        )
                    )
                    else -> Unit
                }
            }
        }
    }

}

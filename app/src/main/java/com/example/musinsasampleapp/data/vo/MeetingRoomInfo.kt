package com.example.musinsasampleapp.data.vo


import com.google.gson.annotations.SerializedName

data class MeetingRoomInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("reservations")
    val reservations: List<Reservation>
) {
    data class Reservation(
        @SerializedName("startTime")
        val startTime: String,
        @SerializedName("endTime")
        val endTime: String
    )
}
package com.example.musinsasampleapp.data.vo

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("score")
    val score: Double,
    var checked: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return this.id == (other as User).id
    }

    override fun hashCode(): Int = this.id
}
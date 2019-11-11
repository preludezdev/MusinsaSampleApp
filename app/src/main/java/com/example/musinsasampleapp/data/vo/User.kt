package com.example.musinsasampleapp.data.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id") val id: Int,
    @SerializedName("login")
    @ColumnInfo(name = "login") val login: String,
    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    @SerializedName("score")
    @ColumnInfo(name = "score") val score: Double,
    @ColumnInfo(name = "checked") var checked: Boolean = false
)
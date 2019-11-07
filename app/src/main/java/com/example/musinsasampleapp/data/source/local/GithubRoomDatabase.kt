package com.example.musinsasampleapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musinsasampleapp.data.source.local.dao.GithubUsersDao
import com.example.musinsasampleapp.data.vo.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class GithubRoomDatabase : RoomDatabase() {

    abstract fun getGithubUsersDao(): GithubUsersDao

}
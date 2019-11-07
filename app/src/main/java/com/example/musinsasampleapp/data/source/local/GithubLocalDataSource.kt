package com.example.musinsasampleapp.data.source.local

import androidx.lifecycle.LiveData
import com.example.musinsasampleapp.data.vo.User

interface GithubLocalDataSource {
    fun loadMyUserList(): LiveData<List<User>>

    fun insertUser(user: User)

    fun deleteUserById(userId: Int)

    fun findUserById(userId: Int): User
}
package com.example.musinsasampleapp.data.source

import androidx.lifecycle.LiveData
import com.example.musinsasampleapp.data.vo.GithubUsersResponse
import com.example.musinsasampleapp.data.vo.User

interface GithubRepository {
    //remote
    fun getUsersByQuery(
        query: String?,
        onSuccess: (data: GithubUsersResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    )

    //local
    fun loadMyUserList(): LiveData<List<User>>

    fun insertUser(user: User)

    fun deleteUserById(userId: Int)

    fun findUserById(userId: Int): User?
}
package com.example.musinsasampleapp.data.source

import androidx.lifecycle.LiveData
import com.example.musinsasampleapp.data.vo.GithubUsersResponse
import com.example.musinsasampleapp.data.vo.User
import io.reactivex.Single

interface GithubRepository {
    //remote
    fun getUsersByQuery(query: String, page: Int, per_page: Int): Single<GithubUsersResponse>

    //local
    fun loadMyUserList(): LiveData<List<User>>

    fun insertUser(user: User)

    fun deleteUserById(userId: Int)

    fun findUserById(userId: Int): User?
}
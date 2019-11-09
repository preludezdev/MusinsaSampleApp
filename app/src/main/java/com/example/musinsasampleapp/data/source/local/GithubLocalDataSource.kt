package com.example.musinsasampleapp.data.source.local

import androidx.lifecycle.LiveData
import com.example.musinsasampleapp.data.vo.User
import io.reactivex.Completable
import io.reactivex.Maybe

interface GithubLocalDataSource {
    fun loadMyUserList(): LiveData<List<User>>

    fun insertUser(user: User): Completable

    fun deleteUserById(userId: Int): Completable

    fun findUserById(userId: Int): Maybe<User>
}
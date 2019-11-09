package com.example.musinsasampleapp.data.source

import androidx.lifecycle.LiveData
import com.example.musinsasampleapp.data.source.local.GithubLocalDataSource
import com.example.musinsasampleapp.data.source.remote.GithubRemoteDataSource
import com.example.musinsasampleapp.data.vo.User

class GithubRepositoryImpl(
    private val remoteDataSource: GithubRemoteDataSource,
    private val localDataSource: GithubLocalDataSource
) : GithubRepository {

    //remote
    override fun getUsersByQuery(query: String, page: Int, per_page: Int) =
        remoteDataSource.getUsersByQuery(query, page, per_page)

    //local
    override fun loadMyUserList(): LiveData<List<User>> = localDataSource.loadMyUserList()

    override fun insertUser(user: User) = localDataSource.insertUser(user)

    override fun deleteUserById(userId: Int) = localDataSource.deleteUserById(userId)

    override fun findUserById(userId: Int) = localDataSource.findUserById(userId)

}
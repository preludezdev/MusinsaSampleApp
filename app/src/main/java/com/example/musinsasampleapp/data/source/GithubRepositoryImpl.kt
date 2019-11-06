package com.example.musinsasampleapp.data.source

import com.example.musinsasampleapp.data.source.local.GithubLocalDataSource
import com.example.musinsasampleapp.data.source.remote.GithubRemoteDataSource
import com.example.musinsasampleapp.data.vo.GithubUsersResponse

class GithubRepositoryImpl(
    private val remoteDataSource: GithubRemoteDataSource,
    private val localDataSource: GithubLocalDataSource
) : GithubRepository {

    //remote
    override fun getUsersByQuery(
        query: String?,
        onSuccess: (data: GithubUsersResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        remoteDataSource.getUsersByQuery(query, onSuccess, onFail)
    }

}
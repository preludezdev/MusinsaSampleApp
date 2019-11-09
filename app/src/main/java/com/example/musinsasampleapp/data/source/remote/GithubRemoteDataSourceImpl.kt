package com.example.musinsasampleapp.data.source.remote

import com.example.musinsasampleapp.data.vo.GithubUsersResponse
import com.example.musinsasampleapp.network.GithubApiService
import io.reactivex.Single

class GithubRemoteDataSourceImpl(private val api: GithubApiService) : GithubRemoteDataSource {
    override fun getUsersByQuery(
        query: String,
        page: Int,
        per_page: Int
    ): Single<GithubUsersResponse> =
        api.searchUserByQuery(query, page, per_page)
}


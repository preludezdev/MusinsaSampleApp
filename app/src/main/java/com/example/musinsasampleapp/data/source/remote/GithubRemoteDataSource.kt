package com.example.musinsasampleapp.data.source.remote

import com.example.musinsasampleapp.data.vo.GithubUsersResponse
import io.reactivex.Single

interface GithubRemoteDataSource {
    fun getUsersByQuery(
        query: String,
        page: Int,
        per_page: Int
    ): Single<GithubUsersResponse>
}
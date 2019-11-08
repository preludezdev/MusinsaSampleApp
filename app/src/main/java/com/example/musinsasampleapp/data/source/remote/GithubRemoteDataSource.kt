package com.example.musinsasampleapp.data.source.remote

import com.example.musinsasampleapp.data.vo.GithubUsersResponse

interface GithubRemoteDataSource {
    fun getUsersByQuery(
        query: String?,
        page: Int,
        per_page: Int,
        onSuccess: (data: GithubUsersResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    )
}
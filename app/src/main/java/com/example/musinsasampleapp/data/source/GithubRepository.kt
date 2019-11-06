package com.example.musinsasampleapp.data.source

import com.example.musinsasampleapp.data.vo.GithubUsersResponse

interface GithubRepository {

    fun getUsersByQuery(
        query: String?,
        onSuccess: (data: GithubUsersResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    )
}
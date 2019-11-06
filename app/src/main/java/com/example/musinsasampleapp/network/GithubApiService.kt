package com.example.musinsasampleapp.network

import com.example.musinsasampleapp.data.vo.GithubUsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    fun searchUserByQuery(@Query("q") query: String): Call<GithubUsersResponse>

}
package com.example.musinsasampleapp.network

import com.example.musinsasampleapp.data.vo.GithubUsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    fun searchUserByQuery(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Single<GithubUsersResponse>

}
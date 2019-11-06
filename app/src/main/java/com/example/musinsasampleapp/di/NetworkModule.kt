package com.example.musinsasampleapp.di

import com.example.musinsasampleapp.network.GithubApiService
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_URL = "https://api.github.com/"

val networkModule = module {
    single { (get() as Retrofit).create(GithubApiService::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(get())
            .build()
    }

    single { GsonConverterFactory.create() as Converter.Factory }
}
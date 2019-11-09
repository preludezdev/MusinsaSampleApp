package com.example.musinsasampleapp.di

import com.example.musinsasampleapp.network.GithubApiService
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_URL = "https://api.github.com/"

val networkModule = module {
    single { (get() as Retrofit).create(GithubApiService::class.java) }

    single {
        Retrofit.Builder()
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .baseUrl(API_URL)
            .build()
    }

    single { GsonConverterFactory.create() as Converter.Factory }
    single { RxJava2CallAdapterFactory.create() as CallAdapter.Factory }
}
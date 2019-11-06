package com.example.musinsasampleapp.di

import com.example.musinsasampleapp.data.source.GithubRepository
import com.example.musinsasampleapp.data.source.GithubRepositoryImpl
import com.example.musinsasampleapp.data.source.local.GithubLocalDataSource
import com.example.musinsasampleapp.data.source.local.GithubLocalDataSourceImpl
import com.example.musinsasampleapp.data.source.remote.GithubRemoteDataSource
import com.example.musinsasampleapp.data.source.remote.GithubRemoteDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { GithubRepositoryImpl(get(named("Remote")), get(named("Local"))) as GithubRepository }
    single(named("Remote")) { GithubRemoteDataSourceImpl(get()) as GithubRemoteDataSource }
    single(named("Local")) { GithubLocalDataSourceImpl() as GithubLocalDataSource }
}
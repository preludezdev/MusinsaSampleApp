package com.example.musinsasampleapp.di

import com.example.musinsasampleapp.vm.GithubSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GithubSearchViewModel(get()) }
}
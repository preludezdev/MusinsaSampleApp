package com.example.musinsasampleapp

import android.app.Application
import com.example.musinsasampleapp.di.networkModule
import com.example.musinsasampleapp.di.repositoryModule
import com.example.musinsasampleapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MusinsaApplication : Application() {

    private val moduleList = listOf(viewModelModule, networkModule, repositoryModule)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MusinsaApplication)

            modules(moduleList)
        }
    }
}
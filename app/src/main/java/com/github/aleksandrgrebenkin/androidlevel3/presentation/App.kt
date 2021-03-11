package com.github.aleksandrgrebenkin.androidlevel3.presentation

import android.app.Application
import com.github.aleksandrgrebenkin.androidlevel3.di.application
import com.github.aleksandrgrebenkin.androidlevel3.di.historyScreen
import com.github.aleksandrgrebenkin.androidlevel3.di.searchScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, searchScreen, historyScreen))
        }
    }
}
package com.github.aleksandrgrebenkin.androidlevel3.presentation

import android.app.Application
import com.github.aleksandrgrebenkin.androidlevel3.di.aplication
import com.github.aleksandrgrebenkin.androidlevel3.di.mainScreen
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(aplication, mainScreen))
        }
    }
}
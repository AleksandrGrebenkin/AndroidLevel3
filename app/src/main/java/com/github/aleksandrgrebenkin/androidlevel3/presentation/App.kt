package com.github.aleksandrgrebenkin.androidlevel3.presentation

import android.app.Application
import com.github.aleksandrgrebenkin.androidlevel3.di.AppComponent
import com.github.aleksandrgrebenkin.androidlevel3.di.DaggerAppComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .application(this)
            .uiScheduler(AndroidSchedulers.mainThread())
            .build()
    }
}
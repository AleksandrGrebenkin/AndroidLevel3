package com.github.aleksandrgrebenkin.androidlevel3.di

import android.app.Application
import com.github.aleksandrgrebenkin.androidlevel3.presentation.App
import com.github.aleksandrgrebenkin.androidlevel3.presentation.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun uiScheduler(uiScheduler: Scheduler): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(app: App)
}
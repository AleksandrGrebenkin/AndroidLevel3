package com.github.aleksandrgrebenkin.androidlevel3.di

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepository
import com.github.aleksandrgrebenkin.androidlevel3.data.web.RetrofitWordRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWordRepository(): WordRepository = RetrofitWordRepository()
}
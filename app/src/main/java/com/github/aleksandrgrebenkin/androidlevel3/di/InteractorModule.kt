package com.github.aleksandrgrebenkin.androidlevel3.di

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepository
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.WordInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    internal fun provideWordInteractor(repository: WordRepository) = WordInteractor(repository)
}
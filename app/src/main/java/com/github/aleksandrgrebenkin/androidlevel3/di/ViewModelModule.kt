package com.github.aleksandrgrebenkin.androidlevel3.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.aleksandrgrebenkin.androidlevel3.presentation.viewmodel.WordViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WordViewModel::class)
    protected abstract fun wordViewModel(wordViewModel: WordViewModel): ViewModel
}
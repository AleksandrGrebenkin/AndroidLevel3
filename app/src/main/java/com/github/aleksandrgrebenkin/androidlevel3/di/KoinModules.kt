package com.github.aleksandrgrebenkin.androidlevel3.di

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepository
import com.github.aleksandrgrebenkin.androidlevel3.data.web.RetrofitWordRepository
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.WordInteractor
import com.github.aleksandrgrebenkin.androidlevel3.presentation.viewmodel.WordViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val aplication = module {
    single<WordRepository> { RetrofitWordRepository() }
}

val mainScreen = module {
    factory {
        WordInteractor(get())
    }
    viewModel {
        WordViewModel(get())
    }
}
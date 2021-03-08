package com.github.aleksandrgrebenkin.androidlevel3.di

import androidx.room.Room
import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepository
import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepositoryLocal
import com.github.aleksandrgrebenkin.androidlevel3.data.room.RoomWordRepositoryLocal
import com.github.aleksandrgrebenkin.androidlevel3.data.room.WordDatabase
import com.github.aleksandrgrebenkin.androidlevel3.data.web.RetrofitWordRepository
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.HistoryInteractor
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.WordInteractor
import com.github.aleksandrgrebenkin.androidlevel3.presentation.view.history.HistoryViewModel
import com.github.aleksandrgrebenkin.androidlevel3.presentation.view.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), WordDatabase::class.java, "WordDB").build() }
    single { get<WordDatabase>().wordDao() }
    single<WordRepository> { RetrofitWordRepository() }
    single<WordRepositoryLocal> { RoomWordRepositoryLocal(get()) }
}

val searchScreen = module {
    factory { WordInteractor(get()) }
    viewModel { SearchViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get()) }
    viewModel { HistoryViewModel(get()) }
}
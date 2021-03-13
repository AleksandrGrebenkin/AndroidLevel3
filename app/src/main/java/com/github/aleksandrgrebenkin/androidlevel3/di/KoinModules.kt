package com.github.aleksandrgrebenkin.androidlevel3.di

import androidx.room.Room
import com.github.aleksandrgrebenkin.core.viewmodel.HistoryViewModel
import com.github.aleksandrgrebenkin.core.viewmodel.SearchViewModel
import com.github.aleksandrgrebenkin.repository.contract.WordRepository
import com.github.aleksandrgrebenkin.repository.contract.WordRepositoryLocal
import com.github.aleksandrgrebenkin.repository.room.RoomWordRepositoryLocal
import com.github.aleksandrgrebenkin.repository.room.WordDatabase
import com.github.aleksandrgrebenkin.repository.web.RetrofitWordRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), WordDatabase::class.java, "WordDB").build() }
    single { get<WordDatabase>().wordDao() }
    single<WordRepository> { RetrofitWordRepository() }
    single<WordRepositoryLocal> { RoomWordRepositoryLocal(get()) }
}

val searchScreen = module {
    factory { com.github.aleksandrgrebenkin.core.domain.interactor.WordInteractor(get()) }
    viewModel { SearchViewModel(get()) }
}

val historyScreen = module {
    factory { com.github.aleksandrgrebenkin.core.domain.interactor.HistoryInteractor(get()) }
    viewModel { HistoryViewModel(get()) }
}
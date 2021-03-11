package com.github.aleksandrgrebenkin.androidlevel3.presentation.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.WordInteractor
import com.github.aleksandrgrebenkin.androidlevel3.presentation.view.base.BaseViewModel
import kotlinx.coroutines.*

class SearchViewModel(
    private val interactor: WordInteractor
) : BaseViewModel() {


    private val searchWordListLiveData: MutableLiveData<List<Word>> = MutableLiveData()


    fun subscribeWordList(): LiveData<List<Word>> = searchWordListLiveData


    fun search(word: String) {
        cancelJob()
        viewModelCoroutineScope.launch {
            updateSearchWordListLiveData(interactor.search(word))
        }
    }

    private fun updateSearchWordListLiveData(words: List<Word>) {
        searchWordListLiveData.value = words
    }



}
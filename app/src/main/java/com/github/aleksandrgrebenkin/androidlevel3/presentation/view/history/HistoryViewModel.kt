package com.github.aleksandrgrebenkin.androidlevel3.presentation.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.HistoryInteractor
import com.github.aleksandrgrebenkin.androidlevel3.presentation.view.base.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor
) : BaseViewModel() {

    private val historyWordListLiveData: MutableLiveData<List<Word>> = MutableLiveData()
    fun subscribeHistoryWordList(): LiveData<List<Word>> = historyWordListLiveData

    fun getAll() {
        cancelJob()
        viewModelCoroutineScope.launch {
            updateHistoryWordListLiveData(interactor.getAll())
        }
    }

    fun search(word: String) {
        cancelJob()
        viewModelCoroutineScope.launch {
            updateHistoryWordListLiveData(interactor.search(word))
        }
    }

    fun save(word: Word) {
        cancelJob()
        viewModelCoroutineScope.launch {
            interactor.save(word)
        }
    }

    private fun updateHistoryWordListLiveData(words: List<Word>) {
        historyWordListLiveData.value = words
    }



}
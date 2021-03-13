package com.github.aleksandrgrebenkin.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.aleksandrgrebenkin.model.entity.Word
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: com.github.aleksandrgrebenkin.core.domain.interactor.HistoryInteractor
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
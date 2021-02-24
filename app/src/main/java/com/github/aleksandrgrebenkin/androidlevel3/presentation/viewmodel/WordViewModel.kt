package com.github.aleksandrgrebenkin.androidlevel3.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.WordInteractor
import kotlinx.coroutines.*

class WordViewModel(
    private val interactor: WordInteractor
) : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            updateErrorLiveData(throwable)
        }
    )

    private val wordListLiveData: MutableLiveData<List<Word>> = MutableLiveData()
    private val wordErrorLiveData: MutableLiveData<String> = MutableLiveData()

    fun subscribeWordList(): LiveData<List<Word>> = wordListLiveData
    fun subscribeWordError(): LiveData<String> = wordErrorLiveData

    fun search(word: String) {
        cancelJob()
        viewModelCoroutineScope.launch {
            updateWordListLiveData(interactor.search(word))
        }
    }

    private fun updateWordListLiveData(words: List<Word>) {
        wordListLiveData.value = words
    }

    private fun updateErrorLiveData(error: Throwable) {
        wordErrorLiveData.value = error.message ?: error.stackTraceToString()
    }

    override fun onCleared() {
        cancelJob()
        super.onCleared()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}
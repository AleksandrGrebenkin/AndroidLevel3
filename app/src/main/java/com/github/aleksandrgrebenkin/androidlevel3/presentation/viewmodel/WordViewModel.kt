package com.github.aleksandrgrebenkin.androidlevel3.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.WordInteractor
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class WordViewModel @Inject constructor(
    private val interactor: WordInteractor,
    private val uiScheduler: Scheduler
) : ViewModel() {

    private var searchDisposable: Disposable? = null
    private val wordListLiveData: MutableLiveData<List<Word>> = MutableLiveData()
    private val wordErrorLiveData: MutableLiveData<String> = MutableLiveData()

    fun subscribeWordList(): LiveData<List<Word>> = wordListLiveData
    fun subscribeWordError(): LiveData<String> = wordErrorLiveData

    fun search(word: String) {
        searchDisposable = interactor.search(word)
            .observeOn(uiScheduler)
            .subscribe(
                { words ->
                    wordListLiveData.value = words
                },
                { error ->
                    wordErrorLiveData.value = error.message ?: error.stackTraceToString()
                }
            )
    }

    override fun onCleared() {
        searchDisposable?.dispose()
    }
}
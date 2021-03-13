package com.github.aleksandrgrebenkin.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.aleksandrgrebenkin.model.entity.Word
import kotlinx.coroutines.launch

class SearchViewModel(
    private val interactor: com.github.aleksandrgrebenkin.core.domain.interactor.WordInteractor
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
package com.github.aleksandrgrebenkin.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            updateErrorLiveData(throwable)
        }
    )

    protected val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun subscribeError(): LiveData<String> = errorLiveData

    protected fun updateErrorLiveData(error: Throwable) {
        errorLiveData.value = error.message ?: error.stackTraceToString()
    }

    override fun onCleared() {
        cancelJob()
        super.onCleared()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }


}
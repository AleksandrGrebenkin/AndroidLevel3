package com.github.aleksandrgrebenkin.androidlevel3.presentation.presenter

import com.github.aleksandrgrebenkin.androidlevel3.data.web.RetrofitWordRepository
import com.github.aleksandrgrebenkin.androidlevel3.domain.interactor.WordInteractor
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable

class MainPresenter(
    private val interactor: WordInteractor = WordInteractor(RetrofitWordRepository()),
    private val uiScheduler: Scheduler,
) {

    var currentView: MainView? = null
    private var searchDisposable: Disposable? = null

    fun attachView(view: MainView) {
        if (currentView != view) {
            currentView = view
        }
    }

    fun detachView(view: MainView) {
        searchDisposable?.dispose()
        if (view == currentView) {
            currentView = null
        }
    }

    fun search(word: String) {
        searchDisposable = interactor.search(word)
            .observeOn(uiScheduler)
            .subscribe(
                { words ->
                    currentView?.showList(words)
                },
                { error ->
                    currentView?.showError(error.message ?: error.stackTraceToString())
                }
            )
    }

}
package com.github.aleksandrgrebenkin.androidlevel3.presentation.presenter

import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word

interface MainView {
    fun showList(words: List<Word>)
    fun showError(error: String)
}
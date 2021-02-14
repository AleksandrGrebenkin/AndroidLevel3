package com.github.aleksandrgrebenkin.androidlevel3.domain.interactor

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepository
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import io.reactivex.rxjava3.core.Single

class WordInteractor(
    private val repository: WordRepository
) {
    fun search(word: String): Single<List<Word>> {
        return repository.search(word)
    }
}
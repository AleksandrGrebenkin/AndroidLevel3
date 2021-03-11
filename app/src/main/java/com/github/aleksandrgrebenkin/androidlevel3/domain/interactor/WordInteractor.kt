package com.github.aleksandrgrebenkin.androidlevel3.domain.interactor

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepository
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word

class WordInteractor(
    private val repository: WordRepository
) {
    suspend fun search(word: String): List<Word> {
        return repository.search(word)
    }
}
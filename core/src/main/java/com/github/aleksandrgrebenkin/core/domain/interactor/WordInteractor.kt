package com.github.aleksandrgrebenkin.core.domain.interactor

import com.github.aleksandrgrebenkin.model.entity.Word
import com.github.aleksandrgrebenkin.repository.contract.WordRepository

class WordInteractor(
    private val repository: WordRepository
) {
    suspend fun search(word: String): List<Word> {
        return repository.search(word)
    }
}
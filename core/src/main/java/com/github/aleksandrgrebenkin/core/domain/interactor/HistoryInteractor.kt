package com.github.aleksandrgrebenkin.core.domain.interactor

import com.github.aleksandrgrebenkin.model.entity.Word
import com.github.aleksandrgrebenkin.repository.contract.WordRepositoryLocal

class HistoryInteractor(
    private val localRepository: WordRepositoryLocal
) {
    suspend fun getAll(): List<Word> {
        return localRepository.getAll()
    }

    suspend fun search(word: String): List<Word> {
        return localRepository.search(word)
    }

    suspend fun save(word: Word) {
        localRepository.save(word)
    }
}
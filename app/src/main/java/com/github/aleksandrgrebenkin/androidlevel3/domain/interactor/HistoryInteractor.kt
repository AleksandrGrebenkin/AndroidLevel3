package com.github.aleksandrgrebenkin.androidlevel3.domain.interactor

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepositoryLocal
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word

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
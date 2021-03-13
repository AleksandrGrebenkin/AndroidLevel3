package com.github.aleksandrgrebenkin.repository.contract

import com.github.aleksandrgrebenkin.model.entity.Word

interface WordRepositoryLocal : WordRepository {
    suspend fun getAll() : List<Word>

    suspend fun save(word: Word)
}
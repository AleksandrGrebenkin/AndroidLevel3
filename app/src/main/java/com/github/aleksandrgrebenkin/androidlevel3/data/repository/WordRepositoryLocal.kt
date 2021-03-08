package com.github.aleksandrgrebenkin.androidlevel3.data.repository

import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word

interface WordRepositoryLocal : WordRepository {
    suspend fun getAll() : List<Word>

    suspend fun save(word: Word)
}
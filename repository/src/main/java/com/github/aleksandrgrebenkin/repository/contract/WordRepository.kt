package com.github.aleksandrgrebenkin.repository.contract

import com.github.aleksandrgrebenkin.model.entity.Word


interface WordRepository {
    suspend fun search(word: String): List<Word>
}

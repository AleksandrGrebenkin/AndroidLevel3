package com.github.aleksandrgrebenkin.androidlevel3.data.repository

import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import io.reactivex.rxjava3.core.Single

interface WordRepository {
    suspend fun search(word: String): List<Word>
}

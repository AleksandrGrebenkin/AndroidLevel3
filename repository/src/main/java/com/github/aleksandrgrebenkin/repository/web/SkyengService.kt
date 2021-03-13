package com.github.aleksandrgrebenkin.repository.web

import com.github.aleksandrgrebenkin.repository.web.entity.Word
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengService {

    @GET("words/search")
    fun searchAsync(@Query("search") word: String) : Deferred<List<Word>>
}
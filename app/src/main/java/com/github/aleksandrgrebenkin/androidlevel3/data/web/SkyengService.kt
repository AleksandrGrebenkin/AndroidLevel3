package com.github.aleksandrgrebenkin.androidlevel3.data.web

import com.github.aleksandrgrebenkin.androidlevel3.data.web.entity.Word
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengService {

    @GET("words/search")
    fun searchAsync(@Query("search") word: String) : Deferred<List<Word>>
}
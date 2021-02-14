package com.github.aleksandrgrebenkin.androidlevel3.data.web

import com.github.aleksandrgrebenkin.androidlevel3.data.web.entity.Word
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengService {
    @GET("words/search")
    fun search(@Query("search") word: String) : Single<List<Word>>
}
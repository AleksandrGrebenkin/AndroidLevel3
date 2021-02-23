package com.github.aleksandrgrebenkin.androidlevel3.data.web

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepository
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Meaning
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitWordRepository : WordRepository {

    private val skyengUrl = "https://dictionary.skyeng.ru/api/public/v1/"

    private fun getService(): SkyengService {
        return Retrofit.Builder()
            .baseUrl(skyengUrl)
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
            .create(SkyengService::class.java)
    }

    private fun createGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .excludeFieldsWithoutExposeAnnotation()
            .create()


    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    override fun search(word: String): Single<List<Word>> {
        return getService().search(word)
            .map { convertWords(it) }
            .subscribeOn(Schedulers.io())
    }

    private fun convertWords(apiWords: List<com.github.aleksandrgrebenkin.androidlevel3.data.web.entity.Word>): List<Word> {
        val result = mutableListOf<Word>()
        for (apiWord in apiWords) {
            val meanings = mutableListOf<Meaning>()
            apiWord.meanings?.let { apiMeanings ->
                for (apiMeaning in apiMeanings) {
                    apiMeaning.translation?.let { apiTranslation ->
                        meanings.add(
                            Meaning(
                                apiTranslation.text ?: "",
                                apiMeaning.partOfSpeechCode ?: ""
                            )
                        )
                    }
                }
            }
            apiWord.text?.let { text ->
                result.add(Word(text, meanings))
            }
        }
        return result
    }
}
package com.github.aleksandrgrebenkin.androidlevel3.data.web

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepository
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Meaning
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitWordRepository : WordRepository {

    private val skyengUrl = "https://dictionary.skyeng.ru/api/public/v1/"

    private fun getService(): SkyengService {
        return Retrofit.Builder()
            .baseUrl(skyengUrl)
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
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

    override suspend fun search(word: String): List<Word> {
        return convertWords(getService().searchAsync(word).await())
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
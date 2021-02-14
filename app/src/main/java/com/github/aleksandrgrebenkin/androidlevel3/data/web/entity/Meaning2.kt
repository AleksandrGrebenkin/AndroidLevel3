package com.github.aleksandrgrebenkin.androidlevel3.data.web.entity

import com.google.gson.annotations.Expose

data class Meaning2(
    @Expose val partOfSpeechCode: String?,
    @Expose val translation: Translation?
)

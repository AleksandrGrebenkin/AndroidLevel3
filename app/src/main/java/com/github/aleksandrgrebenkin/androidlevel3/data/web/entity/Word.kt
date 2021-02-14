package com.github.aleksandrgrebenkin.androidlevel3.data.web.entity

import com.google.gson.annotations.Expose

data class Word(
    @Expose val text: String?,
    @Expose val meanings: List<Meaning2>?
)
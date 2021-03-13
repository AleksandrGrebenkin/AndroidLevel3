package com.github.aleksandrgrebenkin.repository.web.entity

import com.google.gson.annotations.Expose

data class Word(
    @Expose val text: String?,
    @Expose val meanings: List<Meaning2>?
)
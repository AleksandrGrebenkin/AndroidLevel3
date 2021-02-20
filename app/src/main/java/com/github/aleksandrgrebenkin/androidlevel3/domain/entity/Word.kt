package com.github.aleksandrgrebenkin.androidlevel3.domain.entity

data class Word(
        val text: String,
        val meanings: List<Meaning>
)
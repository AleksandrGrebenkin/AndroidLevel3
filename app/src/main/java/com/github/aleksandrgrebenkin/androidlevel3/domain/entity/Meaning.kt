package com.github.aleksandrgrebenkin.androidlevel3.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meaning(
        val translation: String,
        val partOfSpeechCode: String,
        val imageUrl: String,
        val previewUrl: String
) : Parcelable

package com.github.aleksandrgrebenkin.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
        val text: String,
        val meanings: List<Meaning>
) : Parcelable
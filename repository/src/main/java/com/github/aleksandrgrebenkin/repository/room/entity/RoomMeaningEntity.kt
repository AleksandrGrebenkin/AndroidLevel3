package com.github.aleksandrgrebenkin.repository.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["word", "translation", "partOfSpeechCode"]
)
class RoomMeaningEntity(
    @ForeignKey(
        entity = RoomWordEntity::class,
        parentColumns = ["word"],
        childColumns = ["word"],
        onDelete = ForeignKey.CASCADE
    )
    var word: String,
    var translation: String,
    var partOfSpeechCode: String,
    var imageUrl: String?,
    var previewUrl: String?
)

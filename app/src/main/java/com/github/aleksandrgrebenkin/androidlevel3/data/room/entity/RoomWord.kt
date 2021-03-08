package com.github.aleksandrgrebenkin.androidlevel3.data.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class RoomWord(
    @Embedded
    val word: RoomWordEntity,
    @Relation(parentColumn = "word", entityColumn = "word")
    val meanings: List<RoomMeaningEntity>
)


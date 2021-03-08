package com.github.aleksandrgrebenkin.androidlevel3.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomWordEntity(

    @PrimaryKey
    var word: String
)
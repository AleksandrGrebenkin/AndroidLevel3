package com.github.aleksandrgrebenkin.repository.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomWordEntity(

    @PrimaryKey
    var word: String
)
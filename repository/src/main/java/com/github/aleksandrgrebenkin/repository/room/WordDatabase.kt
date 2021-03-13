package com.github.aleksandrgrebenkin.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.aleksandrgrebenkin.repository.room.entity.RoomMeaningEntity
import com.github.aleksandrgrebenkin.repository.room.entity.RoomWordEntity

@Database(entities = [RoomWordEntity::class, RoomMeaningEntity::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
}
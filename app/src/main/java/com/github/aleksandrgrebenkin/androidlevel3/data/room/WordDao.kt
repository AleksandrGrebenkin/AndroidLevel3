package com.github.aleksandrgrebenkin.androidlevel3.data.room

import androidx.room.*
import com.github.aleksandrgrebenkin.androidlevel3.data.room.entity.RoomMeaningEntity
import com.github.aleksandrgrebenkin.androidlevel3.data.room.entity.RoomWord
import com.github.aleksandrgrebenkin.androidlevel3.data.room.entity.RoomWordEntity

@Dao
interface WordDao {

    @Transaction
   suspend fun insert(words: List<RoomWord>) {
        words.map {
            insertWord(it.word)
            insertMeaning(it.meanings)
        }
    }

    @Transaction
    suspend fun insert(word: RoomWord) {
        insertWord(word.word)
        insertMeaning(word.meanings)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(words: List<RoomWordEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: RoomWordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeaning(meanings: List<RoomMeaningEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeaning(meaning: RoomMeaningEntity)

    @Delete
    suspend fun deleteWord(words: List<RoomWordEntity>)

    @Delete
    suspend fun deleteWord(word: RoomWordEntity)

    @Delete
    suspend fun deleteMeaning(meanings: List<RoomMeaningEntity>)

    @Delete
    suspend fun deleteMeaning(meaning: RoomMeaningEntity)

    @Transaction
    @Query("SELECT * FROM RoomWordEntity")
    suspend fun getAll(): List<RoomWord>

    @Transaction
    @Query("SELECT * FROM RoomWordEntity WHERE word LIKE :word")
    suspend fun searchWords(word: String): List<RoomWord>

}

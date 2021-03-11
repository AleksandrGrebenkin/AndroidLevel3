package com.github.aleksandrgrebenkin.androidlevel3.data.room

import com.github.aleksandrgrebenkin.androidlevel3.data.repository.WordRepositoryLocal
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word

class RoomWordRepositoryLocal(
    private val wordDao: WordDao
) : WordRepositoryLocal {
    override suspend fun getAll(): List<Word> {
        return convertRoomToDomainWordEntityList(wordDao.getAll())
        return emptyList()
    }

    override suspend fun save(word: Word) {
        convertDomainToRoomWordEntity(word).let {
            wordDao.insert(it)
        }
    }

    override suspend fun search(word: String): List<Word> {
        return convertRoomToDomainWordEntityList(wordDao.searchWords(word))
        return emptyList()
    }
}
package com.github.aleksandrgrebenkin.repository.room

import com.github.aleksandrgrebenkin.model.entity.Word
import com.github.aleksandrgrebenkin.repository.contract.WordRepositoryLocal

class RoomWordRepositoryLocal(
    private val wordDao: WordDao
) : WordRepositoryLocal {
    override suspend fun getAll(): List<Word> {
        return convertRoomToDomainWordEntityList(wordDao.getAll())
    }

    override suspend fun save(word: Word) {
        convertDomainToRoomWordEntity(word).let {
            wordDao.insert(it)
        }
    }

    override suspend fun search(word: String): List<Word> {
        return convertRoomToDomainWordEntityList(wordDao.searchWords(word))
    }
}
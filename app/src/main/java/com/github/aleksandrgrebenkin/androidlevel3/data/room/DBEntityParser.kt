package com.github.aleksandrgrebenkin.androidlevel3.data.room

import com.github.aleksandrgrebenkin.androidlevel3.data.room.entity.RoomMeaningEntity
import com.github.aleksandrgrebenkin.androidlevel3.data.room.entity.RoomWord
import com.github.aleksandrgrebenkin.androidlevel3.data.room.entity.RoomWordEntity
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Meaning
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word

fun convertRoomToDomainWordEntityList(list: List<RoomWord>): List<Word> {
    val words = mutableListOf<Word>()
    list.map {
        words.add(convertRoomToDomainWord(it))
    }
    return words
}

fun convertRoomToDomainWord(word: RoomWord): Word {
    val meanings = convertRoomToDomainMeaningEntityList(word.meanings)
    return Word(word.word.word, meanings)
}

private fun convertRoomToDomainMeaningEntityList(list: List<RoomMeaningEntity>): List<Meaning> {
    val meanings = mutableListOf<Meaning>()
    list.map { roomMeaning ->
        meanings.add(
            Meaning(
                roomMeaning.translation,
                roomMeaning.partOfSpeechCode,
                roomMeaning.imageUrl ?: "",
                roomMeaning.previewUrl ?: ""
            )
        )
    }
    return meanings
}

fun convertDomainToRoomWordEntityList(list: List<Word>): List<RoomWord> {
    val words = mutableListOf<RoomWord>()
    list.map {
        words.add(convertDomainToRoomWordEntity(it))
    }
    return words
}

fun convertDomainToRoomWordEntity(word: Word): RoomWord {
    val meanings = convertDomainToRoomMeaningEntityList(word.text, word.meanings)
    return RoomWord(RoomWordEntity(word.text), meanings)
}

private fun convertDomainToRoomMeaningEntityList(
    word: String,
    list: List<Meaning>
): List<RoomMeaningEntity> {
    val meanings = mutableListOf<RoomMeaningEntity>()
    list.map { meaning ->
        meanings.add(
            RoomMeaningEntity(
                word,
                meaning.translation,
                meaning.partOfSpeechCode,
                meaning.imageUrl,
                meaning.previewUrl
            )
        )
    }
    return meanings
}
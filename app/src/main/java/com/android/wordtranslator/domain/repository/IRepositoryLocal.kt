package com.android.wordtranslator.domain.repository

import com.android.wordtranslator.domain.storage.entity.WordFavourite
import com.android.wordtranslator.domain.storage.entity.WordTranslate

interface IRepositoryLocal {

    suspend fun saveToDb(word: WordTranslate)

    suspend fun saveToDb(words: List<WordTranslate>)

    suspend fun fetchHistory(): List<WordTranslate>

    suspend fun findInHistoryByWord(word: String): WordTranslate

    suspend fun fetchFavourite(): List<WordFavourite>

    suspend fun insertWordToFavourite(word: WordFavourite): Long

    suspend fun clearFavourite(): Int

    suspend fun clearHistory(): Int
}
package com.android.domain.repository.datasource

import com.android.domain.storage.entity.WordFavourite
import com.android.domain.storage.entity.WordTranslate

interface IDataSourceLocal {

    suspend fun saveToDB(word: WordTranslate)

    suspend fun saveToDB(words: List<WordTranslate>)

    suspend fun fetchHistory(): List<WordTranslate>

    suspend fun findInHistoryByWord(word: String): WordTranslate

    suspend fun fetchFavourite(): List<WordFavourite>

    suspend fun insertWordToFavourite(word: WordFavourite): Long

    suspend fun clearFavourite(): Int

    suspend fun clearHistory(): Int
}
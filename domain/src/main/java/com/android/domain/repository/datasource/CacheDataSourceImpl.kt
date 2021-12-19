package com.android.domain.repository.datasource

import com.android.domain.storage.WordStorage
import com.android.domain.storage.entity.WordFavourite
import com.android.domain.storage.entity.WordTranslate

class CacheDataSourceImpl(private val wordStorage: WordStorage) : IDataSourceLocal {

    override suspend fun saveToDB(word: WordTranslate) {
        wordStorage
            .wordDao()
            .insertWordToHistory(word)
    }

    override suspend fun saveToDB(words: List<WordTranslate>) {
        wordStorage
            .wordDao()
            .insertWordsToHistory(words)
    }

    override suspend fun fetchHistory(): List<WordTranslate> =
        wordStorage
            .wordDao()
            .fetchHistory()

    override suspend fun findInHistoryByWord(word: String): WordTranslate =
        wordStorage
            .wordDao()
            .findInHistoryByWord(word)

    override suspend fun fetchFavourite(): List<WordFavourite> =
        wordStorage
            .wordDao()
            .fetchFavourite()

    override suspend fun insertWordToFavourite(word: WordFavourite): Long =
        wordStorage
            .wordDao()
            .insertWordToFavourite(word)

    override suspend fun clearFavourite(): Int =
        wordStorage
            .wordDao()
            .clearFavourite()

    override suspend fun clearHistory(): Int =
        wordStorage
            .wordDao()
            .clearHistory()

}

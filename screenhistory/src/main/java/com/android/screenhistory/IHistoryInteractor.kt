package com.android.screenhistory

import com.android.domain.storage.entity.WordTranslate

interface IHistoryInteractor<T> {
    suspend fun getData(): List<WordTranslate>
    suspend fun saveToDb(word: WordTranslate)
    suspend fun saveToDb(words: List<WordTranslate>)
    suspend fun clearHistory(): Int
}
package com.android.wordtranslator.view.main

import com.android.wordtranslator.domain.storage.entity.WordFavourite
import com.android.wordtranslator.domain.storage.entity.WordTranslate

interface IMainInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun saveToDb(word: WordTranslate)
    suspend fun saveToDb(words: List<WordTranslate>)
    suspend fun insertWordToFavourite(word: WordFavourite): Long
}
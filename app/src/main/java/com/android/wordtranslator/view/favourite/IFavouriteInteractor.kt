package com.android.wordtranslator.view.favourite

import com.android.wordtranslator.domain.storage.entity.WordFavourite

interface IFavouriteInteractor<T> {
    suspend fun getData(): List<WordFavourite>
    suspend fun clearFavourite(): Int
}
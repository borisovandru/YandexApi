package com.android.screenfavourite

import com.android.domain.storage.entity.WordFavourite

interface IFavouriteInteractor<T> {
    suspend fun getData(): List<WordFavourite>
    suspend fun clearFavourite(): Int
}
package com.android.wordtranslator.view.favourite

import com.android.wordtranslator.domain.repository.IRepositoryLocal
import com.android.wordtranslator.domain.storage.entity.WordFavourite

class FavouriteInteractor(
    val repositoryLocal: IRepositoryLocal
) : IFavouriteInteractor<List<WordFavourite>> {
    override suspend fun getData(): List<WordFavourite> =
        repositoryLocal
            .fetchFavourite()

    override suspend fun clearFavourite(): Int =
        repositoryLocal
            .clearFavourite()
}
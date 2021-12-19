package com.android.screenfavourite

import com.android.domain.repository.IRepositoryLocal
import com.android.domain.storage.entity.WordFavourite

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
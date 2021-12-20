package com.android.wordtranslator.view.main

import com.android.model.DictionaryResult
import com.android.domain.repository.IRepository
import com.android.domain.repository.IRepositoryLocal
import com.android.domain.storage.entity.WordFavourite
import com.android.domain.storage.entity.WordTranslate

class MainInteractor(
    val repositoryRemote: IRepository<DictionaryResult>,
    val repositoryLocal: IRepositoryLocal
) : IMainInteractor<DictionaryResult> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DictionaryResult =
        repositoryRemote
            .getData(word)

    override suspend fun saveToDb(word: WordTranslate) {
        repositoryLocal.saveToDb(word)
    }

    override suspend fun saveToDb(words: List<WordTranslate>) {
        repositoryLocal.saveToDb(words)
    }

    override suspend fun insertWordToFavourite(word: WordFavourite) =
        repositoryLocal.insertWordToFavourite(word)
}
package com.android.wordtranslator.view.main

import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.IRepository
import com.android.wordtranslator.domain.repository.IRepositoryLocal
import com.android.wordtranslator.domain.storage.entity.WordFavourite
import com.android.wordtranslator.domain.storage.entity.WordTranslate

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
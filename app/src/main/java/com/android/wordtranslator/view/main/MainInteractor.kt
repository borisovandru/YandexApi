package com.android.wordtranslator.view.main

import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.IRepository
import com.android.wordtranslator.viewmodel.IInteractor

class MainInteractor(
    val repositoryRemote: IRepository<DictionaryResult>,
    val repositoryLocal: IRepository<DictionaryResult>
) : IInteractor<DictionaryResult> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): DictionaryResult {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word)
    }
}
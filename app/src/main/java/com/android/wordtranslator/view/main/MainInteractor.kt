package com.android.wordtranslator.view.main

import android.util.Log
import io.reactivex.Observable
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.IRepository
import com.android.wordtranslator.viewmodel.IInteractor

class MainInteractor(
    val repositoryRemote: IRepository<DictionaryResult>,
    val repositoryLocal: IRepository<DictionaryResult>
) : IInteractor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            Log.d("translatorDebug", "repositoryRemote")
            repositoryRemote
        } else {
            Log.d("translatorDebug", "repositoryLocal")
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }
}
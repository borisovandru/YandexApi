package com.android.wordtranslator.view.main

import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.IRepository
import com.android.wordtranslator.presenter.IInteractor
import io.reactivex.Observable

class MainInteractor(
    private val repository: IRepository<DictionaryResult>
) : IInteractor<AppState> {
    override fun getData(word: String): Observable<AppState> =
        repository.getData(word)
            .map { AppState.Success(it) }
}
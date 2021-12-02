package com.android.wordtranslator.domain.repository

import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.datasource.IDataSource
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: IDataSource<DictionaryResult>,
) : IRepository<DictionaryResult> {
    override fun getData(word: String): Observable<DictionaryResult> =
        dataSource.getData(word)
}
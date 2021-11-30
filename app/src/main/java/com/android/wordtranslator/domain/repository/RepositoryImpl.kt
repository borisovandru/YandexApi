package com.android.wordtranslator.domain.repository

import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.datasource.ICacheDataSource
import com.android.wordtranslator.domain.repository.datasource.INetworkDataSource
import io.reactivex.Observable

class RepositoryImpl(
    private val cloud: INetworkDataSource,
    private val cache: ICacheDataSource
) : IRepository<DictionaryResult> {

    override fun getData(word: String): Observable<DictionaryResult> =
        cloud.getData(word)
}
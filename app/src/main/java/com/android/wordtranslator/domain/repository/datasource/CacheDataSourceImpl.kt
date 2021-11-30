package com.android.wordtranslator.domain.repository.datasource

import com.android.wordtranslator.domain.model.DictionaryResult
import io.reactivex.Observable

class CacheDataSourceImpl : ICacheDataSource {
    override fun getData(word: String): Observable<DictionaryResult> =
        Observable.empty()
}
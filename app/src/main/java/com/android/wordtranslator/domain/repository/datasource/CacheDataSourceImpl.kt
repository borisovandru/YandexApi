package com.android.wordtranslator.domain.repository.datasource

import com.android.wordtranslator.domain.model.DictionaryResult
import io.reactivex.Observable

class CacheDataSourceImpl : IDataSource<DictionaryResult> {
    override fun getData(word: String): Observable<DictionaryResult> =
        Observable.error(Exception("Локальный источник данных еще не реализован"))
}
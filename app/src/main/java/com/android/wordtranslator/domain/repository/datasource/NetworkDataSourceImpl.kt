package com.android.wordtranslator.domain.repository.datasource

import com.android.wordtranslator.domain.api.YandexApi
import com.android.wordtranslator.domain.model.DictionaryResult
import io.reactivex.Observable

class NetworkDataSourceImpl(private val yandexApi: YandexApi) :
    IDataSource<DictionaryResult> {
    override fun getData(word: String): Observable<DictionaryResult> =
        yandexApi.search(word)
}
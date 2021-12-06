package com.android.wordtranslator.domain.repository.datasource

import com.android.wordtranslator.domain.api.YandexApi
import com.android.wordtranslator.domain.model.DictionaryResult

class NetworkDataSourceImpl(private val yandexApi: YandexApi) :
    IDataSource<DictionaryResult> {
    override suspend fun getData(word: String): DictionaryResult =
        yandexApi.searchAsync(word).await()
}
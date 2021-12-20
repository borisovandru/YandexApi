package com.android.domain.repository.datasource

import com.android.domain.api.YandexApi
import com.android.model.DictionaryResult

class NetworkDataSourceImpl(private val yandexApi: YandexApi) :
    IDataSource<DictionaryResult> {
    override suspend fun getData(word: String): DictionaryResult =
        yandexApi.searchAsync(word).await()
}
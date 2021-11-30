package com.android.wordtranslator.domain.repository.datasource

import com.android.wordtranslator.domain.api.YandexApiFactory

object NetworkDataSourceFactory {
    fun create(): INetworkDataSource = NetworkDataSourceImpl(YandexApiFactory.create())
}
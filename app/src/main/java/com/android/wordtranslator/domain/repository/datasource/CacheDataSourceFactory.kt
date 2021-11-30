package com.android.wordtranslator.domain.repository.datasource

object CacheDataSourceFactory {
    fun create(): ICacheDataSource =
        CacheDataSourceImpl()
}
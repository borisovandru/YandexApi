package com.android.wordtranslator.domain.repository

import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.datasource.CacheDataSourceFactory
import com.android.wordtranslator.domain.repository.datasource.NetworkDataSourceFactory

object RepositoryFactory {
    private val repository: IRepository<DictionaryResult> by lazy {
        RepositoryImpl(
            NetworkDataSourceFactory.create(),
            CacheDataSourceFactory.create()
        )
    }

    fun create(): IRepository<DictionaryResult> = repository
}
package com.android.wordtranslator.domain.repository

import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.datasource.IDataSource

class RepositoryImpl(
    private val dataSource: IDataSource<DictionaryResult>
) : IRepository<DictionaryResult> {

    override suspend fun getData(word: String): DictionaryResult =
        dataSource.getData(word)
}

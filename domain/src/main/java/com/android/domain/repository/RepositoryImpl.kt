package com.android.domain.repository

import com.android.model.DictionaryResult
import com.android.domain.repository.datasource.IDataSource

class RepositoryImpl(
    private val dataSource: IDataSource<DictionaryResult>
) : IRepository<DictionaryResult> {

    override suspend fun getData(word: String): DictionaryResult =
        dataSource.getData(word)
}

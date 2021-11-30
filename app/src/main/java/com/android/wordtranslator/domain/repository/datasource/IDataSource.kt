package com.android.wordtranslator.domain.repository.datasource

import io.reactivex.Observable

interface IDataSource<T> {
    fun getData(word: String): Observable<T>
}
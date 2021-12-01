package com.android.wordtranslator.domain.repository.datasource

import io.reactivex.Observable

interface IDataSource<T> {

    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    fun getData(word: String): Observable<T>
}
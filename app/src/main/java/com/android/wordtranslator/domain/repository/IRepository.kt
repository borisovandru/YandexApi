package com.android.wordtranslator.domain.repository

import io.reactivex.Observable

interface IRepository<T> {
    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    fun getData(word: String): Observable<T>
}
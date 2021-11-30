package com.android.wordtranslator.presenter

import io.reactivex.Observable

interface IInteractor<T> {
    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    fun getData(word: String): Observable<T>
}
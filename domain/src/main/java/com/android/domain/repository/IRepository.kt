package com.android.domain.repository

interface IRepository<T> {
    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    suspend fun getData(word: String): T
}
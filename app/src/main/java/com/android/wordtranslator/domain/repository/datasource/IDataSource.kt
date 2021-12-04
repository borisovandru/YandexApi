package com.android.wordtranslator.domain.repository.datasource

interface IDataSource<T> {
    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    suspend fun getData(word: String): T
}
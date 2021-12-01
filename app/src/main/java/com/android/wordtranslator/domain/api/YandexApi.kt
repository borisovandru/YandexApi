package com.android.wordtranslator.domain.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import com.android.wordtranslator.BuildConfig
import com.android.wordtranslator.domain.model.DictionaryResult

interface YandexApi {
    /**
     * Получить перевод
     * @param targetWord Слово, которое необходимо перевести
     * @return DictionaryResult
     */
    @GET("dicservice.json/lookup?key=${BuildConfig.API_TOKEN}&lang=en-ru")
    fun search(@Query("text") targetWord: String): Observable<DictionaryResult>
}
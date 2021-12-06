package com.android.wordtranslator.domain.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.android.wordtranslator.BuildConfig
import com.android.wordtranslator.domain.model.DictionaryResult
import kotlinx.coroutines.Deferred

interface YandexApi {
    /**
     * Получить перевод
     * @param targetWord Слово, которое необходимо перевести
     * @return DictionaryResult
     */
    @GET("dicservice.json/lookup?key=${BuildConfig.API_TOKEN}&lang=en-ru")
    fun searchAsync(@Query("text") targetWord: String): Deferred<DictionaryResult>
}
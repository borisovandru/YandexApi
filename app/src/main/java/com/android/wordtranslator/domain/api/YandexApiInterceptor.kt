package com.android.wordtranslator.domain.api

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import com.android.wordtranslator.BuildConfig

object YandexApiInterceptor : Interceptor {

    private const val USER_NAME = "demo"
    private const val HEADER_NAME = "Authorization"

    private const val CODE_UNDEFINED_ERROR = 0
    private const val CODE_INFO = 1
    private const val CODE_SUCCESS = 2
    private const val CODE_REDIRECTION = 3
    private const val CODE_CLIENT_ERROR = 4
    private const val CODE_SERVER_ERROR = 5
    private const val RESPONSE_CODE_DIVIDER = 100

    private var responseCode: Int = CODE_UNDEFINED_ERROR

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(
            chain.request()
                .newBuilder()
                .header(
                    HEADER_NAME,
                    Credentials.basic(USER_NAME, BuildConfig.API_TOKEN)
                )
                .build()
        )
        responseCode = response.code
        return response
    }

    fun getResponseCode(): ServerResponseStatusCode {
        return when (responseCode / RESPONSE_CODE_DIVIDER) {
            CODE_INFO -> ServerResponseStatusCode.INFO
            CODE_SUCCESS -> ServerResponseStatusCode.SUCCESS
            CODE_REDIRECTION -> ServerResponseStatusCode.REDIRECTION
            CODE_CLIENT_ERROR -> ServerResponseStatusCode.CLIENT_ERROR
            CODE_SERVER_ERROR -> ServerResponseStatusCode.SERVER_ERROR
            else -> ServerResponseStatusCode.UNDEFINED_ERROR
        }
    }

    enum class ServerResponseStatusCode {
        UNDEFINED_ERROR,
        INFO,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR
    }
}
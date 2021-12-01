package com.android.wordtranslator.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.android.wordtranslator.BuildConfig
import com.android.wordtranslator.domain.api.YandexApi
import com.android.wordtranslator.domain.api.YandexApiInterceptor
import javax.inject.Singleton

@Module
class YandexApiModule {

    private val gson: Gson =
        GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideYandexApi(): YandexApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(YandexApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(YandexApi::class.java)
}

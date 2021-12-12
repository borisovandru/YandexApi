package com.android.wordtranslator

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.android.wordtranslator.di.Di

class AppTranslator : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppTranslator)
            modules(
                listOf(
                    Di.viewModelModule(),
                    Di.interactorModule(),
                    Di.networkModule(),
                    Di.repositoryModule(),
                    Di.yandexApiModule(),
                    Di.navigationModule(),
                    Di.storageModule()
                )
            )
        }
    }
}
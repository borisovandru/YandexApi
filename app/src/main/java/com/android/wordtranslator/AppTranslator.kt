package com.android.wordtranslator

import com.android.wordtranslator.di.DaggerApplicationComponent
import com.android.wordtranslator.domain.scheduler.DefaultSchedulers
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppTranslator : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<AppTranslator> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(DefaultSchedulers())
            .build()
}
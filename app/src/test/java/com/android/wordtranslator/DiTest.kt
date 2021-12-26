package com.android.wordtranslator

import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import com.android.wordtranslator.di.Di

class DiTest : KoinTest {

    @Test
    fun testFunc() {

        val listModules = listOf(
            Di.viewModelModule(),
            Di.interactorModule(),
            Di.networkModule(),
            Di.repositoryModule(),
            Di.yandexApiModule(),
            Di.navigationModule()
        )

        checkModules { listModules }
    }
}
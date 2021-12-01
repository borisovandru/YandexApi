package com.android.wordtranslator.di.module

import com.android.wordtranslator.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ScreenModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}

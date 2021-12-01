package com.android.wordtranslator.di.module

import android.content.Context
import com.android.wordtranslator.utils.network.NetworkStateObservable
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideNetworkState(context: Context) = NetworkStateObservable(context)
}

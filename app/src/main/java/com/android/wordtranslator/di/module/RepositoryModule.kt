package com.android.wordtranslator.di.module

import com.android.wordtranslator.di.Qualifiers.Local
import com.android.wordtranslator.di.Qualifiers.Remote
import com.android.wordtranslator.domain.api.YandexApi
import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.IRepository
import com.android.wordtranslator.domain.repository.RepositoryImpl
import com.android.wordtranslator.domain.repository.datasource.CacheDataSourceImpl
import com.android.wordtranslator.domain.repository.datasource.IDataSource
import com.android.wordtranslator.domain.repository.datasource.NetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Remote
    internal fun provideRepositoryRemote(@Remote dataSourceRemote: IDataSource<DictionaryResult>): IRepository<DictionaryResult> =
        RepositoryImpl(dataSourceRemote)

    @Provides
    @Singleton
    @Local
    internal fun provideRepositoryLocal(@Local dataSourceLocal: IDataSource<DictionaryResult>): IRepository<DictionaryResult> =
        RepositoryImpl(dataSourceLocal)

    @Provides
    @Singleton
    @Remote
    internal fun provideDataSourceRemote(yandexApi: YandexApi): IDataSource<DictionaryResult> =
        NetworkDataSourceImpl(yandexApi)

    @Provides
    @Singleton
    @Local
    internal fun provideDataSourceLocal(): IDataSource<DictionaryResult> = CacheDataSourceImpl()
}

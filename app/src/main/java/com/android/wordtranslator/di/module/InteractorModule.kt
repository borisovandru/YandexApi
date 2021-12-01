package com.android.wordtranslator.di.module

import dagger.Module
import dagger.Provides
import com.android.wordtranslator.di.Qualifiers.Local
import com.android.wordtranslator.di.Qualifiers.Remote
import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.IRepository
import com.android.wordtranslator.view.main.MainInteractor

@Module
class InteractorModule {

    @Provides
    internal fun provideMainInteractor(
        @Remote repositoryRemote: IRepository<DictionaryResult>,
        @Local repositoryLocal: IRepository<DictionaryResult>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}

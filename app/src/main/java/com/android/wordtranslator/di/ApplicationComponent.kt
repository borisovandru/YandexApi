package com.android.wordtranslator.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.android.wordtranslator.AppTranslator
import com.android.wordtranslator.di.module.*
import com.android.wordtranslator.domain.scheduler.Schedulers
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        YandexApiModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ScreenModule::class,
        AndroidSupportInjectionModule::class]
)
interface ApplicationComponent : AndroidInjector<AppTranslator> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): ApplicationComponent
    }
}
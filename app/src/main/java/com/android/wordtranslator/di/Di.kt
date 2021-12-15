package com.android.wordtranslator.di

import androidx.room.Room
import com.android.wordtranslator.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.android.wordtranslator.domain.api.YandexApi
import com.android.wordtranslator.domain.api.YandexApiInterceptor
import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.repository.IRepository
import com.android.wordtranslator.domain.repository.IRepositoryLocal
import com.android.wordtranslator.domain.repository.RepositoryImpl
import com.android.wordtranslator.domain.repository.RepositoryLocalImpl
import com.android.wordtranslator.domain.repository.datasource.CacheDataSourceImpl
import com.android.wordtranslator.domain.repository.datasource.NetworkDataSourceImpl
import com.android.wordtranslator.domain.storage.WordStorage
import com.android.wordtranslator.utils.network.NetworkStateObservable
import com.android.wordtranslator.view.favourite.FavouriteInteractor
import com.android.wordtranslator.view.favourite.FavouriteViewModel
import com.android.wordtranslator.view.history.HistoryInteractor
import com.android.wordtranslator.view.history.HistoryViewModel
import com.android.wordtranslator.view.main.MainInteractor
import com.android.wordtranslator.view.main.MainViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

object Di {
    private const val PERSISTED = "Persisted"
    private const val IN_MEMORY = "InMemory"
    private const val DATABASE_NAME = "translator_database"
    fun viewModelModule() = module {
        viewModel {
            MainViewModel(
                interactor = get(),
                networkState = get(),
            )
        }
        viewModel {
            HistoryViewModel(interactor = get())
        }
        viewModel {
            FavouriteViewModel(interactor = get())
        }
    }

    fun interactorModule() = module {
        factory {
            MainInteractor(
                repositoryLocal = get(),
                repositoryRemote = get()
            )
        }
        factory {
            HistoryInteractor(
                repositoryLocal = get(),
            )
        }
        factory {
            FavouriteInteractor(
                repositoryLocal = get(),
            )
        }
    }

    fun networkModule() = module {
        single { NetworkStateObservable(context = androidContext()) }
    }

    fun repositoryModule() = module {
        single<IRepository<DictionaryResult>> {
            RepositoryImpl(
                dataSource = NetworkDataSourceImpl(
                    yandexApi = get()
                )
            )
        }
        single<IRepositoryLocal> {
            RepositoryLocalImpl(
                dataSource = CacheDataSourceImpl(get(named(PERSISTED)))
            )
        }
    }

    fun yandexApiModule() = module {
        single<Interceptor> {
            YandexApiInterceptor()
        }
        single<Gson> {
            GsonBuilder()
                .create()
        }
        single<YandexApi> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(interceptor = get())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) {
                                HttpLoggingInterceptor.Level.BODY
                            } else {
                                HttpLoggingInterceptor.Level.NONE
                            }
                        })
                        .build()
                )
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
                .create(YandexApi::class.java)
        }
    }

    fun navigationModule() = module {
        single<Cicerone<Router>> {
            Cicerone.create()
        }
        single<NavigatorHolder> {
            get<Cicerone<Router>>().getNavigatorHolder()
        }
        single<Router> {
            get<Cicerone<Router>>().router
        }
    }

    fun storageModule() = module {
        single<WordStorage>(qualifier = named(PERSISTED)) {
            Room.databaseBuilder(androidContext(), WordStorage::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
        single<WordStorage>(qualifier = named(IN_MEMORY)) {
            Room.inMemoryDatabaseBuilder(androidContext(), WordStorage::class.java)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

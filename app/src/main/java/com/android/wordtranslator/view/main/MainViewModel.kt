package com.android.wordtranslator.view.main

import androidx.lifecycle.LiveData
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.repository.RepositoryImpl
import com.android.wordtranslator.domain.repository.datasource.CacheDataSourceFactory
import com.android.wordtranslator.domain.repository.datasource.NetworkDataSourceFactory
import com.android.wordtranslator.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableObserver

class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImpl(
            NetworkDataSourceFactory.create(),
            CacheDataSourceFactory.create()
        )
    )
) : BaseViewModel<AppState>() {
    private var appState: AppState? = null

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.background())
                .observeOn(schedulerProvider.main())
                .doOnSubscribe { liveDataForViewToObserve.postValue(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
        return super.getData(word)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                appState = state
                liveDataForViewToObserve.postValue(state)
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.postValue(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}

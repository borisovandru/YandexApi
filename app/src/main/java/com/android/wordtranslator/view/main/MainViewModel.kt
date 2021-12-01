package com.android.wordtranslator.view.main

import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.scheduler.Schedulers
import com.android.wordtranslator.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor,
    private val schedulers: Schedulers
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun translateLiveData(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable.add(
            interactor
                .getData(word, true)
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.main())
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

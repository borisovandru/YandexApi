package com.android.wordtranslator.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.scheduler.Schedulers
import com.android.wordtranslator.utils.network.NetworkState
import com.android.wordtranslator.utils.network.NetworkStateObservable
import com.android.wordtranslator.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor,
    private val schedulers: Schedulers,
    private val networkState: NetworkStateObservable
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun translateLiveData(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun networkStateLiveData(): LiveData<Boolean> {
        return liveDataForNetworkState
    }

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable +=
            interactor
                .getData(word, true)
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.main())
                .doOnSubscribe { liveDataForViewToObserve.postValue(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        return super.getData(word)
    }

    override fun getNetworkState(): LiveData<Boolean> {
        compositeDisposable +=
            networkState
                .doOnNext { state ->
                    liveDataForNetworkState.postValue(state == NetworkState.CONNECTED)
                    Log.d("networkState", "publish")
                }
                .publish()
                .connect()

        return super.getNetworkState()
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

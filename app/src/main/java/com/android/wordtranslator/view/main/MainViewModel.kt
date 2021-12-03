package com.android.wordtranslator.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.scheduler.Schedulers
import com.android.wordtranslator.utils.network.NetworkState
import com.android.wordtranslator.utils.network.NetworkStateObservable
import com.android.wordtranslator.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.plusAssign
import java.util.concurrent.TimeUnit

class MainViewModel constructor(
    private val interactor: MainInteractor,
    private val schedulers: Schedulers,
    private val networkState: NetworkStateObservable,
    private val state: SavedStateHandle
) : BaseViewModel<AppState>() {
    companion object {
        private const val LAST_INPUT_WORD = "lastWord"
        private const val LOG_TAG = "SavedStateHandleTest"
        private const val TEXT_SAVE = "Save: "
        private const val TEXT_RESTORE = "Restore: "
        private const val DELAY_LOADING = 0L
    }

    fun saveLastWord(word: String) {
        state.set(LAST_INPUT_WORD, word)
        Log.d(LOG_TAG, "$TEXT_SAVE${word}")
    }

    fun getLastWord(): String {
        Log.d(LOG_TAG, "$TEXT_RESTORE${state.get(LAST_INPUT_WORD) ?: ""}")
        return state.get(LAST_INPUT_WORD) ?: ""
    }

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
                .delay(DELAY_LOADING, TimeUnit.SECONDS)
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

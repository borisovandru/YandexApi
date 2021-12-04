package com.android.wordtranslator.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.utils.network.NetworkState
import com.android.wordtranslator.utils.network.NetworkStateObservable
import com.android.wordtranslator.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val interactor: MainInteractor,
    private val networkState: NetworkStateObservable,
    private val state: SavedStateHandle
) : BaseViewModel<AppState>() {
    companion object {
        private const val LAST_INPUT_WORD = "lastWord"
        private const val LOG_TAG = "SavedStateHandleTest"
        private const val TEXT_SAVE = "Save: "
        private const val TEXT_RESTORE = "Restore: "

        //Задержка для экспериментов с корутинами
        private const val DELAY_LOADING = 1500L
        private const val EMPTY_RESULT_MESSAGE = "Отсутсвуют данные. Измените/повторите запрос."
    }

    fun saveLastWord(word: String) {
        state.set(LAST_INPUT_WORD, word)
        Log.d(LOG_TAG, "$TEXT_SAVE${word}")
    }

    fun getLastWord(): String {
        Log.d(LOG_TAG, "$TEXT_RESTORE${state.get(LAST_INPUT_WORD) ?: ""}")
        return state.get(LAST_INPUT_WORD) ?: ""
    }

    fun translateLiveData(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun networkStateLiveData(): LiveData<Boolean> {
        return liveDataForNetworkState
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        delay(DELAY_LOADING)
        val result = interactor.getData(word, isOnline)

        if (result.dictionaryEntryList.isNotEmpty()) {
            liveDataForViewToObserve.postValue(AppState.Success(result))
        } else {
            liveDataForViewToObserve.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
        }
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
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
}

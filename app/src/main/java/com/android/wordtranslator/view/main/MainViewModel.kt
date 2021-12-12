package com.android.wordtranslator.view.main

import androidx.lifecycle.LiveData
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.domain.storage.entity.WordTranslate
import com.android.wordtranslator.utils.mapToListWordTranslate
import com.android.wordtranslator.utils.mapTranslateToFavourite
import com.android.wordtranslator.utils.network.NetworkState
import com.android.wordtranslator.utils.network.NetworkStateObservable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val interactor: MainInteractor,
    private val networkState: NetworkStateObservable,
) : BaseMainViewModel<AppState>() {

    companion object {
        //Задержка для экспериментов с корутинами
        private const val DELAY_LOADING = 300L

        private const val EMPTY_RESULT_MESSAGE = "Отсутсвуют данные. Измените/повторите запрос."
    }

    fun translateLiveData(): LiveData<AppState> {
        return translateLiveData
    }

    fun networkStateLiveData(): LiveData<Boolean> {
        return networkStateLiveData
    }

    fun findHistoryLiveData(): LiveData<AppState> {
        return historyLiveData
    }

    fun favouriteLiveData(): LiveData<AppState> {
        return favouritesLiveData
    }

    override fun findInHistory(word: String) {
        historyLiveData.postValue(AppState.Loading(null))
        cancelJob()

        viewModelCoroutineScope.launch {
            val result = interactor.repositoryLocal.findInHistoryByWord(word)

            if (result?.word != null) {
                historyLiveData.postValue(AppState.Success(result))
            } else {
                historyLiveData.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
            }
        }
    }

    override fun saveToFavourite(word: WordTranslate) {
        favouritesLiveData.postValue(AppState.Loading(null))
        cancelJob()

        viewModelCoroutineScope.launch {
            val result =
                interactor.repositoryLocal.insertWordToFavourite(mapTranslateToFavourite(word))
            if (result > 0) {
                favouritesLiveData.postValue(AppState.Success(result))
            } else {
                favouritesLiveData.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
            }
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        translateLiveData.postValue(AppState.Loading(null))
        cancelJob()

        viewModelCoroutineScope.launch {
            delay(DELAY_LOADING)
            val result = interactor.getData(word, isOnline)
            if (result.dictionaryEntryList.isNotEmpty()) {
                translateLiveData.postValue(AppState.Success(result))
            } else {
                translateLiveData.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
            }
        }
    }

    override fun handleError(error: Throwable) {
        translateLiveData.postValue(AppState.Error(error))
        historyLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        historyLiveData.postValue(AppState.Success(null))
        super.onCleared()
    }

    override fun getNetworkState(): LiveData<Boolean> {
        compositeDisposable +=
            networkState
                .doOnNext { state ->
                    networkStateLiveData.postValue(state == NetworkState.CONNECTED)
                }
                .publish()
                .connect()
        return super.getNetworkState()
    }

    fun saveToHistory(translates: DictionaryResult) {
        viewModelCoroutineScope.launch {
            interactor
                .repositoryLocal
                .saveToDb(mapToListWordTranslate(translates))
        }
    }

    fun reset() {
        cancelJob()
        compositeDisposable.clear()
        historyLiveData.postValue(AppState.Success(null))
    }
}

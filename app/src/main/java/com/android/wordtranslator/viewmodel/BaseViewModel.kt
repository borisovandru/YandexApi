package com.android.wordtranslator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.wordtranslator.domain.model.AppState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val liveDataForNetworkState: MutableLiveData<Boolean> = MutableLiveData(),
) : ViewModel() {

    open fun getData(word: String): LiveData<T> = liveDataForViewToObserve

    open fun getNetworkState(): LiveData<Boolean> = liveDataForNetworkState

    override fun onCleared() {
        compositeDisposable.clear()
    }
}

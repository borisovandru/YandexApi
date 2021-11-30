package com.android.wordtranslator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.scheduler.SchedulerFactory
import com.android.wordtranslator.domain.scheduler.Schedulers
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: Schedulers = SchedulerFactory.create()
) : ViewModel() {

    open fun getData(word: String): LiveData<T> = liveDataForViewToObserve

    override fun onCleared() {
        compositeDisposable.clear()
    }
}

package com.android.wordtranslator.view.favourite

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.wordtranslator.domain.model.AppState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

abstract class BaseFavouriteViewModel<T : AppState>(
    protected val favouriteWordsLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val clearLiveData: MutableLiveData<T> = MutableLiveData(),
) : ViewModel(), LifecycleObserver {

    companion object {
        private const val CANCEL_MESSAGE = "Уже не актуально."
    }

    abstract fun getData()
    abstract fun clearFavourite()

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { coroutineContext, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
        compositeDisposable.clear()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren(CancellationException(CANCEL_MESSAGE))
    }

    abstract fun handleError(error: Throwable)
}
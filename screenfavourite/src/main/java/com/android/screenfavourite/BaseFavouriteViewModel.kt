package com.android.screenfavourite

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import com.android.model.AppState

abstract class BaseFavouriteViewModel<T : AppState>(
    protected val favouriteWordsLiveData: MutableLiveData<T> = MutableLiveData(),
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
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren(CancellationException(CANCEL_MESSAGE))
    }

    abstract fun handleError(error: Throwable)
}
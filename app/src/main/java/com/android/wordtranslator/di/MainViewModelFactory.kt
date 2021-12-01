package com.android.wordtranslator.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.android.wordtranslator.domain.scheduler.Schedulers
import com.android.wordtranslator.utils.network.NetworkStateObservable
import com.android.wordtranslator.view.main.MainInteractor
import com.android.wordtranslator.view.main.MainViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class MainViewModelFactory @AssistedInject constructor(
    private val interactor: MainInteractor,
    private val schedulers: Schedulers,
    private val networkState: NetworkStateObservable,
    @Assisted owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = MainViewModel(interactor, schedulers, networkState, handle) as T
}

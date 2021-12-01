package com.android.wordtranslator.di

import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MainViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): MainViewModelFactory
}
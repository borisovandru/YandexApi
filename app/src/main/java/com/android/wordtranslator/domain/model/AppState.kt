package com.android.wordtranslator.domain.model

sealed class AppState {
    data class Success(val data: DictionaryResult?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
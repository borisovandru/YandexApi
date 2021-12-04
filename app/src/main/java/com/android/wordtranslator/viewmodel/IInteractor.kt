package com.android.wordtranslator.viewmodel

interface IInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
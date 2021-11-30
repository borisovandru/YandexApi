package com.android.wordtranslator.view.base

import com.android.wordtranslator.domain.model.AppState

interface IView {
    /**
     * Отобразить результаты
     * @param appState State
     */
    fun renderData(appState: AppState)
}
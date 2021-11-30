package com.android.wordtranslator.presenter

import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.view.base.IView

interface IPresenter<T : AppState, V : IView> {
    /**
     * Привязать вьюху
     * @view Вьюха
     */
    fun attachView(view: V)

    /**
     * Отвязать вьюху
     * @view Вьюха
     */
    fun detachView(view: V)

    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     */
    fun getData(word: String)
}
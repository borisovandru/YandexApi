package com.android.wordtranslator.view.base

import androidx.appcompat.app.AppCompatActivity
import com.android.wordtranslator.R
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity(R.layout.activity_main) {

    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)
}
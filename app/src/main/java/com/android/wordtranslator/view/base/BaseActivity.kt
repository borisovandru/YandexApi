package com.android.wordtranslator.view.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.wordtranslator.R
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.viewmodel.BaseViewModel
import com.android.wordtranslator.viewmodel.IInteractor

abstract class BaseActivity<T : AppState, I : IInteractor<T>> :
    AppCompatActivity(R.layout.activity_main) {
    protected var isNetworkAvailable: Boolean = false
    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)
    protected fun noInternetMessageShow() {
        Toast.makeText(baseContext, getString(R.string.no_internet_message), Toast.LENGTH_LONG)
            .show()
    }
}
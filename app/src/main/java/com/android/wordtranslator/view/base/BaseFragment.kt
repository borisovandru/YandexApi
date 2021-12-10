package com.android.wordtranslator.view.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.wordtranslator.R
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.model.DictionaryResult
import com.android.wordtranslator.viewmodel.BaseViewModel
import com.android.wordtranslator.viewmodel.IInteractor

abstract class BaseFragment<T : AppState, I : IInteractor<DictionaryResult>> :
    Fragment(R.layout.fragment_main) {
    protected var isNetworkAvailable: Boolean = false
    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)
    protected fun noInternetMessageShow() {
        Toast.makeText(requireContext(), getString(R.string.no_internet_message), Toast.LENGTH_LONG)
            .show()
    }
}

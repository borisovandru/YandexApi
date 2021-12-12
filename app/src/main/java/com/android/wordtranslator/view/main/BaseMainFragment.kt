package com.android.wordtranslator.view.main

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.wordtranslator.R
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.model.DictionaryResult

abstract class BaseMainFragment<T : AppState, I : IMainInteractor<DictionaryResult>> :
    Fragment(R.layout.fragment_main) {
    protected var isNetworkAvailable: Boolean = false
    protected fun noInternetMessageShow() {
        Toast.makeText(requireContext(), getString(R.string.no_internet_message), Toast.LENGTH_LONG)
            .show()
    }
}

package com.android.wordtranslator.view.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.android.wordtranslator.domain.storage.entity.WordTranslate
import com.github.terrakok.cicerone.androidx.FragmentScreen

class DetailScreen(private val word: WordTranslate) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        DetailFragment.newInstance(word)
}
package com.android.wordtranslator.view.favourite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FavouriteScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        FavouriteFragment.newInstance()
}
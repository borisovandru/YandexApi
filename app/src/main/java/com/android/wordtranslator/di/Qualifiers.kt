package com.android.wordtranslator.di

import javax.inject.Qualifier

class Qualifiers {

    @Qualifier
    annotation class Local

    @Qualifier
    annotation class Remote
}
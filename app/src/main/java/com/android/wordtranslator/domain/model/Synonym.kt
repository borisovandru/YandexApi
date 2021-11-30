package com.android.wordtranslator.domain.model

import com.google.gson.annotations.SerializedName

data class Synonym(
    /**
     *  ?
     */
    @SerializedName("fr")
    val fr: Int,

    /**
     *  Род существительного
     */
    @SerializedName("gen")
    val gen: String,

    /**
     *  Часть речи
     */
    @SerializedName("pos")
    val pos: String,

    /**
     *  Текст статьи, перевода или синонима.
     */
    @SerializedName("text")
    val text: String, // словечко

    /**
     *  Число
     */
    @SerializedName("num")
    val num: String = ""
)
package com.android.wordtranslator.domain.model

import com.google.gson.annotations.SerializedName

data class Mean(
    /**
     *  Часть речи
     */
    @SerializedName("text")
    val text: String,

    /**
     *  Род существительного
     */
    @SerializedName("gen")
    val gender: String = "",

    /**
     *  Часть речи
     */
    @SerializedName("pos")
    val partOfSpeech: String = "",

    /**
     *  Число
     */
    @SerializedName("num")
    val num: String = ""
)
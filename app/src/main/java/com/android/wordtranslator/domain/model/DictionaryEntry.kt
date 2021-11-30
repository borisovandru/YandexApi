package com.android.wordtranslator.domain.model

import com.google.gson.annotations.SerializedName

data class DictionaryEntry(
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
     *  Текст статьи, перевода или синонима.
     */
    @SerializedName("text")
    val text: String = "",

    /**
     *  Список переводов
     */
    @SerializedName("tr")
    val translatesList: List<Translation> = listOf(),

    /**
     *  Транскрипция искомого слова
     */
    @SerializedName("ts")
    val transcription: String = "",

    /**
     *  Число
     */
    @SerializedName("num")
    val num: String = ""
)

package com.android.wordtranslator.domain.model

import com.google.gson.annotations.SerializedName

data class Examples(
    /**
     *  Текст статьи, перевода или синонима.
     */
    @SerializedName("text")
    val text: String,

    /**
     *  Список переводов
     */
    @SerializedName("tr")
    val translatesList: List<Translation>
) {
    override fun toString(): String {
        return text + " - " + translatesList.joinToString("\n")
    }
}
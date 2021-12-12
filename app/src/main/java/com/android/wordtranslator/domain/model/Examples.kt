package com.android.wordtranslator.domain.model

import com.google.gson.annotations.SerializedName

data class Examples(
    @SerializedName(ModelConstants.MODEL_TEXT)
    val text: String,
    @SerializedName(ModelConstants.MODEL_TRANSLATES_LIST)
    val translatesList: List<Translation>
) {
    override fun toString(): String {
        return text + " - " + translatesList.joinToString("\n")
    }
}
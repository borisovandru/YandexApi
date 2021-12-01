package com.android.wordtranslator.domain.model

import com.android.wordtranslator.domain.model.ModelConstants.MODEL_TEXT
import com.android.wordtranslator.domain.model.ModelConstants.MODEL_TRANSLATES_LIST
import com.google.gson.annotations.SerializedName

data class Examples(

    @SerializedName(MODEL_TEXT)
    val text: String,

    @SerializedName(MODEL_TRANSLATES_LIST)
    val translatesList: List<Translation>
) {
    override fun toString(): String {
        return text + " - " + translatesList.joinToString("\n")
    }
}
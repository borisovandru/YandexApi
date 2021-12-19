package com.android.model

import com.google.gson.annotations.SerializedName

data class DictionaryEntry(
    @SerializedName(ModelConstants.MODEL_GENDER)
    val gender: String = "",
    @SerializedName(ModelConstants.MODEL_PART_OF_SPEECH)
    val partOfSpeech: String = "",
    @SerializedName(ModelConstants.MODEL_TEXT)
    val text: String = "",
    @SerializedName(ModelConstants.MODEL_TRANSLATES_LIST)
    val translatesList: List<Translation> = listOf(),
    @SerializedName(ModelConstants.MODEL_TRANSCRIPTION)
    val transcription: String = "",
    @SerializedName(ModelConstants.MODEL_NUM)
    val num: String = ""
)

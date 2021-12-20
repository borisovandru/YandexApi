package com.android.model

import com.google.gson.annotations.SerializedName

data class Mean(
    @SerializedName(ModelConstants.MODEL_TEXT)
    val text: String,
    @SerializedName(ModelConstants.MODEL_GENDER)
    val gender: String = "",
    @SerializedName(ModelConstants.MODEL_PART_OF_SPEECH)
    val partOfSpeech: String = "",
    @SerializedName(ModelConstants.MODEL_NUM)
    val num: String = ""
)
package com.android.wordtranslator.domain.model

import com.android.wordtranslator.domain.model.ModelConstants.MODEL_GENDER
import com.android.wordtranslator.domain.model.ModelConstants.MODEL_NUM
import com.android.wordtranslator.domain.model.ModelConstants.MODEL_PART_OF_SPEECH
import com.android.wordtranslator.domain.model.ModelConstants.MODEL_TEXT
import com.android.wordtranslator.domain.model.ModelConstants.MODEL_TRANSCRIPTION
import com.android.wordtranslator.domain.model.ModelConstants.MODEL_TRANSLATES_LIST
import com.google.gson.annotations.SerializedName

data class DictionaryEntry(
    @SerializedName(MODEL_GENDER)
    val gender: String = "",
    @SerializedName(MODEL_PART_OF_SPEECH)
    val partOfSpeech: String = "",
    @SerializedName(MODEL_TEXT)
    val text: String = "",
    @SerializedName(MODEL_TRANSLATES_LIST)
    val translatesList: List<Translation> = listOf(),
    @SerializedName(MODEL_TRANSCRIPTION)
    val transcription: String = "",
    @SerializedName(MODEL_NUM)
    val num: String = ""
)

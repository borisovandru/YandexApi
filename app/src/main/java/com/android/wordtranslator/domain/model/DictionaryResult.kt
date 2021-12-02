package com.android.wordtranslator.domain.model

import com.android.wordtranslator.domain.model.ModelConstants.MODEL_DICTIONARY_ENTRY_LIST
import com.google.gson.annotations.SerializedName

data class DictionaryResult(
    @SerializedName(MODEL_DICTIONARY_ENTRY_LIST)
    val dictionaryEntryList: List<DictionaryEntry>
)

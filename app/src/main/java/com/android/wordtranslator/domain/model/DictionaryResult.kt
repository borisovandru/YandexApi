package com.android.wordtranslator.domain.model

import com.google.gson.annotations.SerializedName

data class DictionaryResult(
    /**
     *  Список словарных статей
     */
    @SerializedName("def")
    val dictionaryEntryList: List<DictionaryEntry>
)

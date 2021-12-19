package com.android.utils

import com.android.model.DictionaryResult
import com.android.domain.storage.entity.WordFavourite
import com.android.domain.storage.entity.WordTranslate

const val DEFAULT_IMAGE_URL =
    "https://png.pngtree.com/png-vector/20210106/ourlarge/pngtree-parts-of-speech-english-learning-png-image_2698558.jpg"

fun mapToListWordTranslate(dictionaryResult: DictionaryResult): ArrayList<WordTranslate> {
    val listTranslates: ArrayList<WordTranslate> = ArrayList()

    var mean: String = ""
    var translate: String = ""
    var synonym: String = ""
    var example: String = ""

    dictionaryResult.dictionaryEntryList.forEach { dictionaryEntry ->
        dictionaryEntry.translatesList.forEach { translation ->
            translation.meanList.forEach { meanItem ->
                mean += if (mean.isEmpty()) {
                    ""
                } else {
                    ", "
                } + meanItem.text
            }
            translate +=
                if (translate.isEmpty()) {
                    ""
                } else {
                    ", "
                } + translation.text

            translation.synonymList.forEach { synonymItem ->
                synonym += if (synonym.isEmpty()) {
                    ""
                } else {
                    ", "
                } + synonymItem.text
            }

            translation.examplesList?.forEach { exampleItem ->
                example += if (example.isEmpty()) {
                    ""
                } else {
                    ", "
                } + exampleItem.text + "(" + exampleItem.translatesList[0].text + ")"
            }
        }

        listTranslates.add(
            com.android.domain.storage.entity.WordTranslate(
                word = dictionaryEntry.text,
                translate = translate,
                mean = mean,
                transcription = dictionaryEntry.transcription,
                partOfSpeech = dictionaryEntry.partOfSpeech,
                example = example,
                synonym = synonym,
                imageURL = DEFAULT_IMAGE_URL
            )
        )
        translate = ""
        mean = ""
        synonym = ""
        example = ""
    }

    return listTranslates
}

fun mapTranslateToFavourite(word: WordTranslate): WordFavourite =
    WordFavourite(
        word = word.word,
        translate = word.translate,
        mean = word.mean,
        transcription = word.transcription,
        partOfSpeech = word.partOfSpeech,
        synonym = word.synonym,
        example = word.example,
        imageURL = word.imageURL,
        favourite = word.favourite
    )

fun mapFavouriteToTranslate(word: WordFavourite): WordTranslate =
    WordTranslate(
        word = word.word,
        translate = word.translate,
        mean = word.mean,
        transcription = word.transcription,
        partOfSpeech = word.partOfSpeech,
        synonym = word.synonym,
        example = word.example,
        imageURL = word.imageURL,
        favourite = word.favourite
    )
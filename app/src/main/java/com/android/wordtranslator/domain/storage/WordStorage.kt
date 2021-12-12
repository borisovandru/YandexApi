package com.android.wordtranslator.domain.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.wordtranslator.domain.storage.entity.WordFavourite
import com.android.wordtranslator.domain.storage.entity.WordTranslate

@Database(
    exportSchema = true,
    entities = [WordTranslate::class, WordFavourite::class],
    version = 13
)
abstract class WordStorage : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
package com.android.domain.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.domain.storage.entity.WordFavourite
import com.android.domain.storage.entity.WordTranslate

@Database(
    exportSchema = true,
    entities = [WordTranslate::class, WordFavourite::class],
    version = 13
)
abstract class WordStorage : RoomDatabase() {
    abstract fun wordDao(): com.android.domain.storage.WordDao
}
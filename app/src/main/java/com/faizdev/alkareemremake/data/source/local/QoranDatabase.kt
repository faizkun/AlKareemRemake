package com.faizdev.alkareemremake.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faizdev.alkareemremake.data.source.local.entity.Juz
import com.faizdev.alkareemremake.data.source.local.entity.Page
import com.faizdev.alkareemremake.data.source.local.entity.Quran
import com.faizdev.alkareemremake.data.source.local.entity.Surah

@Database(
    entities = [Quran::class],
    version = 1,
    views = [Surah::class, Juz::class, Page::class]
)
abstract class QoranDatabase : RoomDatabase() {
    abstract fun dao(): QuranDao
}
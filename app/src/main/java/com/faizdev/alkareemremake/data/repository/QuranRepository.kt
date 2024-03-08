package com.faizdev.alkareemremake.data.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faizdev.alkareemremake.data.source.local.entity.Bookmark
import com.faizdev.alkareemremake.data.source.local.entity.Juz
import com.faizdev.alkareemremake.data.source.local.entity.Page
import com.faizdev.alkareemremake.data.source.local.entity.Quran
import com.faizdev.alkareemremake.data.source.local.entity.Surah
import com.faizdev.alkareemremake.data.source.model.AdzanScheduleResponse
import kotlinx.coroutines.flow.Flow

interface QuranRepository   {

    fun getQuranIndexBySurah(): Flow<List<Surah>>

    fun getQuranIndexByJuz(): Flow<List<Juz>>

    fun getQuranIndexByPage(): Flow<List<Page>>

    fun readAyahBySurahNumber(soraNumber: Int): Flow<List<Quran>>

    fun readAyahByJuzNumber(soraNumber: Int): Flow<List<Quran>>

    fun readAyahByPageNumber(soraNumber: Int): Flow<List<Quran>>

    fun getBookmarkList(): Flow<List<Bookmark>>

    suspend fun insertBookmark(bookmark: Bookmark)

    suspend fun deleteBookmark(bookmark: Bookmark)

    suspend fun deleteAllBookmark()

    fun searchSurah(search: String): Flow<List<Surah>>

    fun searchEntireQuran(search: String): Flow<List<Quran>>

    suspend fun getAdzanSchedule(latitude: String, longitude: String): AdzanScheduleResponse




}
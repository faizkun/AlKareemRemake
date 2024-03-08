package com.faizdev.alkareemremake.data.repository

import com.faizdev.alkareemremake.data.source.local.BookmarkDatabase
import com.faizdev.alkareemremake.data.source.local.QoranDatabase
import com.faizdev.alkareemremake.data.source.local.entity.Bookmark
import com.faizdev.alkareemremake.data.source.local.entity.Juz
import com.faizdev.alkareemremake.data.source.local.entity.Page
import com.faizdev.alkareemremake.data.source.local.entity.Quran
import com.faizdev.alkareemremake.data.source.local.entity.Surah
import com.faizdev.alkareemremake.data.source.model.AdzanScheduleResponse
import com.faizdev.alkareemremake.data.source.service.ApiInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(
    private  val quranDatabase: QoranDatabase,
    private val api: ApiInterface,
    private val bookmarkDatabase : BookmarkDatabase
) : QuranRepository {
    override fun getQuranIndexBySurah(): Flow<List<Surah>> {
       return quranDatabase.dao().getQuranIndexBySurah()
    }

    override fun getQuranIndexByJuz(): Flow<List<Juz>> {
        return quranDatabase.dao().getQuranIndexByJuz()
    }

    override fun getQuranIndexByPage(): Flow<List<Page>> {
        return quranDatabase.dao().getQuranIndexByPage()
    }

    override fun readAyahBySurahNumber(soraNumber: Int): Flow<List<Quran>> {
        return quranDatabase.dao().readAyahBySurahNumber(soraNumber)
    }

    override fun readAyahByJuzNumber(juz: Int): Flow<List<Quran>> {
        return quranDatabase.dao().readAyahByJuzNumber(juz)
    }

    override fun readAyahByPageNumber(page: Int): Flow<List<Quran>> {
        return quranDatabase.dao().readAyahByPageNumber(page)
    }

    //Bookmark
    override fun getBookmarkList(): Flow<List<Bookmark>> {
       return bookmarkDatabase.getBookmarkDao().getBookmarkList()
    }

    override suspend fun insertBookmark(bookmark: Bookmark) {
       return bookmarkDatabase.getBookmarkDao().insertBookmark(bookmark)
    }

    override suspend fun deleteBookmark(bookmark: Bookmark) {
        return bookmarkDatabase.getBookmarkDao().deleteBookmark(bookmark)
    }

    override suspend fun deleteAllBookmark() {
       return bookmarkDatabase.getBookmarkDao().deleteAllBookmark()
    }

    override fun searchSurah(search: String): Flow<List<Surah>> {
        return quranDatabase.dao().searchSurah(search)
    }

    override fun searchEntireQuran(search: String): Flow<List<Quran>> {
        return quranDatabase.dao().searchEntireQuran(search)
    }



    override suspend fun getAdzanSchedule(
        latitude: String,
        longitude: String
    ): AdzanScheduleResponse {
        return api.getAdzanSchedule(latitude, longitude)
    }

}


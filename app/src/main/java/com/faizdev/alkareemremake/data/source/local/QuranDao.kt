package com.faizdev.alkareemremake.data.source.local

import androidx.room.Dao
import androidx.room.Query
import com.faizdev.alkareemremake.data.source.local.entity.Juz
import com.faizdev.alkareemremake.data.source.local.entity.Page
import com.faizdev.alkareemremake.data.source.local.entity.Quran
import com.faizdev.alkareemremake.data.source.local.entity.Surah
import kotlinx.coroutines.flow.Flow


@Dao
interface QuranDao {
    @Query("SELECT * FROM Surah")
    fun getQuranIndexBySurah(): Flow<List<Surah>>

    @Query("SELECT * FROM Juz")
    fun getQuranIndexByJuz(): Flow<List<Juz>>

    @Query("SELECT * FROM Page")
    fun getQuranIndexByPage(): Flow<List<Page>>

    @Query("SELECT id, sora, jozz, aya_no, aya_text, aya_text_emlaey, translation_id, translation_en, footnotes_en, footnotes_id, sora_descend_place, sora_name_en, sora_name_id  FROM quran WHERE sora = :soraNumber")
    fun readAyahBySurahNumber(soraNumber: Int) : Flow<List<Quran>>

    @Query("SELECT id, sora, jozz, aya_no, aya_text, aya_text_emlaey, translation_id, translation_en, footnotes_en, footnotes_id, sora_descend_place, sora_name_en, sora_name_id FROM quran WHERE jozz = :juz")
    fun readAyahByJuzNumber(juz: Int) : Flow<List<Quran>>

    @Query("SELECT id, sora, jozz, aya_no, aya_text, aya_text_emlaey, translation_id, translation_en, footnotes_en, footnotes_id, sora_descend_place, sora_name_en, sora_name_id, page FROM quran WHERE page = :page")
    fun readAyahByPageNumber(page: Int) : Flow<List<Quran>>

    @Query("SELECT * FROM quran where sora_name_emlaey LIKE '%' ||:search||'%' OR sora = '%' ||:search|| '%' GROUP BY sora")
    fun searchSurah(search: String): Flow<List<Surah>>

    @Query("SELECT * FROM quran where translation_id LIKE '%' ||:search||'%' OR aya_text_emlaey LIKE :search OR translation_en LIKE '%'||:search||'%'")
    fun searchEntireQuran(search: String): Flow<List<Quran>>

    @Query("SELECT * FROM quran WHERE aya_text_emlaey LIKE '%' || :ayahSurah || '%' OR aya_text_emlaey LIKE :ayahSurah OR translation_en  LIKE '%' || :ayahSurah || '%'")
    fun seacrhAyah(ayahSurah : String) : Flow<List<Quran>>




}
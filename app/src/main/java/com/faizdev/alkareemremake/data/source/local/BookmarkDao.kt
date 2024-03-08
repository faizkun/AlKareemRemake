package com.faizdev.alkareemremake.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faizdev.alkareemremake.data.source.local.entity.Bookmark
import kotlinx.coroutines.flow.Flow


@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun getBookmarkList(): Flow<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmark")
    suspend fun deleteAllBookmark()


}
package com.faizdev.alkareemremake.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bookmark")
data class Bookmark (
    @PrimaryKey(true)
    val id : Int,
    val surahName : String,
    val ayahNumber : Int,
    val surahNumber : Int,
    val ayahText : String,
    val position: Int
)
package com.faizdev.alkareemremake.data.kotpref

import com.chibatching.kotpref.KotprefModel

object LastReadPreferences: KotprefModel() {
    var lastSurahName by nullableStringPref(null)
    var lastSurahNumber by intPref(0)
    var lastPosition by intPref(0)
}
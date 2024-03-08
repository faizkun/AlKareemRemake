package com.faizdev.alkareemremake.screen.read

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.faizdev.alkareemremake.data.source.local.entity.Surah
import kotlinx.coroutines.flow.MutableStateFlow


class GlobalViewModel: ViewModel() {
    private val _totalAyah = mutableStateListOf<Int>()

    fun setTotalAyah(surahList: List <Surah>){
        surahList.forEach {
            Log.d("SURAH", it.toString())
            _totalAyah.add(it.numberOfAyah!!)
        }
    }

    fun getTotalAyah() = _totalAyah
}
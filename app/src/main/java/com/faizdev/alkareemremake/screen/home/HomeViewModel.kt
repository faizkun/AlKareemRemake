package com.faizdev.alkareemremake.screen.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizdev.alkareemremake.data.repository.QuranRepository
import com.faizdev.alkareemremake.data.source.local.entity.Juz
import com.faizdev.alkareemremake.data.source.local.entity.Page
import com.faizdev.alkareemremake.data.source.local.entity.Quran
import com.faizdev.alkareemremake.data.source.local.entity.Surah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: QuranRepository
): ViewModel() {
    

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }


    private val _searchSurahState = MutableStateFlow(emptyList<Surah>())
    val searchSurahState = _searchSurahState.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchAyahState = MutableStateFlow(emptyList<Quran>())
    val searchAyahState = _searchAyahState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _surahListState = mutableStateOf(emptyList<Surah>())
    val surahListState = _surahListState

    private val _juzListState = mutableStateOf(emptyList<Juz>())
    val juztListState = _juzListState

    private val _pageListState = mutableStateOf(emptyList<Page>())
    val pageListState = _pageListState



    fun searchResult(text: String){
        repository.searchSurah(text).onEach {listQuran ->
            _searchSurahState.emit(listQuran)
        }.launchIn(viewModelScope)
        repository.searchEntireQuran(text).onEach { listAyah ->
            _searchAyahState.emit(listAyah)
        }.launchIn(viewModelScope)
    }
    


    private var getSurahJob: Job? = null
    private var getJuzJob: Job? = null
    private var getPageJob: Job? = null

    private fun getAllQuranIndex(){
        getSurahJob?.cancel()
        getJuzJob?.cancel()
        getPageJob?.cancel()

        getSurahJob = repository.getQuranIndexBySurah().onEach { list ->
            Log.d("Check",list.toString())
            _surahListState.value = list
        }.launchIn(viewModelScope)

        getJuzJob = repository.getQuranIndexByJuz().onEach { list ->
            Log.d("Check",list.toString())
            _juzListState.value = list
        }.launchIn(viewModelScope)

        getPageJob = repository.getQuranIndexByPage().onEach { list ->
            Log.d("Check",list.toString())
            _pageListState.value = list
        }.launchIn(viewModelScope)


    }

    init {
        getAllQuranIndex()
    }
}


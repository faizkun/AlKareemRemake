package com.faizdev.alkareemremake.screen.readsurah

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizdev.alkareemremake.data.kotpref.SettingPreference
import com.faizdev.alkareemremake.data.repository.QuranRepository
import com.faizdev.alkareemremake.data.source.local.entity.Bookmark
import com.faizdev.alkareemremake.data.source.local.entity.Quran
import com.faizdev.alkareemremake.navArgs
import com.faizdev.alkareemremake.screen.setting.SettingScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import snow.player.PlayerClient
import snow.player.audio.MusicItem
import snow.player.playlist.Playlist
import javax.inject.Inject


@HiltViewModel
class SurahViewModel @Inject constructor(
    val repository: QuranRepository,
    savedStateHandle: SavedStateHandle,
    private val playerClient: PlayerClient

) : ViewModel() {



    val isPlayMode = mutableStateOf(PlayMode.NOT_PLAYING)

    var qoran = mutableStateOf<Quran?>(null)

    val navArgs: ReadArguments = savedStateHandle.navArgs()

    private val _ayahList = MutableStateFlow<List<Quran>>(emptyList())
    val ayahList = _ayahList
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), emptyList())

    fun addBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            repository.insertBookmark(bookmark)
        }
    }

    val position = mutableStateOf(
        navArgs.position
    )


    private val _currentRead = mutableStateOf("")
    val currentRead = _currentRead


    fun load() {


        when (navArgs.readType) {
            0 -> { // Surah
                repository.readAyahBySurahNumber(navArgs.surahNumber ?: 0).onEach { list ->
                    _ayahList.emit(list)
                    currentRead.value = list[0].surahNameEn.toString()

                }.launchIn(viewModelScope)
            }

            1 -> { //Juz
                repository.readAyahByJuzNumber(navArgs.juzNumber ?: 0).onEach { list ->
                    _ayahList.emit(list)
                    _currentRead.value = list[0].juzNumber.toString()

                }.launchIn(viewModelScope)

            }

            2 -> { //Page

                repository.readAyahByPageNumber(navArgs.pageNumber ?: 0).onEach {
                    _ayahList.emit(it)
                    _currentRead.value = "Halaman ${it[0].page}"
                }.launchIn(viewModelScope)

            }

            else -> {}
        }


    }

    init {
        load()
    }

    fun onEvent(event: SurahScreenEvent) {
        when (event) {
            is SurahScreenEvent.PlayAyah -> {
                playerClient.stop()
                playerClient.connect {
                    val playlist = createOneTimePlaylist(event.quran)
                    playerClient.setPlaylist(playlist, true)
                    isPlayMode.value = PlayMode.IS_PLAYING
                    qoran.value = event.quran
                }
            }

            is SurahScreenEvent.NextAyah -> {
                playerClient.skipToNext()

            }

            is SurahScreenEvent.PauseAyah -> {
                playerClient.playPause()
                if (isPlayMode.value == PlayMode.IS_PAUSED){
                    isPlayMode.value = PlayMode.IS_PLAYING
                } else {
                    PlayMode.IS_PAUSED
                }

            }

            is SurahScreenEvent.PlayAllAyah -> {
                playerClient.stop()
                playerClient.connect{
                    val playlist = createAllPlayList(event.quranList)
                    playerClient.setPlaylist(playlist, true)
                    isPlayMode.value = PlayMode.IS_PLAYING
                    isPlayMode.value = PlayMode.PLAY_LOOP
                    playerClient.addOnPlaylistChangeListener{_, position ->
                        qoran.value = event.quranList[position]
                    }
                }

            }

            is SurahScreenEvent.PrevAyah -> {
                playerClient.skipToPrevious()
            }

            is SurahScreenEvent.StopAyah -> {
                playerClient.stop()
                isPlayMode.value = PlayMode.NOT_PLAYING
            }
        }
    }

    private fun createAllPlayList(
        quranList: List<Quran>
    ): Playlist {

        val musicItemList = mutableListOf<MusicItem>()

        quranList.forEach { quran ->
            val formattedSurahNumber = String.format("%03d", quran.surahNumber)
            val formattedAyahNumber = String.format("%03d", quran.ayahNumber)

            musicItemList.add(
                MusicItem.Builder()
                    .setTitle("${quran.surahNameEn} - ${quran.surahNumber}")
                    .setArtist("Mishary Alafasy")
                    .setUri("https://everyayah.com/data/Alafasy_128kbps/${SettingPreference.listQari[SettingPreference.currentQari].qariId}/$formattedAyahNumber.mp3")
                    .setIconUri("https://lyricstranslate.com/files/styles/artist/public/5_6.jpg")
                    .autoDuration()
                    .build()
            )

        }

        return Playlist.Builder()
            .appendAll(musicItemList)
            .build()

    }


    private fun createOneTimePlaylist(
       quran: Quran
    ): Playlist {

        val formattedSurahNumber = String.format("%03d", quran.surahNumber)
        val formattedAyahNumber = String.format("%03d", quran.ayahNumber)

        return Playlist.Builder()
            .append(
                MusicItem.Builder()
                    .setTitle("${quran.surahNameEn} - ${quran.surahNumber}")
                    .setArtist("Mishary Alafasy")
                    .setUri("https://everyayah.com/data/Alafasy_128kbps/$formattedSurahNumber$formattedAyahNumber.mp3")
                    .setIconUri("https://lyricstranslate.com/files/styles/artist/public/5_6.jpg")
                    .autoDuration()
                    .build()
            )
            .build()
    }

}

sealed class SurahScreenEvent {

    data class PlayAyah(val quran: Quran) : SurahScreenEvent()
    data class PlayAllAyah(val quranList: List<Quran>) :
        SurahScreenEvent()

    object PauseAyah : SurahScreenEvent()
    object NextAyah : SurahScreenEvent()
    object PrevAyah : SurahScreenEvent()
    object StopAyah : SurahScreenEvent()


}

enum class PlayMode {
    IS_PLAYING,
    NOT_PLAYING,
    IS_PAUSED,
    PLAY_LOOP

}
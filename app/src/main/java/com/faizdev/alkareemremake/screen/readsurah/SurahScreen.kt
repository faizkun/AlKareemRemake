package com.faizdev.alkareemremake.screen.readsurah

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.faizdev.alkareemremake.component.PlayAyahControlPanel
import com.faizdev.alkareemremake.data.kotpref.LastReadPreferences
import com.faizdev.alkareemremake.data.kotpref.SettingPreference
import com.faizdev.alkareemremake.data.source.local.entity.Bookmark
import com.faizdev.alkareemremake.data.source.local.entity.Quran
import com.faizdev.alkareemremake.item.AyatItem
import com.faizdev.alkareemremake.screen.read.GlobalViewModel
import com.faizdev.alkareemremake.ui.theme.poppins
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = ReadArguments::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahScreen(
    viewModel: SurahViewModel = hiltViewModel(),
    globalViewModel: GlobalViewModel,
    navigator: DestinationsNavigator,


    ) {

    val playMode = viewModel.isPlayMode
    val surahName by viewModel.currentRead
    val context = LocalContext.current
    val ayahList by viewModel.ayahList.collectAsStateWithLifecycle()
    val totalAyahList = remember {
        globalViewModel.getTotalAyah()
    }


    val position = viewModel.position.value ?: 0
    val lazyColumnState = rememberLazyListState()
    LaunchedEffect(key1 = true) {
        lazyColumnState.scrollToItem(position)
    }


    Scaffold(

        bottomBar = {
            if (playMode.value == PlayMode.IS_PLAYING || playMode.value == PlayMode.IS_PAUSED) {
                PlayAyahControlPanel(
                    surahName = viewModel.qoran.value?.surahNameEn!!,
                    qoriName = "Mishary Rashid Alafasy",
                    ayahNumber = viewModel.qoran.value?.ayahNumber!!,
                    onSkipPrev = {
                        viewModel.onEvent(SurahScreenEvent.PrevAyah)
                    },

                    onSkipNext = {
                        viewModel.onEvent(SurahScreenEvent.NextAyah)
                    },
                    onStop = {
                        viewModel.onEvent(SurahScreenEvent.StopAyah)
                    },
                    onPause = {
                        viewModel.onEvent(SurahScreenEvent.PauseAyah)
                    },
                    isPause = playMode.value == PlayMode.IS_PAUSED
                )


            }

        },

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.navigateUp()
                    }) {

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            modifier = Modifier.size(55.dp)

                        )

                    }
                },

                title = {
                    Text(
                        text = surahName,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp


                    )

                })
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {

            LazyColumn(
                state = lazyColumnState,
                content = {
                    items(ayahList.size) { index ->
                        val ayat = ayahList[index]

                        LastReadPreferences.lastSurahNumber = ayat.surahNumber!!
                        LastReadPreferences.lastSurahName = ayat.surahNameEn
                        LastReadPreferences.lastPosition


                        AyatItem(
                            no = ayat.ayahNumber ?: 0,
                            surahName = ayat.surahNameEn ?: "",
                            surahInd = ayat.surahNameId ?: "",
                            place = ayat.surahDescentPlace ?: "",
                            ayahTotal = totalAyahList[ayat.surahNumber!! - 1],
                            surahArabic = ayat.ayahText ?: "",
                            surahTranslate = ayat.translation_id ?: "",
                            translate = if (SettingPreference.currentLanguage == SettingPreference.INDONESIA) ayat.translation_id!! else ayat.translation_en!!,
                            insert = {
                                viewModel.addBookmark(
                                    Bookmark(
                                        id = ayat.id!!,
                                        surahName = ayat.surahNameEn!!,
                                        ayahNumber = ayat.ayahNumber!!,
                                        ayahText = ayat.ayahText!!,
                                        position = index,
                                        surahNumber = ayat.surahNumber!!,


                                        )
                                )
                                Toast.makeText(context, "Ditambahkan", Toast.LENGTH_SHORT).show()

                            },
                            onClickPlayAyah = {
                                viewModel.onEvent(
                                    SurahScreenEvent.PlayAyah(
                                        quran = ayat
                                    )
                                )
                            }
                        )


                    }
                })


        }


    }
}


data class ReadArguments(
    val readType: Int = 0,
    val surahNumber: Int? = null,
    val pageNumber: Int? = null,
    val juzNumber: Int? = null,
    val position: Int? = null
)


//@Preview
//@Composable
//fun SurahScreenPv() {
//    SurahScreen()
//}
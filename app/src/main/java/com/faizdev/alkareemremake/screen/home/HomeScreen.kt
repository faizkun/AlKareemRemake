package com.faizdev.alkareemremake.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faizdev.alkareemremake.R
import com.faizdev.alkareemremake.TabItem
import com.faizdev.alkareemremake.data.kotpref.LastReadPreferences
import com.faizdev.alkareemremake.destinations.AdzanScheduleScreenDestination
import com.faizdev.alkareemremake.destinations.BookmarkScreenDestination
import com.faizdev.alkareemremake.destinations.FindQiblaScreenDestination
import com.faizdev.alkareemremake.destinations.SettingScreenDestination
import com.faizdev.alkareemremake.destinations.SurahScreenDestination
import com.faizdev.alkareemremake.item.JuzItem
import com.faizdev.alkareemremake.item.PageItem
import com.faizdev.alkareemremake.item.QuranItem
import com.faizdev.alkareemremake.item.SearchAyahItem
import com.faizdev.alkareemremake.screen.read.GlobalViewModel
import com.faizdev.alkareemremake.screen.readsurah.ReadArguments
import com.faizdev.alkareemremake.screen.setting.SettingScreen
import com.faizdev.alkareemremake.ui.theme.poppins
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@RootNavGraph(true)
@Destination
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    globalViewModel: GlobalViewModel
) {


    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val surahList = viewModel.surahListState.value
    val juzList = viewModel.juztListState.value
    val pageList = viewModel.pageListState.value
    val searchSurahList = viewModel.searchSurahState.collectAsState().value
    val searchAyahList = viewModel.searchAyahState.collectAsState().value


    LaunchedEffect(surahList) {
        globalViewModel.setTotalAyah(surahList)
    }

    LaunchedEffect(searchText){
        viewModel.searchResult(searchText)
    }





    val tabItems = listOf(
        TabItem(
            title = "Surah",


            ),
        TabItem(
            title = "Juz",

            ),
        TabItem(
            title = "Page",

            ),
    )

    val pagerState = rememberPagerState {
        tabItems.size
    }


//    val surahList = viewModel.surahListState.value
//    val juzList = viewModel.justListState.value
//    val pageList = viewModel.pageListState.value


    Scaffold(

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    Color.White
                ),
                title = {
                    Text(
                        text = "AlKareem",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins,
                        fontSize = 18.sp
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navigator.navigate(AdzanScheduleScreenDestination())
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccessTimeFilled,
                            contentDescription = "",


                            )


                    }

                    IconButton(onClick = {
                        navigator.navigate(FindQiblaScreenDestination())
                    }) {

                        Icon(
                            imageVector = Icons.Default.Explore,
                            contentDescription = ""
                        )

                    }

                    IconButton(onClick = {
                        navigator.navigate(BookmarkScreenDestination())
                    }) {

                        Icon(
                            painter = painterResource(id = R.drawable.baseline_bookmark_24),
                            contentDescription = ""
                        )

                    }


                    IconButton(onClick = {
                        navigator.navigate(SettingScreenDestination())
                    }) {

                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = ""
                        )

                    }
                }

            )
        }


    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            SearchBar(
                query = searchText,
                onQueryChange = {
                    viewModel.onSearchTextChange(it)
                },
                onSearch = {
                    viewModel.onSearchTextChange(it)
                },
                placeholder = { Text("Cari Surah atau Ayat") },
                active = isSearching,
                onActiveChange = { viewModel.onToogleSearch() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp,)
                   ,

                leadingIcon = {
                    IconButton(onClick = {

                    }) {

                        Icon(imageVector = Icons.Rounded.Search, contentDescription = "")
                    }
                }
            ) {
                LazyColumn(modifier = Modifier) {
                    items(searchSurahList) { surah ->
                        QuranItem(
                            no = surah.surahNumber ?: 0,
                            ayahNum = surah.numberOfAyah ?: 0,
                            surahName = surah.surahNameEn ?: "",
                            surahCategory = surah.turunSurah ?: "",
                            surahNameAr = surah.surahNameArabic ?: "",
                            onClick = { surahNumber ->
                                navigator.navigate(
                                    SurahScreenDestination(
                                        ReadArguments(
                                            readType = pagerState.currentPage,
                                            surahNumber = surahNumber,
                                            position = 0
                                        )
                                    )
                                )
                            }
                        )
                    }

                    items(searchAyahList){ item ->
                        SearchAyahItem(
                            surahArabic = item.ayahText ?: "",
                            surahTranslate = item.translation_id ?: "",
                            onClick = {
                                navigator.navigate(
                                    SurahScreenDestination(
                                        ReadArguments(
                                            readType = 0,
                                            surahNumber = item.surahNumber,
                                            pageNumber = null,
                                            juzNumber = null,
                                            position = item.ayahNumber!!.minus(1)
                                        )
                                    )
                                )
                            }
                        )
                    }
                }

            }


            val scope = rememberCoroutineScope()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp),

                shape = RoundedCornerShape(35.dp),
                onClick = {
                    navigator.navigate(
                        SurahScreenDestination(
                            ReadArguments(
                                readType = 0,
                                surahNumber = LastReadPreferences.lastSurahNumber,
                                pageNumber = null,
                                juzNumber = null,
                                position = LastReadPreferences.lastPosition
                            )
                        )
                    )
                },
                colors = CardDefaults.cardColors(containerColor = Color(0xff47629E))

            ) {

                Column(
                    modifier = Modifier.padding(start = 18.dp, top = 18.dp)

                ) {

                    Text(
                        text = "Terakhir dibaca",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = poppins
                    )

                    Text(
                        text = LastReadPreferences.lastSurahName ?: "Belum Membaca",
                        color = Color.White,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 23.sp

                    )

                    Text(
                        text = "Ayat ${LastReadPreferences.lastSurahNumber}",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = poppins
                    )

                }

            }




            Spacer(modifier = Modifier.size(18.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        color = Color(0XFF89ADFF),
                        shape = RoundedCornerShape(topEnd = 35.dp, topStart = 35.dp)
                    ),
            ) {

                TabRow(selectedTabIndex = pagerState.currentPage) {
                    tabItems.forEachIndexed { index, item ->
                        Tab(
                            selected = index == pagerState.currentPage,
                            onClick = {
                                scope.launch {
                                    pagerState.scrollToPage(index)
                                }

                            },
                            text = {
                                Text(text = item.title)
                            }
                        )
                    }


                }

                HorizontalPager(state = pagerState) { page ->
                    when (page) {
                        0 -> {
                            LazyColumn(modifier = Modifier) {
                                items(surahList.size) { index ->
                                    val surah = surahList[index]
                                    QuranItem(
                                        no = surah.surahNumber ?: 0,
                                        ayahNum = surah.numberOfAyah ?: 0,
                                        surahName = surah.surahNameEn ?: "",
                                        surahCategory = surah.turunSurah ?: "",
                                        surahNameAr = surah.surahNameArabic ?: "",
                                        onClick = { surahNumber ->
                                            navigator.navigate(
                                                SurahScreenDestination(
                                                    ReadArguments(
                                                        readType = pagerState.currentPage,
                                                        surahNumber = surahNumber,
                                                        position = 0
                                                    )
                                                )
                                            )
                                        }
                                    )
                                }
                            }

                        }

                        1 -> {
                            LazyColumn(modifier = Modifier) {
                                items(juzList) { juz ->
                                    JuzItem(
                                        no = juz.juzNumber ?: 0,
                                        juz = juz.juzNumber ?: 0,
                                        surah = juz.SurahName_en ?: "",
                                        ayat = juz.nomorAyah ?: 0,
                                        onClick = {
                                            navigator.navigate(
                                                SurahScreenDestination(
                                                    ReadArguments(
                                                        readType = pagerState.currentPage,
                                                        juzNumber = juz.juzNumber
                                                    )
                                                )
                                            )

                                        }
                                    )
                                }
                            }

                        }

                        2 -> {
                            LazyColumn(modifier = Modifier) {
                                items(pageList) { page ->
                                    PageItem(
                                        no = page.pageNumber ?: 0,
                                        page = page.pageNumber ?: 0,
                                        surah = page.SurahName_en ?: "",
                                        ayat = page.AyahNumber.toString(),
                                        onClick = {
                                            navigator.navigate(
                                                SurahScreenDestination(
                                                    ReadArguments(
                                                        readType = pagerState.currentPage,
                                                        pageNumber = page.pageNumber
                                                    )
                                                )
                                            )

                                        }
                                    )
                                }
                            }
                        }

                    }
                }


            }


        }
    }
}

@Preview
@Composable
fun HomeScreenPv() {
//    HomeScreen()

}

data class TabItem(
    val title: String,
//    val unselectedIcon: ImageVector,
//    val selectedIcon: ImageVector,
)

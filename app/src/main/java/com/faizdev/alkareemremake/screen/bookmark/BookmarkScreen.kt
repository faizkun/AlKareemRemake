package com.faizdev.alkareemremake.screen.bookmark

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.faizdev.alkareemremake.destinations.SurahScreenDestination
import com.faizdev.alkareemremake.item.BookmarkItem
import com.faizdev.alkareemremake.screen.readsurah.ReadArguments
import com.faizdev.alkareemremake.ui.theme.poppins
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun BookmarkScreen(
    navigator: DestinationsNavigator,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {

    val bookmarkList = viewModel.bookmarkState.collectAsStateWithLifecycle().value
    val context = LocalContext.current



    Scaffold(

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
                        text = "Bookmark",
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
                .fillMaxSize()
        ) {

            LazyColumn {
                items(bookmarkList) { bookmark ->
                    BookmarkItem(
                        ayah = bookmark.ayahNumber,
                        surahName = bookmark.surahName,
                        delete = {
                            viewModel.deleteBookmark(
                                bookmark
                            )
                            Toast.makeText(context, "Terhapus", Toast.LENGTH_SHORT).show()
                        },
                        onClick = {
                            navigator.navigate(
                                SurahScreenDestination(
                                    ReadArguments(
                                        readType = 0,
                                        surahNumber = bookmark.surahNumber,
                                        pageNumber = null,
                                        juzNumber = null,
                                        position = bookmark.position,
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


@Preview
@Composable
fun BookmarkScreenPv() {

}
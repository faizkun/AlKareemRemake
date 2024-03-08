package com.faizdev.alkareemremake.item

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.faizdev.alkareemremake.R
import com.faizdev.alkareemremake.ui.theme.poppins
import com.faizdev.alkareemremake.ui.theme.uthmanic

@Composable
fun AyatItem(

    no: Int,
    surahName: String,
    surahInd: String,
    place: String,
    ayahTotal: Int,
    surahArabic: String,
    surahTranslate: String,
    insert : () -> Unit,
    onClickPlayAyah : () -> Unit,
    translate : String,

) {

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Column {
        if (no == 1){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(186.dp)
                    .padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(35.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0XFF47629E)),
            ) {

                Spacer(modifier = Modifier.size(19.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        text = "${surahName}",
                        color = Color.White,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 21.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)


                    )

                    Text(
                        text = "${surahInd}",
                        color = Color.White,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)


                    )

                    Divider(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .padding(horizontal = 27.dp),
                    )

                    Text(
                        text = "${place} | ${ayahTotal} Ayat",
                        color = Color.White,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 12.dp),


                        )

                    Text(
                        text = "بِسْمِ اللّهِ الرَّحْمَنِ الرَّحِيْمِ",
                        color = Color.White,
                        fontFamily = uthmanic,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 12.dp),


                        )


                }


            }
        }

        Spacer(modifier = Modifier.height(14.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp)
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0XFFC9D1E3))

        ) {

            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        imageVector = Icons.Default.Circle,
                        contentDescription = null,
                        tint = Color(0XFF47629E)

                    )

                    Text(

                        text = "${no}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Color.White


                    )


                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    IconButton(onClick = {

                        clipboardManager.setText(
                            AnnotatedString(
                                "${no}/n${surahArabic}/n${surahTranslate}"
                            )
                        )
                        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()


                    }) {

                        Icon(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(18.dp),
                            imageVector = Icons.Default.ContentCopy, contentDescription = null
                        )

                    }

                    IconButton(onClick = {
                        onClickPlayAyah()
                    }) {

                        Icon(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(27.dp),

                            imageVector = Icons.Default.PlayArrow, contentDescription = null
                        )


                    }

                    IconButton(
                        onClick = {

                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "(${no})  ${surahArabic}\n${surahTranslate}"
                                )
                                type = "text/plain"

                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }

                    ) {

                        Icon(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(18.dp),
                            imageVector = Icons.Default.Share, contentDescription = "share_button"
                        )


                    }

                    IconButton(onClick = {
                        insert()
                    }) {


                        Icon(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(18.dp),
                            imageVector = Icons.Default.Bookmark, contentDescription = null
                        )


                    }

                }


            }


        }

        Spacer(modifier = Modifier.height(14.dp))
        Text(
            textAlign = TextAlign.End,
            text = "${surahArabic}",
            fontSize = 24.sp,
            fontFamily = uthmanic,
            modifier = Modifier
                .align(Alignment.End)
                .padding(17.dp)
                .fillMaxWidth(),
        )


        Text(
            textAlign = TextAlign.Justify,
            text = "${surahTranslate}",
            fontFamily = poppins,
            fontSize = 12.sp,
            modifier = Modifier

                .padding(top = 26.dp, start = 16.dp, end = 16.dp, bottom = 23.dp),


            )

//        Divider(
//            Modifier
//                .padding(top = 14.dp)
//                .padding(horizontal = 16.dp),
//            color = Color.Black
//        )


    }

}


//@Preview
//@Composable
//fun AyatItemPrev() {
//    AyatItem()
//}
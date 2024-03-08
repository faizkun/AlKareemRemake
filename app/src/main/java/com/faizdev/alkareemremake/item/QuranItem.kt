package com.faizdev.alkareemremake.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faizdev.alkareemremake.R
import com.faizdev.alkareemremake.ui.theme.poppins
import com.faizdev.alkareemremake.ui.theme.uthmanic
import com.ramcosta.composedestinations.annotation.Destination


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun QuranItem(no : Int, surahName: String, surahCategory: String, ayahNum: Int, surahNameAr: String, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 16.dp, end = 16.dp, top = 13.dp),
        shape = RoundedCornerShape(21.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0XFF47629E)),
        onClick = {
            onClick(no)
        }

    ) {

        Row(
            modifier = Modifier.padding(top = 15.dp, start = 11.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.number_background),
                    contentDescription = ""


                )

                Text(

                    text = "${no}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 7.8.sp,
                    


                )
            }

            Row(

                modifier = Modifier.padding(start = 19.dp)
            ) {
                Column() {
                    Text(
                        text = "${surahName}",
                        color = Color.White,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 19.sp

                    )

                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "${surahCategory} | ${ayahNum} Ayat",
                        color = Color.White,
                        fontFamily = poppins,
                        fontSize = 12.sp

                    )
                }


                Text(
                    text = "${surahNameAr}",
                    color = Color.White,
                    fontSize = 19.sp,
                    fontFamily = uthmanic,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .padding(end = 15.dp),


                    )
            }


        }


    }


}


//@Preview
//@Composable
//fun QuranItemPv() {
//    QuranItem()
//}
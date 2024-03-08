package com.faizdev.alkareemremake.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faizdev.alkareemremake.R
import com.faizdev.alkareemremake.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuzItem(no : Int, juz: Int, surah: String, ayat: Int, onClick:  () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 16.dp, end = 16.dp, top = 13.dp),

        shape = RoundedCornerShape(21.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0XFF47629E)),
        onClick = {
            onClick()
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
                    fontSize = 9.sp,



                    )
            }

            Row(

                modifier = Modifier.padding(start = 19.dp)
            ) {
                Column() {
                    Text(
                        text = "Juz ${juz}",
                        color = Color.White,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 19.sp

                    )

                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "${surah} | Ayat ${ayat} ",
                        color = Color.White,
                        fontFamily = poppins,
                        fontSize = 12.sp

                    )
                }



            }


        }


    }


}
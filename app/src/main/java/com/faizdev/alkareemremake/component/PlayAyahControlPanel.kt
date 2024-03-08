package com.faizdev.alkareemremake.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faizdev.alkareemremake.screen.readsurah.PlayMode
import com.faizdev.alkareemremake.ui.theme.poppins

@Composable
fun PlayAyahControlPanel(
    surahName: String,
    qoriName: String,
    ayahNumber: Int,
    onStop: () -> Unit,
    onPause: () -> Unit,
    isPause: Boolean ,
    onSkipNext : () -> Unit,
    onSkipPrev : () -> Unit,


    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(start = 14.dp, end = 16.dp, bottom = 14.dp),
        shape = RoundedCornerShape(21.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0XFF47629E)),

        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxSize()
                 ,

            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 12.dp)
                    ,
                    text = "${surahName} : ${ayahNumber}",
                    fontFamily = poppins,
                    fontSize = 15.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                    ,
                    text = "${qoriName}",
                    fontFamily = poppins,
                    fontSize = 11.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Thin
                )

            }

            Row(

                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 14.dp),

            ) {
                IconButton(onClick = { onSkipPrev() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Default.SkipPrevious, contentDescription = "" )
                }

                IconButton(onClick = { onPause() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = if (isPause) Icons.Default.Pause else  Icons.Default.PlayArrow, contentDescription = "" )
                }

                IconButton(onClick = { onSkipNext() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Default.SkipNext, contentDescription = "" )
                }

                IconButton(onClick = { onStop() }) {
                    Icon(
                        tint = Color.Red,
                        imageVector = Icons.Default.Close, contentDescription = "" )
                }




            }

        }
    }
}


//@Preview
//@Composable
//fun PlayAyahPv() {
//    PlayAyahControlPanel()
//}

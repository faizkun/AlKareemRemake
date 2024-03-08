package com.faizdev.alkareemremake.item

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faizdev.alkareemremake.R
import com.faizdev.alkareemremake.ui.theme.poppins
import androidx.compose.material3.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkItem(surahName : String, ayah : Int, onClick : (Int) -> Unit, delete : () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 16.dp, end = 16.dp, top = 13.dp),
        shape = RoundedCornerShape(21.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0XFF47629E)),
        onClick = {
            onClick(
                ayah -1
            )
        }


        ) {

        Row(
            modifier = Modifier
                .padding(start = 11.dp)
                .fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {


                    Text(
                        text = "${surahName} : ${ayah}",
                        color = Color.White,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),


                        )

                IconButton(onClick = {
                    delete()
                }) {

                    Icon(

                        tint = Color.Red,
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete"
                    )

                }



        }


    }
}


//@Preview
//@Composable
//fun BookmarkItemPv() {
//    BookmarkItem()
//}
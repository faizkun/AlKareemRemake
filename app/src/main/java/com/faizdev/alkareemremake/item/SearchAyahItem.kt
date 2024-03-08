package com.faizdev.alkareemremake.item




import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faizdev.alkareemremake.ui.theme.poppins
import com.faizdev.alkareemremake.ui.theme.uthmanic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAyahItem(
   surahArabic : String,
   surahTranslate : String,
   onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(186.dp)
            .padding(start = 16.dp, end = 16.dp)
            ,
        shape = RoundedCornerShape(35.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0XFF47629E)),
        onClick = {onClick},

    ) {

        Text(
            textAlign = TextAlign.End,
            text = "${surahArabic}",
            fontSize = 24.sp,
            fontFamily = uthmanic,
            modifier = Modifier
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

    }





}
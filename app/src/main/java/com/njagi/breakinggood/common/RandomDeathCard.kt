package com.njagi.breakinggood.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.base.R
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.njagi.breakinggood.models.DeathsItem

@Composable
fun RandomDeathCard(deathsItem: DeathsItem) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp), shape = RoundedCornerShape(1.dp),
        backgroundColor = Color.LightGray,
        border = BorderStroke(1.dp, Color.Black)
    ) {


        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = deathsItem.img)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.notification_template_icon_bg)
                    }).build()

            ), contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ){
            Column(modifier = Modifier.padding(4.dp)) {

                Text(text = deathsItem.death,fontSize = 15.sp, color = Color.Red, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "lastwords:"+" "+" "+deathsItem.lastWords,fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Red,
                    fontStyle = FontStyle.Italic)
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = deathsItem.cause,fontSize = 14.sp, color = Color.Red, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Responsible:"+" "+deathsItem.responsible, fontSize = 12.sp, color = Color.Red,fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic )
            }
        }
    }
}
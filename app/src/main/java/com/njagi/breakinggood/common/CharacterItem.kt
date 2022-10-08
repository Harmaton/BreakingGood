package com.njagi.breakinggood.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.base.R
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.njagi.breakinggood.models.CharactersItem

@Composable
fun BadCharacters(characters: ArrayList<CharactersItem>) {

//    val state1 = rememberLazyGridState(0)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
//            state = state1
    ) {
        items(characters.size) {
            ItemDisplay(charactersItem = characters[it])
        }
    }
}


@Composable
fun ItemDisplay(charactersItem: CharactersItem) {
    // character item
    Card(elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .background(Color.White)
            .clickable { }
            .padding(4.dp),
        border = BorderStroke(1.dp, color = Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = charactersItem.img)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.notification_template_icon_bg)
                        }).build()

                ), contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = charactersItem.nickname,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = charactersItem.portrayed,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = charactersItem.status,
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )

            Text(text = charactersItem.category,
                maxLines = 2,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = Color.Gray

            )

        }
    }
}

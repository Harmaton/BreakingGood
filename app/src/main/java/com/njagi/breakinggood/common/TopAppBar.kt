package com.njagi.breakinggood.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppHead() {

    TopAppBar(
        contentPadding = PaddingValues(10.dp),
        backgroundColor = Color.Transparent
    )
    {

        Text(
            text = "Breaking Good",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            softWrap = true,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.padding(12.dp),
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.width(65.dp))

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "notify new", tint = Color.Black
            )

        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "refresh",
                tint = MaterialTheme.colors.onSecondary
            )


        }


    }
}


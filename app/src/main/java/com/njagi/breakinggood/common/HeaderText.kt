package com.njagi.breakinggood.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(text: String) {
    Row(modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 2.dp)
    , horizontalArrangement = Arrangement.Start
        ) {
        Text(text = text, color = Color.Black, fontSize = 20.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.SemiBold)
    }
}
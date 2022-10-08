package com.njagi.breakinggood.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loading(text: String) {

    Column(modifier = Modifier.padding(150.dp)) {
        CircularProgressIndicator(
            progress = 0.45f,
            color = Color.Black,
            modifier = Modifier.size(30.dp)
        )

        Row(modifier = Modifier.fillMaxSize()) {
            Text(text = text, color = Color.Green)
        }
    }

}
package com.njagi.breakinggood.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Error(text: String) {

    Column(modifier = Modifier.padding(150.dp)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Error",
                tint = Color.Red,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .size(4.dp)
            )

            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp),
                text = text,
                color = Color.Red
            )
        }
    }
}
package com.njagi.breakinggood.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String,val title: String, val icon: ImageVector) {

    object Home: Screens(
        "Home",
        "Home",
        Icons.Default.Home
    )
    object Deaths: Screens(
        "Deaths",
        "Deaths",
        Icons.Default.Info
    )

    object Quotes: Screens(
        "Quotes",
        "Quotes",
        Icons.Default.MailOutline
    )
}
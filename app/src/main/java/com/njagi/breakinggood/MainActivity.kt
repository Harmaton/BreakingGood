package com.njagi.breakinggood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.njagi.breakinggood.common.AppHead
import com.njagi.breakinggood.presentation.HomeScreen
import com.njagi.breakinggood.screens.CharacterDetails
import com.njagi.breakinggood.ui.theme.BreakingGoodTheme
import com.ramcosta.composedestinations.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingGoodTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {

                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top) {
                        AppHead()
                        Spacer(modifier = Modifier.height(8.dp))
                        HomeScreen()
                    }


                }


            }
        }
    }
}


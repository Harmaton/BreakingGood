package com.njagi.breakinggood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.njagi.breakinggood.presentation.HomeScreen
import com.njagi.breakinggood.ui.theme.BreakingGoodTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingGoodTheme {

                val navController = rememberNavController()
//                val navHostEngine = rememberNavHostEngine()
//                val newBackStackEntry by navController.currentBackStackEntryAsState()
//                val route = newBackStackEntry?.destination?.route

                Surface(modifier = Modifier.fillMaxSize()) {

//                    DestinationsNavHost(navGraph = NavGraphs.root,
//                        navController = navController)
                }
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top) {

                        HomeScreen()
                }


            }
        }
    }
}


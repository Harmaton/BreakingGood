package com.njagi.breakinggood.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.njagi.breakinggood.screens.Screens

//@Composable
//fun CustomBottomBar(
//    navController: NavController,
//    showBottomBar: Boolean = true,
//    items: List<Screens> = listOf(
//        Screens.Home,
//        Screens.Quotes,
//        Screens.Deaths
//    ),
//) {
//    Scaffold(
//        bottomBar = {
//            if (showBottomBar) {
//                BottomNavigation(
//                    backgroundColor = Color.Transparent,
//                    elevation = 5.dp
//                ) {
//                    val navBackStackEntry by navController.currentBackStackEntryAsState()
//                    val currentDestination = navBackStackEntry?.destination
//                    items.forEach { item ->
//                        BottomNavigationItem(
//                            icon = {
//                               Icon(imageVector = item.icon, contentDescription = "screen icon")
//                            },
//                            selectedContentColor = Color.Blue,
//                            unselectedContentColor = Color.Gray,
//                            selected = currentDestination?.route?.contains(item.route) == true,
//                            onClick = {
//                                navController.navigate(item.route) {
//                                    navController.graph.startDestinationRoute?.let { screen_route ->
//                                        popUpTo(screen_route) {
//                                            saveState = true
//                                        }
//                                    }
//                                    launchSingleTop = true
//                                    restoreState = true
//                                }
//                            }
//                        )
//                    }
//                }
//            }
//        }
//    )
//}
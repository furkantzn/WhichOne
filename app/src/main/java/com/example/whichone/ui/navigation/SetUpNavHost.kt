package com.example.whichone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.whichone.ui.screen.home.HomeScreen

@Composable
fun SetUpNavHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Destinations.Home
    ) {
        composable(route = Destinations.Home){
            HomeScreen()
        }
    }
}
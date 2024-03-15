package com.example.iniciosesionfp2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iniciosesionfp2.data.project20
import com.example.iniciosesionfp2.screens.HomeScreen
import com.example.iniciosesionfp2.screens.LoginScreen
import com.example.iniciosesionfp2.screens.ProjectDetailsScreen
import com.example.iniciosesionfp2.screens.RegistroScreen
import com.example.iniciosesionfp2.viewmodels.LoginScreenViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) { LoginScreen(navController, loginScreenViewModel = LoginScreenViewModel()) }
        composable(route = AppScreens.HomeScreen.route) { HomeScreen(navController) }
        composable(route = AppScreens.RegistroScreen.route) { RegistroScreen(navController) }
        composable(route = AppScreens.ProjectDetailsScreen.route) { ProjectDetailsScreen(
            navController = navController,
            project = project20
        )}
    }
}
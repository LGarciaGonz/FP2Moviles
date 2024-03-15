package com.example.iniciosesionfp2.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen : AppScreens(route = "login_screen")
    object HomeScreen : AppScreens(route = "home_screen")
    object RegistroScreen : AppScreens(route = "registro_screen")
    object ProjectDetailsScreen : AppScreens(route = "projectdetails_screen")
}

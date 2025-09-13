package com.calyrsoft.ucbp1.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object ForgotPassword : Screen("forgot_password")
    object Home : Screen("home")
    object CardExamples: Screen("card")
    object Profile: Screen("profile") // Añade esta línea
    object Github: Screen("github") // Y esta línea
}
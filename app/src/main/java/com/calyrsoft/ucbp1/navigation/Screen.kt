package com.calyrsoft.ucbp1.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object ForgotPassword : Screen("forgot_password")
    // Aquí puedes agregar otras pantallas, como la de inicio o perfil
    object Home : Screen("home")

    object CardExamples: Screen("card")
}
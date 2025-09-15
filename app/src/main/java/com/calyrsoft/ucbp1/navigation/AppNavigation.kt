package com.calyrsoft.ucbp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calyrsoft.ucbp1.features.forgot_password.presentation.ForgotPasswordScreen
import com.calyrsoft.ucbp1.features.github.presentation.GithubScreen
import com.calyrsoft.ucbp1.features.login.presentation.LoginScreen
import com.calyrsoft.ucbp1.features.profile.application.ProfileScreen
import com.calyrsoft.ucbp1.features.signup.presentation.SignUpScreen
import com.calyrsoft.ucbp1.features.signup.presentation.SignUpViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Login
        composable(Screen.Login.route) {
            LoginScreen(
                onForgotPasswordClicked = {
                    navController.navigate(Screen.ForgotPassword.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route)
                },
                onSignUpClicked = {   // 🔹 Nuevo callback
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }


        composable(Screen.SignUp.route) {
            val signUpViewModel: SignUpViewModel = koinViewModel()

            SignUpScreen(
                viewModel = signUpViewModel,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }


        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // Github
        composable(Screen.Github.route) {
            GithubScreen(modifier = Modifier)
        }

        // Home
        composable(Screen.Home.route) {
            // Aquí tu pantalla principal
        }

        // Profile
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}

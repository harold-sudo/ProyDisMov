package com.calyrsoft.ucbp1.features.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calyrsoft.ucbp1.R // Importa la clase R para los recursos
import org.koin.androidx.compose.koinViewModel
import androidx.annotation.DrawableRes

@Composable
fun LoginScreen(
    vm: LoginViewModel = koinViewModel(),
    onForgotPasswordClicked: () -> Unit,
    onLoginSuccess: () -> Unit,
    onSignUpClicked: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val uiState by vm.uiState.collectAsState()

    // Maneja la navegación cuando el inicio de sesión es exitoso
    LaunchedEffect(key1 = uiState) {
        if (uiState is LoginViewModel.LoginUiState.Success) {
            onLoginSuccess()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E3B46)), // Color de fondo oscuro de la imagen
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Inicia Sesión",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(50.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Ingresa tu número o correo electrónico", color = Color.LightGray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.White
                ),
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Ingresa tu contraseña", color = Color.LightGray) },
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.White
                ),
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                modifier = Modifier.fillMaxWidth()
            )

            // Texto "¿Olvidaste tu contraseña?"
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onForgotPasswordClicked) {
                    Text(
                        "¿Olvidaste tu contraseña?",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { vm.onLoginClicked(email, password) },
                enabled = uiState !is LoginViewModel.LoginUiState.Loading,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                if (uiState is LoginViewModel.LoginUiState.Loading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text(
                        "Inicia Sesion",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Manejo de estados y mensajes de error
            Spacer(modifier = Modifier.height(16.dp))
            when (uiState) {
                is LoginViewModel.LoginUiState.Error -> {
                    Text(
                        (uiState as LoginViewModel.LoginUiState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {}
            }

            Spacer(modifier = Modifier.weight(1f)) // Empuja el contenido inferior hacia abajo

            // Sección de inicio de sesión con redes sociales
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "ó inicia sesion con",
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    SocialButton(R.drawable.facebook_icon)
                    SocialButton(R.drawable.google_icon)
                    SocialButton(R.drawable.instagram_icon)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Texto "¿No tienes una cuenta? Regístrate"
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "¿No tienes una cuenta? ",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                    Button(onClick = onSignUpClicked) {
                        Text("¿No tienes cuenta? Regístrate")
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun SocialButton(
    @DrawableRes iconId: Int
) {
    Image(
        painter = painterResource(id = iconId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}
package com.calyrsoft.ucbp1.features.login.data.repository

import com.calyrsoft.ucbp1.features.login.domain.model.UserModel
import kotlinx.coroutines.delay

class LoginRepository {
    suspend fun login(email: String, password: String): Result<UserModel> {
        delay(1000) // Simula la llamada a la red

        return when {
            email.isEmpty() || password.isEmpty() -> Result.failure(IllegalArgumentException("El correo y la contraseña son obligatorios."))
            email == "test@test.com" && password == "password123" -> {
                Result.success(
                    UserModel(
                        email = email,
                        token = "fake_auth_token",
                        userId = "12345"
                    )
                )
            }
            else -> Result.failure(Exception("Credenciales incorrectas."))
        }
    }
}
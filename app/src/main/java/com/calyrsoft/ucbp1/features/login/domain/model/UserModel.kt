package com.calyrsoft.ucbp1.features.login.domain.model

data class UserModel(
    val email: String,
    val token: String, // Podrías usar un token para autenticar al usuario en el futuro
    val userId: String
)
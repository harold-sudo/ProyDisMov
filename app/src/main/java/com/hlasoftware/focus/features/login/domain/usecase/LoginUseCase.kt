package com.hlasoftware.focus.features.login.domain.usecase

import com.hlasoftware.focus.features.login.data.repository.LoginRepository
import com.hlasoftware.focus.features.login.domain.model.UserModel

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String): Result<UserModel> {
        return repository.login(email, password)
    }
}
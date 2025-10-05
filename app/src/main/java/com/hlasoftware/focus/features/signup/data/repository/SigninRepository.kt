package com.hlasoftware.focus.features.signup.data.repository

import com.hlasoftware.focus.features.signup.domain.model.SignUpModel
import kotlinx.coroutines.delay

class SignupRepository {
    suspend fun signUp(user : SignUpModel): Boolean {
        delay(1500)

        return !user.email.endsWith("exists.com")
    }
}
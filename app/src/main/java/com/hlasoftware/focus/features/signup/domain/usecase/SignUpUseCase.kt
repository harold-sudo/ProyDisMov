package com.hlasoftware.focus.features.signup.domain.usecase

import com.hlasoftware.focus.features.signup.data.repository.SignupRepository
import com.hlasoftware.focus.features.signup.domain.model.SignUpModel

class SignUpUseCase(
    private val signupRepository: SignupRepository
) {
    suspend operator fun invoke(user : SignUpModel): Boolean {
        return signupRepository.signUp(user);
    }
}
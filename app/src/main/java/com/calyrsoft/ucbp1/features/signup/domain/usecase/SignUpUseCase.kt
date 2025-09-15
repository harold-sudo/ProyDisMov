package com.calyrsoft.ucbp1.features.signup.domain.usecase

import com.calyrsoft.ucbp1.features.signup.data.repository.SignupRepository
import com.calyrsoft.ucbp1.features.signup.domain.model.SignUpModel

class SignUpUseCase(
    private val signupRepository: SignupRepository
) {
    suspend operator fun invoke(user : SignUpModel): Boolean {
        return signupRepository.signUp(user);
    }
}
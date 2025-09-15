package com.calyrsoft.ucbp1.features.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.signup.domain.model.SignUpModel
import com.calyrsoft.ucbp1.features.signup.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class SignUpUiState(
    val name: String = "",
    val birthdate: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    fun onNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun onBirthdateChanged(birthdate: String) {
        _uiState.value = _uiState.value.copy(birthdate = birthdate)
    }

    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onConfirmPasswordChanged(confirm: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirm)
    }

    fun onSignUpClick() {
        val current = _uiState.value

        if (
            current.name.isBlank() ||
            current.birthdate.isBlank() ||
            current.email.isBlank() ||
            current.password.isBlank() ||
            current.confirmPassword.isBlank()
        ) {
            _uiState.value = current.copy(error = "Todos los campos son obligatorios")
            return
        }

        if (!current.email.contains("@")) {
            _uiState.value = current.copy(error = "Email no válido")
            return
        }

        if (current.password.length < 6) {
            _uiState.value = current.copy(error = "La contraseña debe tener al menos 6 caracteres")
            return
        }

        if (current.password != current.confirmPassword) {
            _uiState.value = current.copy(error = "Las contraseñas no coinciden")
            return
        }

        viewModelScope.launch {
            _uiState.value = current.copy(loading = true, error = null)

            val user = SignUpModel(
                current.name,
                current.birthdate,
                current.email,
                current.password,
                current.confirmPassword
            )
            val result = signUpUseCase(user)

            if (result)
            {
                _uiState.value = _uiState.value.copy(loading = false, success = true)
            } else
            {
                _uiState.value = _uiState.value.copy(loading = false, error = "El email ya existe")
            }
        }
    }
}

package com.calyrsoft.ucbp1.features.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.home.domain.model.ActivityModel // Asegúrate que la ruta es correcta
import com.calyrsoft.ucbp1.features.home.domain.usecase.HomeUseCase // Asegúrate que la ruta es correcta
import kotlinx.coroutines.launch

// Estado de la UI para Home
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val activities: List<ActivityModel> // Solo la lista de actividades
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

class HomeViewModel(
    private val homeUseCase: HomeUseCase // Inyecta el caso de uso
) : ViewModel() {

    var uiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        loadUpcomingActivities()
    }

    fun loadUpcomingActivities() {
        viewModelScope.launch {
            uiState = HomeUiState.Loading
            homeUseCase().onSuccess { homeModel -> // Llama a HomeUseCase (sin parámetros)
                uiState = HomeUiState.Success(
                    activities = homeModel.upcomingActivities
                )
            }.onFailure { exception ->
                uiState = HomeUiState.Error(exception.localizedMessage ?: "Error desconocido al cargar actividades")
            }
        }
    }

    // Ya no necesitamos onPreviousDateClicked ni onNextDateClicked

    fun onActivityOptionsClicked(activityId: String) {
        // Lógica para manejar el clic en opciones de una actividad
        println("Opciones para la actividad con ID: $activityId seleccionada.")
    }
}

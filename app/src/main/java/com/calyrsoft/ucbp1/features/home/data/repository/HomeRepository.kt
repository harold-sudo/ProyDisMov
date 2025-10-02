package com.calyrsoft.ucbp1.features.home.data.repository

import com.calyrsoft.ucbp1.features.home.domain.model.ActivityModel
import com.calyrsoft.ucbp1.features.home.domain.model.ActivityType
import com.calyrsoft.ucbp1.features.home.domain.model.HomeModel
import com.calyrsoft.ucbp1.features.home.domain.repository.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeRepository : IHomeRepository {

    // Lista fija de actividades "próximas" para simulación
    private val upcomingActivitiesList = listOf(
        ActivityModel("1", "Clase de Física", "14:00 - 15:00", ActivityType.CLASS),
        ActivityModel("2", "Tarea de Matemáticas", "16:00 - 17:00", ActivityType.TASK),
        ActivityModel("3", "Reunión Grupal", "Hoy 10:00 - 11:30", ActivityType.MEETING),
        ActivityModel("4", "Estudiar para Examen", "Mañana", ActivityType.TASK),
        ActivityModel("5", "Clase de Química", "Próx. Lun 09:00", ActivityType.CLASS)
    )

    override suspend fun getUpcomingActivitiesData(): Result<HomeModel> {
        return withContext(Dispatchers.IO) {
            try {
                delay(500) // Simular latencia

                val homeData = HomeModel(
                    upcomingActivities = upcomingActivitiesList // Devuelve la lista completa
                )
                Result.success(homeData)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}

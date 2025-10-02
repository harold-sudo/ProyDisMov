package com.calyrsoft.ucbp1.features.home.domain.usecase // Make sure this package path is correct

import com.calyrsoft.ucbp1.features.home.domain.model.HomeModel
import com.calyrsoft.ucbp1.features.home.domain.repository.IHomeRepository
import java.time.LocalDate // Ensure LocalDate is imported

class HomeUseCase(
    private val homeRepository: IHomeRepository
) {
    // El operador invoke ya no necesita el parámetro 'date'
    suspend operator fun invoke(): Result<HomeModel> {
        return homeRepository.getUpcomingActivitiesData()
    }
}

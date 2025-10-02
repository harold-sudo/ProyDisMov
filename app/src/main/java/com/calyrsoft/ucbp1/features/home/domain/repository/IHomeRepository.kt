package com.calyrsoft.ucbp1.features.home.domain.repository

import com.calyrsoft.ucbp1.features.home.domain.model.HomeModel

interface IHomeRepository {
    suspend fun getUpcomingActivitiesData(): Result<HomeModel> // Un solo método para obtener los datos
}
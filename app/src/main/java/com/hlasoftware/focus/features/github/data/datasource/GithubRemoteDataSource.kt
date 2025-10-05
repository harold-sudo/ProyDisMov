package com.hlasoftware.focus.features.github.data.datasource


import com.hlasoftware.focus.features.github.data.api.GithubService
import com.hlasoftware.focus.features.github.data.api.dto.GithubDto


class GithubRemoteDataSource(
    val githubService: GithubService
) {
    suspend fun getUser(nick: String): Result<GithubDto> {
        val response = githubService.getInfoAvatar(nick)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        } else {
            return Result.failure(Exception("Error al obtener el usuario"))
        }
    }
}

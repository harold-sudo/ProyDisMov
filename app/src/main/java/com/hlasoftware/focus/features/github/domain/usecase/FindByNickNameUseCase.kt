package com.hlasoftware.focus.features.github.domain.usecase

import com.hlasoftware.focus.features.github.domain.model.UserModel
import com.hlasoftware.focus.features.github.domain.repository.IGithubRepository
import kotlinx.coroutines.delay

class FindByNickNameUseCase(
    val repository: IGithubRepository
) {
    suspend fun invoke(nickname: String) : Result<UserModel>  {
        delay(5000)
        return repository.findByNick(nickname)
    }
}
package com.hlasoftware.focus.features.github.domain.repository

import com.hlasoftware.focus.features.github.domain.model.UserModel

interface IGithubRepository {
    fun findByNick(value: String): Result<UserModel>
}
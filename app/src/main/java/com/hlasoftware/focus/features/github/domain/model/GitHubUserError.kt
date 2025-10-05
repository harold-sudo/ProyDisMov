package com.hlasoftware.focus.features.github.domain.model

sealed class GitHubUserError : Exception() {
    object EmptyInput : GitHubUserError()
    data class UserNotFound(val username: String) : GitHubUserError()
    object NetworkError : GitHubUserError()
    object UnknownError : GitHubUserError()
}
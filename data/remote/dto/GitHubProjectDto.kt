package com.example.meucartaodevisitas.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GitHubProjectDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("language")
    val language: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
    // Você pode adicionar mais campos aqui conforme necessário da API do GitHub
)
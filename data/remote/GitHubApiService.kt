package com.example.meucartaodevisitas.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Headers // CORREÇÃO: Importando Headers
import com.example.meucartaodevisitas.data.remote.dto.GitHubProjectDto

interface GitHubApiService {

    @Headers("Accept: application/vnd.github.v3+json") // CORREÇÃO: Adicionando cabeçalho Accept para a API v3
    @GET("users/{username}/repos")
    suspend fun getUserRepositories(
        @Path("username") username: String
    ): List<GitHubProjectDto>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}
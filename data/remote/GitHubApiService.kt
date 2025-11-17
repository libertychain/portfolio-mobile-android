package com.example.meucartaodevisitas.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import com.example.meucartaodevisitas.data.remote.dto.GitHubProjectDto // CORREÇÃO: Importando GitHubProjectDto

interface GitHubApiService {

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(
        @Path("username") username: String
    ): List<GitHubProjectDto>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}
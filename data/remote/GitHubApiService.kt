package com.example.meucartaodevisitas.data.remote

interface GitHubApiService {

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(
        @Path("username") username: String
    ): List<GitHubProjectDto>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}
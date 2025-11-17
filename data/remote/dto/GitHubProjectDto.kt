package com.example.meucartaodevisitas.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GitHubProjectDto(
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("description")
    val description: String?,
    
    @SerializedName("language")
    val language: String?,
    
    @SerializedName("stargazers_count")
    val stars: Int,
    
    @SerializedName("html_url")
    val url: String,
    
    @SerializedName("updated_at")
    val updatedAt: String
)
package com.example.meucartaodevisitas.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val language: String? = null,
    val stars: Int = 0,
    val url: String? = null,
    val updatedAt: String? = null
)
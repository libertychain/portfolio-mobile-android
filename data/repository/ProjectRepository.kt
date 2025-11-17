package com.example.meucartaodevisitas.data.repository

import com.example.meucartaodevisitas.data.local.dao.ProjectDao
import com.example.meucartaodevisitas.data.local.entity.ProjectEntity
import com.example.meucartaodevisitas.data.remote.GitHubApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    private val projectDao: ProjectDao,
    private val apiService: GitHubApiService
) {
    
    fun getProjects(): Flow<List<ProjectEntity>> {
        return projectDao.getAllProjects()
    }
    
    suspend fun getProjectById(id: Int): ProjectEntity? {
        return projectDao.getProjectById(id)
    }
    
    suspend fun syncProjectsFromGitHub(username: String): Result<Unit> {
        return try {
            val gitHubProjects = apiService.getUserRepositories(username)
            val projectEntities = gitHubProjects.map { dto ->
                ProjectEntity(
                    id = dto.id,
                    name = dto.name,
                    description = dto.description ?: "Sem descrição",
                    language = dto.language,
                    stars = dto.stars,
                    url = dto.url,
                    updatedAt = dto.updatedAt
                )
            }
            
            projectDao.clearAllProjects()
            projectDao.insertProjects(projectEntities)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun hasLocalData(): Boolean {
        return projectDao.getProjectCount() > 0
    }
    
    suspend fun clearLocalData() {
        projectDao.clearAllProjects()
    }
}
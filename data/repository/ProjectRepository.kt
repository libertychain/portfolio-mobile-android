package com.example.meucartaodevisitas.data.repository

import com.example.meucartaodevisitas.data.local.dao.ProjectDao
import com.example.meucartaodevisitas.data.local.entity.ProjectEntity
import com.example.meucartaodevisitas.data.remote.GitHubApiService
import com.example.meucartaodevisitas.data.remote.dto.GitHubProjectDto // Import adicionado anteriormente
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.Result // CORREÇÃO: Importando kotlin.Result

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
            val projectEntities = gitHubProjects.map { dto: GitHubProjectDto -> // CORREÇÃO: Especificando o tipo do dto explicitamente
                ProjectEntity(
                    id = dto.id.toInt(), // CORREÇÃO: Convertido Long para Int
                    name = dto.name,
                    description = dto.description ?: "Sem descrição",
                    language = dto.language,
                    stars = dto.stargazersCount, // CORREÇÃO: Mapeado de stargazersCount para stars
                    url = dto.htmlUrl, // CORREÇÃO: Mapeado de htmlUrl para url
                    updatedAt = "" // CORREÇÃO: Adicionando valor padrão temporário para updatedAt (não existe no DTO)
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
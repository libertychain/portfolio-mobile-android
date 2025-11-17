package com.example.meucartaodevisitas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meucartaodevisitas.data.local.entity.ProjectEntity
import com.example.meucartaodevisitas.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val repository: ProjectRepository
) : ViewModel() {
    
    // Configurar seu nome de usuário do GitHub aqui
    private val gitHubUsername = "SEU_USUARIO_GITHUB"
    
    private val _uiState = MutableStateFlow<ProjectUiState>(ProjectUiState.Loading)
    val uiState: StateFlow<ProjectUiState> = _uiState.asStateFlow()
    
    private val _projects = MutableStateFlow<List<ProjectEntity>>(emptyList())
    val projects: StateFlow<List<ProjectEntity>> = _projects.asStateFlow()
    
    init {
        loadProjects()
    }
    
    private fun loadProjects() {
        viewModelScope.launch {
            try {
                _uiState.value = ProjectUiState.Loading
                
                // Verificar se há dados locais
                val hasLocalData = repository.hasLocalData()
                
                // Se não houver dados locais, sincronizar com GitHub
                if (!hasLocalData) {
                    syncWithGitHub()
                }
                
                // Observar dados locais
                repository.getProjects().collect { projects ->
                    _projects.value = projects
                    _uiState.value = if (projects.isEmpty()) {
                        ProjectUiState.Error("Nenhum projeto encontrado")
                    } else {
                        ProjectUiState.Success
                    }
                }
            } catch (e: Exception) {
                _uiState.value = ProjectUiState.Error("Erro ao carregar projetos: ${e.message}")
            }
        }
    }
    
    fun refreshProjects() {
        viewModelScope.launch {
            syncWithGitHub()
        }
    }
    
    private suspend fun syncWithGitHub() {
        try {
            _uiState.value = ProjectUiState.Loading
            val result = repository.syncProjectsFromGitHub(gitHubUsername)
            
            if (result.isFailure) {
                _uiState.value = ProjectUiState.Error(
                    "Erro ao sincronizar com GitHub: ${result.exceptionOrNull()?.message}"
                )
            }
        } catch (e: Exception) {
            _uiState.value = ProjectUiState.Error("Erro na sincronização: ${e.message}")
        }
    }
    
    fun clearAndReload() {
        viewModelScope.launch {
            repository.clearLocalData()
            loadProjects()
        }
    }
}

sealed class ProjectUiState {
    object Loading : ProjectUiState()
    object Success : ProjectUiState()
    data class Error(val message: String) : ProjectUiState()
}
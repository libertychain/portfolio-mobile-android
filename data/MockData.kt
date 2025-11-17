package com.example.meucartaodevisitas.data

import com.example.meucartaodevisitas.model.Project

object MockData {
    fun getProjects(): List<Project> {
        return listOf(
            Project(
                id = 1,
                name = "App de Tarefas",
                description = "Aplicativo para gerenciamento de tarefas diárias com notificações"
            ),
            Project(
                id = 2,
                name = "E-commerce Mobile",
                description = "Loja virtual com carrinho de compras e integração com pagamento"
            ),
            Project(
                id = 3,
                name = "Rede Social",
                description = "Aplicativo de rede social com feed, perfis e mensagens"
            ),
            Project(
                id = 4,
                name = "Weather App",
                description = "Aplicativo de previsão do tempo com localização e gráficos"
            ),
            Project(
                id = 5,
                name = "Fitness Tracker",
                description = "Rastreador de atividades físicas com metas e estatísticas"
            )
        )
    }
}
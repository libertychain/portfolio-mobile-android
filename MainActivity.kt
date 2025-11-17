package com.example.meucartaodevisitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meucartaodevisitas.ui.screens.ProfileScreen
import com.example.meucartaodevisitas.ui.screens.ProjectDetailScreen
import com.example.meucartaodevisitas.ui.screens.ProjectListScreen
import com.example.meucartaodevisitas.ui.theme.MeuCartaoDeVisitasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuCartaoDeVisitasTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "projectList"
    ) {
        composable("projectList") {
            ProjectListScreen(navController)
        }
        
        composable("profile") {
            ProfileScreen(navController)
        }
        
        composable("projectDetail/{projectId}") { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")?.toIntOrNull()
            if (projectId != null) {
                ProjectDetailScreen(navController, projectId)
            } else {
                // Tratar erro - ID inválido
                Text("ID de projeto inválido")
            }
        }
    }
}
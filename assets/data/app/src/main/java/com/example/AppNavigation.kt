package com.example

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature.home.HomeScreen
import com.example.feature.assessment.AssessmentScreen
import com.example.feature.anatomy.AnatomyScreen
import com.example.feature.viva.VivaScreen
import com.example.feature.chat.ChatbotScreen
import com.example.feature.posting.ClinicalPostingScreen
import com.example.feature.about.AboutScreen

@Composable
fun AppNavigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("assessment") {
            AssessmentScreen(navController = navController, context = context)
        }
        composable("anatomy") {
            AnatomyScreen(navController = navController)
        }
        composable("viva") {
            VivaScreen(navController = navController)
        }
        composable("chat") {
            ChatbotScreen(navController = navController)
        }
        composable("posting") {
            ClinicalPostingScreen(navController = navController)
        }
        composable("about") {
            AboutScreen(navController = navController)
        }
    }
}

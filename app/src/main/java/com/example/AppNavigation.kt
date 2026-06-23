package com.example

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature.home.HomeScreen
import com.example.feature.assessment.AssessmentScreen
import com.example.feature.assessment.SpecialTestRepositoryScreen
import com.example.feature.assessment.ClinicalCaseRepositoryScreen
import com.example.feature.anatomy.AnatomyScreen
import com.example.feature.viva.VivaScreen
import com.example.feature.viva.VivaGeneratorScreen
import com.example.feature.chat.ChatbotScreen
import com.example.feature.posting.ClinicalPostingScreen
import com.example.feature.about.AboutScreen
import com.example.feature.search.SearchScreen
import com.example.feature.topic.TopicHubScreen
import com.example.feature.reference.ReferenceHubScreen
import com.example.feature.curriculum.CurriculumScreen
import com.example.feature.subject.SubjectDetailScreen
import com.example.feature.subject.ChapterDetailScreen
import androidx.navigation.navArgument
import androidx.navigation.NavType

@Composable
fun AppNavigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("flashcards") {
            com.example.feature.retention.FlashcardScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("curriculum_dashboard") {
            CurriculumScreen(navController = navController)
        }
        composable("reference_hub") {
            ReferenceHubScreen(navController = navController)
        }
        composable(
            route = "subject_detail/{subjectId}",
            arguments = listOf(
                navArgument("subjectId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            SubjectDetailScreen(navController = navController, subjectId = subjectId)
        }
        composable(
            route = "module_detail/{subjectId}/{moduleId}",
            arguments = listOf(
                navArgument("subjectId") { type = NavType.StringType },
                navArgument("moduleId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: ""
            com.example.feature.subject.ModuleDetailScreen(navController = navController, subjectId = subjectId, moduleId = moduleId)
        }
        composable(
            route = "chapter_detail/{subjectId}/{chapterId}",
            arguments = listOf(
                navArgument("subjectId") { type = NavType.StringType },
                navArgument("chapterId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            val chapterId = backStackEntry.arguments?.getString("chapterId") ?: ""
            ChapterDetailScreen(navController = navController, subjectId = subjectId, chapterId = chapterId)
        }
        composable(
            route = "topic_hub?topicId={topicId}&query={query}",
            arguments = listOf(
                navArgument("topicId") { defaultValue = "" },
                navArgument("query") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: ""
            val query = backStackEntry.arguments?.getString("query") ?: ""
            TopicHubScreen(navController = navController, topicId = topicId, queryStr = query)
        }
        composable("search") {
            SearchScreen(navController = navController)
        }
        composable(
            route = "assessment?tab={tab}",
            arguments = listOf(
                navArgument("tab") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val tab = backStackEntry.arguments?.getInt("tab") ?: 0
            AssessmentScreen(navController = navController, context = context, initialTab = tab)
        }
        composable("special_tests") {
            SpecialTestRepositoryScreen(navController = navController)
        }
        composable("clinical_cases") {
            ClinicalCaseRepositoryScreen(navController = navController)
        }
        composable("anatomy") {
            AnatomyScreen(navController = navController)
        }
        composable("anatomy_navigator") {
            com.example.feature.anatomy.AnatomyNavigatorScreen(navController = navController)
        }
        composable(
            route = "region_dashboard/{region}",
            arguments = listOf(
                navArgument("region") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val region = backStackEntry.arguments?.getString("region") ?: ""
            com.example.feature.anatomy.RegionDashboardScreen(navController = navController, region = region)
        }
        composable("viva") {
            VivaScreen(navController = navController)
        }
        composable("viva_generator") {
            VivaGeneratorScreen(navController = navController)
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

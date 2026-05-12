package com.memory.brain.training.games.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.memory.brain.training.games.presentation.screens.game.GameFactory
import com.memory.brain.training.games.presentation.screens.game.GameResultScreen
import com.memory.brain.training.games.presentation.screens.main.MainScreen
import com.memory.brain.training.games.presentation.screens.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Main.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Main.route) {
            MainScreen(
                onNavigateToGame = { gameId ->
                    navController.navigate(Screen.Game.createRoute(gameId))
                }
            )
        }
        
        composable(
            route = Screen.Game.route,
            arguments = listOf(
                navArgument("gameId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 1
            GameFactory(
                gameId = gameId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onGameComplete = { score, stars ->
                    navController.navigate(Screen.GameResult.createRoute(gameId, score, stars)) {
                        popUpTo(Screen.Game.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(
            route = Screen.GameResult.route,
            arguments = listOf(
                navArgument("gameId") { type = NavType.IntType },
                navArgument("score") { type = NavType.IntType },
                navArgument("stars") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 1
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            val stars = backStackEntry.arguments?.getInt("stars") ?: 0
            
            GameResultScreen(
                gameId = gameId,
                score = score,
                stars = stars,
                onPlayAgain = {
                    navController.navigate(Screen.Game.createRoute(gameId)) {
                        popUpTo(Screen.Main.route) { inclusive = false }
                    }
                },
                onBackToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Main.route) { inclusive = false }
                    }
                }
            )
        }
    }
}

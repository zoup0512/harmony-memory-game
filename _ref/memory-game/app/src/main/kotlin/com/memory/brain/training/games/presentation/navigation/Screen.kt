package com.memory.brain.training.games.presentation.navigation

/**
 * 应用中的所有屏幕路由
 */
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Game : Screen("game/{gameId}") {
        fun createRoute(gameId: Int) = "game/$gameId"
    }
    object GameResult : Screen("game_result/{gameId}/{score}/{stars}") {
        fun createRoute(gameId: Int, score: Int, stars: Int) = "game_result/$gameId/$score/$stars"
    }
}

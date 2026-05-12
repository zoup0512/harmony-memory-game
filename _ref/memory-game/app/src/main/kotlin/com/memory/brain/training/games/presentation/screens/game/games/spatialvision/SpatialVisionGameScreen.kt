package com.memory.brain.training.games.presentation.screens.game.games.spatialvision

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.memory.brain.training.games.presentation.screens.game.common.PlaceholderGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PlaceholderGameViewModel

@Composable
fun SpatialVisionGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: PlaceholderGameViewModel = hiltViewModel()
) {
    PlaceholderGameScreen(
        gameId = gameId,
        onNavigateBack = onNavigateBack,
        onGameComplete = onGameComplete,
        viewModel = viewModel
    )
}

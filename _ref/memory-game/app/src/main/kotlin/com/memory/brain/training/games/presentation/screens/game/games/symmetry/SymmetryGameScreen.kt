package com.memory.brain.training.games.presentation.screens.game.games.symmetry

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

@Composable
fun SymmetryGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: SymmetryViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    
    LaunchedEffect(Unit) {
        viewModel.startGame()
    }
    
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == GameState.GAME_OVER) {
            val score = (uiState.level - 1) * 100
            val stars = when {
                uiState.level >= 15 -> 3
                uiState.level >= 8 -> 2
                else -> 1
            }
            onGameComplete(score, stars)
        }
    }
    
    if (uiState.isPaused) {
        PauseDialog(
            gameName = game?.name ?: "",
            onDismiss = { viewModel.resumeGame() },
            onResume = { viewModel.resumeGame() },
            onRestart = { viewModel.restartGame() },
            onExit = onNavigateBack
        )
    }
    
    BaseGameScreen(
        game = game,
        level = uiState.level,
        lives = uiState.lives,
        showProgressBar = true,
        progressBarProgress = (uiState.timeRemaining / 80000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                uiState.cells.chunked(uiState.gridWidth).forEach { row ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        row.forEach { cell ->
                            SymmetryCell(
                                cell = cell,
                                onClick = { viewModel.onCellClicked(cell) },
                                enabled = uiState.gameState == GameState.PLAYING,
                                modifier = Modifier.weight(1f).aspectRatio(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SymmetryCell(
    cell: SymmetryViewModel.CellData,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        cell.isClicked && cell.isSymmetric -> Color(0xFF4CAF50)
        cell.isClicked && !cell.isSymmetric -> Color(0xFFF44336)
        else -> Color(0xFF2196F3)
    }
    
    Box(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .border(1.dp, Color.White, RoundedCornerShape(4.dp))
            .clickable(enabled = enabled && !cell.isClicked) { onClick() }
    )
}

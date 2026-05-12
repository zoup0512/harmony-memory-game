package com.memory.brain.training.games.presentation.screens.game.games.whosnew

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

/**
 * Game 3: Who's New Game Screen (谁是新的)
 */
@Composable
fun WhosNewGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    val viewModel = remember { WhosNewViewModel() }
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(uiState.isPlaying, uiState.lives) {
        if (!uiState.isPlaying && uiState.lives <= 0) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
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
        score = uiState.score,
        level = uiState.level,
        lives = uiState.lives,
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            GridLayout(
                gridSize = uiState.gridSize,
                highlightedCells = uiState.highlightedCells,
                newCellIndex = uiState.newCellIndex,
                gameState = uiState.gameState,
                onCellClick = { viewModel.onCellClick(it) }
            )
        }
    }
}

@Composable
fun GridLayout(
    gridSize: Int,
    highlightedCells: Set<Int>,
    newCellIndex: Int,
    gameState: GameState,
    onCellClick: (Int) -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (gameState == GameState.SUCCESS_FEEDBACK) 1.1f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        repeat(gridSize) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(gridSize) { col ->
                    val cellIndex = row * gridSize + col
                    val isHighlighted = highlightedCells.contains(cellIndex)
                    val isNew = cellIndex == newCellIndex && gameState == GameState.USER_INPUT
                    
                    val cellColor = when {
                        gameState == GameState.HIDING -> Color(0xFFE0E0E0)
                        gameState == GameState.SUCCESS_FEEDBACK && isNew -> Color(0xFF4CAF50)
                        gameState == GameState.FAILURE_FEEDBACK && isNew -> Color(0xFFF44336)
                        isHighlighted -> Color(0xFF2196F3)
                        else -> Color(0xFFE0E0E0)
                    }
                    
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .background(cellColor, RoundedCornerShape(8.dp))
                            .border(2.dp, Color.White, RoundedCornerShape(8.dp))
                            .clickable(enabled = gameState == GameState.USER_INPUT) {
                                onCellClick(cellIndex)
                            }
                    )
                }
            }
        }
    }
}

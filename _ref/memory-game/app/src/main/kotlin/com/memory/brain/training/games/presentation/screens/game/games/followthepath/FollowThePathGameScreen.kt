package com.memory.brain.training.games.presentation.screens.game.games.followthepath

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

/**
 * Game 5: Follow the Path Game Screen (跟随路径)
 */
@Composable
fun FollowThePathGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    val viewModel = remember { FollowThePathViewModel() }
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
            PathGrid(
                gridSize = uiState.gridSize,
                pathSequence = uiState.pathSequence,
                currentPathIndex = uiState.currentPathIndex,
                userSequence = uiState.userSequence,
                gameState = uiState.gameState,
                onCellClick = { viewModel.onCellClick(it) }
            )
        }
    }
}

@Composable
fun PathGrid(
    gridSize: Int,
    pathSequence: List<Int>,
    currentPathIndex: Int,
    userSequence: List<Int>,
    gameState: GameState,
    onCellClick: (Int) -> Unit
) {
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
                    
                    PathCell(
                        cellIndex = cellIndex,
                        pathSequence = pathSequence,
                        currentPathIndex = currentPathIndex,
                        userSequence = userSequence,
                        gameState = gameState,
                        onCellClick = onCellClick
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.PathCell(
    cellIndex: Int,
    pathSequence: List<Int>,
    currentPathIndex: Int,
    userSequence: List<Int>,
    gameState: GameState,
    onCellClick: (Int) -> Unit
) {
    val isInPath = pathSequence.contains(cellIndex)
    val isCurrentlyShowing = gameState == GameState.SHOWING_PATH && 
                             currentPathIndex >= 0 && 
                             pathSequence[currentPathIndex] == cellIndex
    val isUserClicked = userSequence.contains(cellIndex)
    val userClickIndex = userSequence.indexOf(cellIndex)
    val isCorrect = userClickIndex >= 0 && pathSequence.getOrNull(userClickIndex) == cellIndex
    val isWrong = userClickIndex >= 0 && pathSequence.getOrNull(userClickIndex) != cellIndex
    
    // Animation for showing path
    val scale by animateFloatAsState(
        targetValue = if (isCurrentlyShowing) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    val cellColor = when {
        gameState == GameState.SHOWING_PATH && isCurrentlyShowing -> Color(0xFF2196F3) // Blue when showing
        gameState == GameState.USER_INPUT && isUserClicked && isCorrect -> Color(0xFF4CAF50) // Green for correct
        gameState == GameState.USER_INPUT && isUserClicked && isWrong -> Color(0xFFF44336) // Red for wrong
        gameState == GameState.SUCCESS_FEEDBACK && isInPath -> Color(0xFF4CAF50) // Green for success
        gameState == GameState.FAILURE_FEEDBACK && isInPath && !isUserClicked -> Color(0xFFFFC107) // Yellow for missed
        gameState == GameState.FAILURE_FEEDBACK && isWrong -> Color(0xFFF44336) // Red for wrong
        else -> Color(0xFFE0E0E0) // Gray for empty
    }
    
    Box(
        modifier = Modifier
            .weight(1f)
            .aspectRatio(1f)
            .padding(4.dp)
            .background(cellColor, CircleShape)
            .border(2.dp, Color.White, CircleShape)
            .clickable(enabled = gameState == GameState.USER_INPUT) {
                onCellClick(cellIndex)
            }
    )
}

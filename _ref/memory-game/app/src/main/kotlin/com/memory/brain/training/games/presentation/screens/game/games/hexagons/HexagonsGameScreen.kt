package com.memory.brain.training.games.presentation.screens.game.games.hexagons

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI

/**
 * Game 2: Hexagons Game Screen (六边形)
 * Hexagonal grid memory game
 */
@Composable
fun HexagonsGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    val viewModel = remember { HexagonsViewModel() }
    val uiState by viewModel.uiState.collectAsState()
    
    // Handle game over
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
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HexagonalGrid(
                gridSize = uiState.gridSize,
                challengeCells = uiState.challengeCells,
                userSelectedCells = uiState.userSelectedCells,
                gameState = uiState.gameState,
                onCellClick = { viewModel.onCellClick(it) }
            )
        }
    }
}

@Composable
fun HexagonalGrid(
    gridSize: Int,
    challengeCells: Set<Int>,
    userSelectedCells: Set<Int>,
    gameState: GameState,
    onCellClick: (Int) -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (gameState == GameState.SUCCESS_FEEDBACK) 1.1f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(gameState) {
                if (gameState == GameState.USER_INPUT) {
                    detectTapGestures { offset ->
                        val cellIndex = getCellIndexFromOffset(
                            offset,
                            size.width.toFloat(),
                            size.height.toFloat(),
                            gridSize
                        )
                        if (cellIndex != -1) {
                            onCellClick(cellIndex)
                        }
                    }
                }
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val hexRadius = (canvasWidth / (gridSize * 2.2f)).coerceAtMost(canvasHeight / (gridSize * 2.2f))
        
        for (row in 0 until gridSize) {
            for (col in 0 until gridSize) {
                val cellIndex = row * gridSize + col
                val center = getHexagonCenter(row, col, hexRadius, canvasWidth, canvasHeight, gridSize)
                
                val color = when {
                    gameState == GameState.SHOWING_CHALLENGE && challengeCells.contains(cellIndex) -> 
                        Color(0xFF4CAF50) // Green for challenge
                    gameState == GameState.USER_INPUT && userSelectedCells.contains(cellIndex) -> 
                        Color(0xFF2196F3) // Blue for selected
                    gameState == GameState.SUCCESS_FEEDBACK && challengeCells.contains(cellIndex) -> 
                        Color(0xFF4CAF50) // Green for success
                    gameState == GameState.FAILURE_FEEDBACK && challengeCells.contains(cellIndex) && !userSelectedCells.contains(cellIndex) -> 
                        Color(0xFFFFC107) // Yellow for missed
                    gameState == GameState.FAILURE_FEEDBACK && userSelectedCells.contains(cellIndex) && !challengeCells.contains(cellIndex) -> 
                        Color(0xFFF44336) // Red for wrong
                    else -> Color(0xFFE0E0E0) // Gray for empty
                }
                
                drawHexagon(center, hexRadius * scale, color)
            }
        }
    }
}

fun getHexagonCenter(row: Int, col: Int, radius: Float, canvasWidth: Float, canvasHeight: Float, gridSize: Int): Offset {
    val horizontalSpacing = radius * 1.73f // sqrt(3) * radius
    val verticalSpacing = radius * 1.5f
    
    val offsetX = if (row % 2 == 1) horizontalSpacing / 2 else 0f
    val x = col * horizontalSpacing + offsetX + radius * 2
    val y = row * verticalSpacing + radius * 2
    
    // Center the grid
    val totalWidth = (gridSize - 1) * horizontalSpacing + horizontalSpacing / 2
    val totalHeight = (gridSize - 1) * verticalSpacing
    val centerOffsetX = (canvasWidth - totalWidth) / 2
    val centerOffsetY = (canvasHeight - totalHeight) / 2
    
    return Offset(x + centerOffsetX, y + centerOffsetY)
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawHexagon(center: Offset, radius: Float, color: Color) {
    val path = Path()
    for (i in 0..6) {
        val angle = (PI / 3.0 * i).toFloat()
        val x = center.x + radius * cos(angle)
        val y = center.y + radius * sin(angle)
        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    path.close()
    
    drawPath(path, color)
    drawPath(path, Color.White, style = Stroke(width = 3f))
}

fun getCellIndexFromOffset(offset: Offset, canvasWidth: Float, canvasHeight: Float, gridSize: Int): Int {
    val hexRadius = (canvasWidth / (gridSize * 2.2f)).coerceAtMost(canvasHeight / (gridSize * 2.2f))
    
    for (row in 0 until gridSize) {
        for (col in 0 until gridSize) {
            val center = getHexagonCenter(row, col, hexRadius, canvasWidth, canvasHeight, gridSize)
            val distance = kotlin.math.sqrt(
                (offset.x - center.x) * (offset.x - center.x) +
                (offset.y - center.y) * (offset.y - center.y)
            )
            if (distance <= hexRadius) {
                return row * gridSize + col
            }
        }
    }
    return -1
}

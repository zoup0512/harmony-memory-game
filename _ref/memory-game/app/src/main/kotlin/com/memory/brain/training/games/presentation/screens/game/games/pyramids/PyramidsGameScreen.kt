package com.memory.brain.training.games.presentation.screens.game.games.pyramids

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

/**
 * Game 17: Pyramids Game Screen (金字塔)
 */
@Composable
fun PyramidsGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    val viewModel = remember { PyramidsViewModel() }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Timer bar
            if (uiState.gameState == GameState.USER_INPUT) {
                val progress = (uiState.timeRemaining / 60000f).coerceIn(0f, 1f)
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = if (progress > 0.3f) Color(0xFF4CAF50) else Color(0xFFF44336)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            // Pyramids
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                uiState.pyramids.forEachIndexed { index, pyramid ->
                    PyramidView(
                        pyramid = pyramid,
                        isDifferent = index == uiState.differentIndex,
                        gameState = uiState.gameState,
                        onClick = { viewModel.onPyramidClick(index) },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(0.7f)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PyramidView(
    pyramid: Pyramid,
    isDifferent: Boolean,
    gameState: GameState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(
        targetValue = when {
            gameState == GameState.SUCCESS_FEEDBACK && isDifferent -> 1.1f
            gameState == GameState.FAILURE_FEEDBACK && isDifferent -> 1.1f
            else -> 1f
        },
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    val borderColor = when {
        gameState == GameState.SUCCESS_FEEDBACK && isDifferent -> Color(0xFF4CAF50)
        gameState == GameState.FAILURE_FEEDBACK && isDifferent -> Color(0xFFFFC107)
        else -> Color(0xFFE0E0E0)
    }
    
    Box(
        modifier = modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(3.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(enabled = gameState == GameState.USER_INPUT) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val centerX = canvasWidth / 2
            
            // Draw circles from bottom to top
            pyramid.colors.forEachIndexed { index, colorLong ->
                val color = Color(colorLong)
                val sizeRatio = pyramid.sizes[index]
                val radius = (canvasWidth / 2) * sizeRatio * 0.9f
                
                // Calculate Y position (stack from bottom)
                val spacing = canvasHeight / (pyramid.colors.size + 1)
                val y = canvasHeight - (spacing * (index + 1))
                
                drawCircle(
                    color = color,
                    radius = radius,
                    center = Offset(centerX, y)
                )
                
                // Draw border
                drawCircle(
                    color = Color.White,
                    radius = radius,
                    center = Offset(centerX, y),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3f)
                )
            }
        }
    }
}

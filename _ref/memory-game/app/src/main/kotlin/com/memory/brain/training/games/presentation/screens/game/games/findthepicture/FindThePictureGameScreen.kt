package com.memory.brain.training.games.presentation.screens.game.games.findthepicture

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

/**
 * Game 8: Find the Picture Game Screen (找图片)
 */
@Composable
fun FindThePictureGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    val viewModel = remember { FindThePictureViewModel() }
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
            // Target picture display (shown during user input)
            if (uiState.gameState == GameState.USER_INPUT) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "找到这个图片：",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = uiState.targetEmoji,
                            fontSize = 56.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // Grid
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                PictureGrid(
                    gridSize = uiState.gridSize,
                    grid = uiState.grid,
                    targetEmoji = uiState.targetEmoji,
                    gameState = uiState.gameState,
                    onCellClick = { viewModel.onCellClick(it) }
                )
            }
        }
    }
}

@Composable
fun PictureGrid(
    gridSize: Int,
    grid: List<String>,
    targetEmoji: String,
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
                    val emoji = grid.getOrNull(cellIndex) ?: ""
                    
                    PictureCell(
                        emoji = emoji,
                        targetEmoji = targetEmoji,
                        gameState = gameState,
                        onClick = { onCellClick(cellIndex) }
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.PictureCell(
    emoji: String,
    targetEmoji: String,
    gameState: GameState,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = when (gameState) {
            GameState.SUCCESS_FEEDBACK -> if (emoji == targetEmoji) 1.2f else 1f
            GameState.FAILURE_FEEDBACK -> if (emoji == targetEmoji) 1.2f else 1f
            else -> 1f
        },
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    val alpha by animateFloatAsState(
        targetValue = when (gameState) {
            GameState.SHOWING_PICTURES -> 1f
            GameState.HIDING -> 0f
            GameState.USER_INPUT -> if (emoji.isNotEmpty()) 1f else 0f
            else -> 1f
        },
        animationSpec = tween(300)
    )
    
    val backgroundColor = when {
        gameState == GameState.SUCCESS_FEEDBACK && emoji == targetEmoji -> Color(0xFF4CAF50)
        gameState == GameState.FAILURE_FEEDBACK && emoji == targetEmoji -> Color(0xFFFFC107)
        else -> Color(0xFFF5F5F5)
    }
    
    Box(
        modifier = Modifier
            .weight(1f)
            .aspectRatio(1f)
            .padding(4.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .border(2.dp, Color.White, RoundedCornerShape(12.dp))
            .clickable(enabled = gameState == GameState.USER_INPUT && emoji.isNotEmpty()) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        if (emoji.isNotEmpty()) {
            Text(
                text = emoji,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.graphicsLayer(
                    alpha = alpha,
                    scaleX = scale,
                    scaleY = scale
                )
            )
        }
    }
}

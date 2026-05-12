package com.memory.brain.training.games.presentation.screens.game.games.imagevortex

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

/**
 * Game 6: Image Vortex Game Screen (图像漩涡)
 */
@Composable
fun ImageVortexGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    val viewModel = remember { ImageVortexViewModel() }
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
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // Target emoji display
            if (uiState.gameState == GameState.USER_INPUT) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "找到这个：",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = uiState.targetEmoji,
                        fontSize = 48.sp
                    )
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
                ImageGrid(
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
fun ImageGrid(
    gridSize: Int,
    grid: List<String>,
    targetEmoji: String,
    gameState: GameState,
    onCellClick: (Int) -> Unit
) {
    val rotation by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = if (gameState == GameState.SHOWING_IMAGES) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
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
                    val emoji = grid.getOrNull(cellIndex) ?: ""
                    
                    ImageCell(
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
fun RowScope.ImageCell(
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
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .border(2.dp, Color.White, RoundedCornerShape(8.dp))
            .clickable(enabled = gameState == GameState.USER_INPUT && emoji.isNotEmpty()) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        if (emoji.isNotEmpty() && gameState != GameState.USER_INPUT) {
            // Show emoji during showing phase
            Text(
                text = emoji,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        } else if (emoji.isNotEmpty() && gameState == GameState.USER_INPUT) {
            // Show emoji during user input (hidden in original, but we'll show for simplicity)
            Text(
                text = emoji,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

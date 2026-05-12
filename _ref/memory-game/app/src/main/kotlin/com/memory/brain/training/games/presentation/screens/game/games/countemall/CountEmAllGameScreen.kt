package com.memory.brain.training.games.presentation.screens.game.games.countemall

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
 * Game 19: Count'em All Game Screen (数一数)
 */
@Composable
fun CountEmAllGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    val viewModel = remember { CountEmAllViewModel() }
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
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (uiState.gameState) {
                GameState.SHOWING_ITEMS, GameState.HIDING -> {
                    ItemsGrid(
                        gridSize = uiState.gridSize,
                        grid = uiState.grid,
                        gameState = uiState.gameState
                    )
                }
                GameState.SHOWING_OPTIONS, GameState.SUCCESS_FEEDBACK, GameState.FAILURE_FEEDBACK -> {
                    OptionsDialog(
                        options = uiState.options,
                        correctCount = uiState.correctCount,
                        gameState = uiState.gameState,
                        onOptionClick = { viewModel.onOptionClick(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemsGrid(
    gridSize: Int,
    grid: List<String>,
    gameState: GameState
) {
    val alpha by animateFloatAsState(
        targetValue = if (gameState == GameState.HIDING) 0f else 1f,
        animationSpec = tween(300)
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
                    
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                            .border(2.dp, Color.White, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (emoji.isNotEmpty()) {
                            Text(
                                text = emoji,
                                fontSize = 32.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.graphicsLayer(alpha = alpha)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OptionsDialog(
    options: List<Int>,
    correctCount: Int,
    gameState: GameState,
    onOptionClick: (Int) -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = when (gameState) {
            GameState.SUCCESS_FEEDBACK, GameState.FAILURE_FEEDBACK -> 0f
            else -> 1f
        },
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "有多少个？",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Options grid (2x2)
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OptionButton(
                        option = options[0],
                        correctCount = correctCount,
                        gameState = gameState,
                        onClick = { onOptionClick(options[0]) },
                        modifier = Modifier.weight(1f)
                    )
                    OptionButton(
                        option = options[1],
                        correctCount = correctCount,
                        gameState = gameState,
                        onClick = { onOptionClick(options[1]) },
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OptionButton(
                        option = options[2],
                        correctCount = correctCount,
                        gameState = gameState,
                        onClick = { onOptionClick(options[2]) },
                        modifier = Modifier.weight(1f)
                    )
                    OptionButton(
                        option = options[3],
                        correctCount = correctCount,
                        gameState = gameState,
                        onClick = { onOptionClick(options[3]) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun OptionButton(
    option: Int,
    correctCount: Int,
    gameState: GameState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isCorrect = option == correctCount
    val backgroundColor = when {
        gameState == GameState.FAILURE_FEEDBACK && isCorrect -> Color(0xFF4CAF50)
        else -> Color(0xFF2196F3)
    }
    
    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(12.dp),
        enabled = gameState == GameState.SHOWING_OPTIONS
    ) {
        Text(
            text = option.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

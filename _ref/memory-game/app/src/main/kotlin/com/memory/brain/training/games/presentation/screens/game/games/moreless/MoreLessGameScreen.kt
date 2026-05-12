package com.memory.brain.training.games.presentation.screens.game.games.moreless

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

@Composable
fun MoreLessGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: MoreLessViewModel = viewModel()
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
        lives = 0, // No lives in this game
        showProgressBar = true,
        progressBarProgress = (uiState.timeRemaining / if (uiState.level <= 10) 6000f else 10000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Expression cards with slide animation
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left expression
                    ExpressionCard(
                        expression = uiState.expression1,
                        isSelected = uiState.selectedAnswer == ComparisonResult.GREATER,
                        isCorrect = uiState.correctAnswer == ComparisonResult.GREATER,
                        showFeedback = uiState.showFeedback,
                        onClick = { viewModel.onAnswerSelected(ComparisonResult.GREATER) },
                        enabled = uiState.gameState == GameState.PLAYING && !uiState.showFeedback,
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Equals sign
                    EqualsSign(
                        isSelected = uiState.selectedAnswer == ComparisonResult.EQUAL,
                        isCorrect = uiState.correctAnswer == ComparisonResult.EQUAL,
                        showFeedback = uiState.showFeedback,
                        onClick = { viewModel.onAnswerSelected(ComparisonResult.EQUAL) },
                        enabled = uiState.gameState == GameState.PLAYING && !uiState.showFeedback
                    )
                    
                    // Right expression
                    ExpressionCard(
                        expression = uiState.expression2,
                        isSelected = uiState.selectedAnswer == ComparisonResult.LESS,
                        isCorrect = uiState.correctAnswer == ComparisonResult.LESS,
                        showFeedback = uiState.showFeedback,
                        onClick = { viewModel.onAnswerSelected(ComparisonResult.LESS) },
                        enabled = uiState.gameState == GameState.PLAYING && !uiState.showFeedback,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ExpressionCard(
    expression: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    showFeedback: Boolean,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    // Slide in animation
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(expression) {
        visible = false
        kotlinx.coroutines.delay(50)
        visible = true
    }
    
    val offsetX by animateFloatAsState(
        targetValue = if (visible) 0f else -100f,
        animationSpec = tween(durationMillis = 300),
        label = "slideIn"
    )
    
    val scale by animateFloatAsState(
        targetValue = if (isSelected && showFeedback) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )
    
    val backgroundColor = when {
        showFeedback && isSelected && isCorrect -> Color(0xFF4CAF50)
        showFeedback && isSelected && !isCorrect -> Color(0xFFF44336)
        showFeedback && !isSelected && isCorrect -> Color(0xFF66BB6A)
        else -> Color(0xFF2196F3)
    }
    
    Box(
        modifier = modifier
            .offset(x = offsetX.dp)
            .scale(scale)
            .aspectRatio(1.2f)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable(enabled = enabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = expression,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun EqualsSign(
    isSelected: Boolean,
    isCorrect: Boolean,
    showFeedback: Boolean,
    onClick: () -> Unit,
    enabled: Boolean
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected && showFeedback) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )
    
    val backgroundColor = when {
        showFeedback && isSelected && isCorrect -> Color(0xFF4CAF50)
        showFeedback && isSelected && !isCorrect -> Color(0xFFF44336)
        showFeedback && !isSelected && isCorrect -> Color(0xFF66BB6A)
        else -> Color(0xFFFFFFFF)
    }
    
    val textColor = when {
        showFeedback && (isSelected || isCorrect) -> Color.White
        else -> Color(0xFF2196F3)
    }
    
    Box(
        modifier = Modifier
            .size(60.dp)
            .scale(scale)
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .clickable(enabled = enabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "=",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

package com.memory.brain.training.games.presentation.screens.game.games.colors

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

/**
 * Colors Game Screen - Stroop Effect Game
 * Based on Game20ColorsActivity.java
 * 
 * Game Features:
 * - Show color word in a color
 * - Player judges if word matches color
 * - Two buttons: Match / Don't Match
 * - 30 second timer
 * - No lives system
 * - Shows "CORRECT!" hint on success
 */
@Composable
fun ColorsGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: ColorsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    
    // Handle game over
    LaunchedEffect(uiState.isGameOver) {
        if (uiState.isGameOver) {
            // Calculate stars (simplified)
            val stars = when {
                uiState.score >= 80 -> 3
                uiState.score >= 40 -> 2
                uiState.score >= 20 -> 1
                else -> 0
            }
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
        lives = 0, // No lives in this game
        showProgressBar = true,
        progressBarProgress = (uiState.timeRemaining / 30000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Color word display
                ColorWordDisplay(
                    word = uiState.currentWord,
                    color = uiState.currentColor
                )
                
                Spacer(modifier = Modifier.height(48.dp))
                
                // Answer buttons
                AnswerButtons(
                    onMatchClick = { viewModel.onMatchClicked() },
                    onDontMatchClick = { viewModel.onDontMatchClicked() },
                    enabled = !uiState.isGameOver && uiState.timeRemaining > 0
                )
            }
            
            // "CORRECT!" hint overlay
            CorrectHintOverlay(visible = uiState.showCorrectHint)
        }
    }
}

/**
 * Display the color word in a specific color
 */
@Composable
fun ColorWordDisplay(
    word: String,
    color: Color
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 64.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = word,
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

/**
 * Two answer buttons: Match / Don't Match
 */
@Composable
fun AnswerButtons(
    onMatchClick: () -> Unit,
    onDontMatchClick: () -> Unit,
    enabled: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Match button
        Button(
            onClick = onMatchClick,
            enabled = enabled,
            modifier = Modifier
                .weight(1f)
                .height(72.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                disabledContainerColor = Color(0xFFBDBDBD)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "匹配",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        
        // Don't Match button
        Button(
            onClick = onDontMatchClick,
            enabled = enabled,
            modifier = Modifier
                .weight(1f)
                .height(72.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF44336),
                disabledContainerColor = Color(0xFFBDBDBD)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "不匹配",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

/**
 * "CORRECT!" hint overlay with animation
 */
@Composable
fun CorrectHintOverlay(visible: Boolean) {
    // Scale animation
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "correctHintScale"
    )
    
    // Alpha animation
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "correctHintAlpha"
    )
    
    if (visible || scale > 0f) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .scale(scale)
                    .padding(32.dp),
                shape = RoundedCornerShape(24.dp),
                color = Color(0xFF4CAF50).copy(alpha = alpha),
                shadowElevation = 16.dp
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 48.dp, vertical = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "正确！",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

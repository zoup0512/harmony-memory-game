package com.memory.brain.training.games.presentation.screens.game.games.correctly

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen

@Composable
fun CorrectlyGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: CorrectlyViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Handle game over
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == CorrectlyState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    BaseGameScreen(
        game = uiState.game,
        score = uiState.score,
        level = uiState.level,
        lives = uiState.lives,
        timeRemaining = uiState.totalTimeRemaining,
        showProgressBar = false,
        progressBarProgress = 0f,
        showPauseButton = false,
        onNavigateBack = onNavigateBack,
        onPauseClick = { }
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
                // Question timer
                QuestionTimer(
                    timeRemaining = uiState.questionTimeRemaining,
                    gameState = uiState.gameState
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Equation display
                EquationDisplay(
                    equation = uiState.equation,
                    gameState = uiState.gameState
                )
                
                Spacer(modifier = Modifier.height(48.dp))
                
                // Answer buttons
                AnswerButtons(
                    gameState = uiState.gameState,
                    onCorrectClick = { viewModel.onAnswerClick(true) },
                    onWrongClick = { viewModel.onAnswerClick(false) }
                )
            }
        }
    }
}

@Composable
fun QuestionTimer(
    timeRemaining: Int,
    gameState: CorrectlyState
) {
    val color = when {
        timeRemaining <= 2 -> Color(0xFFF44336) // Red
        timeRemaining <= 4 -> Color(0xFFFFC107) // Amber
        else -> Color(0xFF4CAF50) // Green
    }
    
    // Pulse animation when time is low
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (timeRemaining <= 2) 1.1f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )
    
    Card(
        modifier = Modifier
            .size(80.dp)
            .scale(if (timeRemaining <= 2) pulseScale else 1f),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(40.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = timeRemaining.toString(),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

@Composable
fun EquationDisplay(
    equation: Equation,
    gameState: CorrectlyState
) {
    val backgroundColor = when (gameState) {
        CorrectlyState.SHOW_SUCCESS -> Color(0xFF4CAF50).copy(alpha = 0.1f)
        CorrectlyState.SHOW_FAILURE -> Color(0xFFF44336).copy(alpha = 0.1f)
        else -> Color(0xFF673AB7).copy(alpha = 0.1f)
    }
    
    val textColor = when (gameState) {
        CorrectlyState.SHOW_SUCCESS -> Color(0xFF4CAF50)
        CorrectlyState.SHOW_FAILURE -> Color(0xFFF44336)
        else -> Color(0xFF673AB7)
    }
    
    // Scale animation for feedback
    val scale by animateFloatAsState(
        targetValue = when (gameState) {
            CorrectlyState.SHOW_SUCCESS -> 1.1f
            CorrectlyState.SHOW_FAILURE -> 0.95f
            else -> 1f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "equation_scale"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Equation text
            Text(
                text = equation.text,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                lineHeight = 56.sp
            )
            
            // Feedback icon
            AnimatedVisibility(
                visible = gameState == CorrectlyState.SHOW_SUCCESS || 
                         gameState == CorrectlyState.SHOW_FAILURE,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Icon(
                    imageVector = if (gameState == CorrectlyState.SHOW_SUCCESS) {
                        Icons.Default.CheckCircle
                    } else {
                        Icons.Default.Cancel
                    },
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(48.dp)
                )
            }
        }
    }
}

@Composable
fun AnswerButtons(
    gameState: CorrectlyState,
    onCorrectClick: () -> Unit,
    onWrongClick: () -> Unit
) {
    val enabled = gameState == CorrectlyState.PLAYING
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Correct button
        Button(
            onClick = onCorrectClick,
            modifier = Modifier
                .weight(1f)
                .height(80.dp),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                disabledContainerColor = Color(0xFF4CAF50).copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "正确",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "正确",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        // Wrong button
        Button(
            onClick = onWrongClick,
            modifier = Modifier
                .weight(1f)
                .height(80.dp),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF44336),
                disabledContainerColor = Color(0xFFF44336).copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "错误",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "错误",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

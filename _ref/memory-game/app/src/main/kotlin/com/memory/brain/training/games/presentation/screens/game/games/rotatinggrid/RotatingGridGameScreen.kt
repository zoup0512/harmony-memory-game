package com.memory.brain.training.games.presentation.screens.game.games.rotatinggrid

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen

@Composable
fun RotatingGridGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: RotatingGridViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Handle game over
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == RotatingGridState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    BaseGameScreen(
        game = uiState.game,
        score = uiState.score,
        level = uiState.level,
        lives = uiState.lives,
        timeRemaining = uiState.timeRemaining,
        showProgressBar = false,
        progressBarProgress = 0f,
        showPauseButton = true,
        onNavigateBack = onNavigateBack,
        onPauseClick = { /* TODO: Implement pause */ }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Main game content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Game state indicator
                GameStateIndicator(
                    gameState = uiState.gameState,
                    rotationAngle = uiState.rotationAngle
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Rotating Grid
                RotatingGrid(
                    gridSize = uiState.gridSize,
                    pattern = uiState.pattern,
                    userSelections = uiState.userSelections,
                    gameState = uiState.gameState,
                    rotationAngle = uiState.rotationAngle,
                    onCellClick = { position -> viewModel.onCellClick(position) }
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Submit button
                AnimatedVisibility(
                    visible = uiState.gameState == RotatingGridState.PLAYER_TURN,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Button(
                        onClick = { viewModel.submitAnswer() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        enabled = uiState.userSelections.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF009688)
                        )
                    ) {
                        Text(
                            "提交答案",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GameStateIndicator(
    gameState: RotatingGridState,
    rotationAngle: Float
) {
    val (text, color) = when (gameState) {
        RotatingGridState.LOADING -> "加载中..." to Color.Gray
        RotatingGridState.READY -> "准备好！" to Color(0xFF4CAF50)
        RotatingGridState.GRID_ANIMATION -> "注意..." to Color(0xFF2196F3)
        RotatingGridState.SHOW_CHALLENGE -> "记住这些位置！" to Color(0xFFFFC107)
        RotatingGridState.ROTATING -> "网格旋转中... ${rotationAngle.toInt()}°" to Color(0xFF009688)
        RotatingGridState.PLAYER_TURN -> "选择旋转后的位置" to Color(0xFF4CAF50)
        RotatingGridState.LEVEL_COMPLETE -> "正确！" to Color(0xFF4CAF50)
        RotatingGridState.SHOW_FAILURE -> "错误！" to Color(0xFFF44336)
        RotatingGridState.GAME_OVER -> "游戏结束" to Color(0xFFF44336)
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (gameState == RotatingGridState.ROTATING) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
            }
            
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

@Composable
fun RotatingGrid(
    gridSize: Int,
    pattern: List<Int>,
    userSelections: List<Int>,
    gameState: RotatingGridState,
    rotationAngle: Float,
    onCellClick: (Int) -> Unit
) {
    // Animate rotation
    val animatedRotation by animateFloatAsState(
        targetValue = if (gameState == RotatingGridState.ROTATING || 
                          gameState == RotatingGridState.PLAYER_TURN ||
                          gameState == RotatingGridState.LEVEL_COMPLETE ||
                          gameState == RotatingGridState.SHOW_FAILURE) {
            rotationAngle
        } else {
            0f
        },
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        ),
        label = "grid_rotation"
    )
    
    // Animate scale for grid animation state
    val animatedScale by animateFloatAsState(
        targetValue = if (gameState == RotatingGridState.GRID_ANIMATION) 1f else 0.95f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "grid_scale"
    )
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .rotate(animatedRotation)
            .scale(animatedScale),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (row in 0 until gridSize) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (col in 0 until gridSize) {
                        val position = row * gridSize + col
                        GridCell(
                            position = position,
                            isInPattern = position in pattern,
                            isSelected = position in userSelections,
                            gameState = gameState,
                            onClick = { onCellClick(position) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GridCell(
    position: Int,
    isInPattern: Boolean,
    isSelected: Boolean,
    gameState: RotatingGridState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val showPattern = gameState == RotatingGridState.SHOW_CHALLENGE && isInPattern
    val showSelection = gameState == RotatingGridState.PLAYER_TURN && isSelected
    val showSuccess = gameState == RotatingGridState.LEVEL_COMPLETE && isInPattern
    val showFailure = gameState == RotatingGridState.SHOW_FAILURE && isInPattern
    
    val backgroundColor = when {
        showSuccess -> Color(0xFF4CAF50)
        showFailure -> Color(0xFFF44336)
        showPattern -> Color(0xFF009688)
        showSelection -> Color(0xFF2196F3)
        else -> Color(0xFFE0E0E0)
    }
    
    val scale by animateFloatAsState(
        targetValue = when {
            showSelection || showPattern -> 0.9f
            showSuccess -> 1.05f
            else -> 1f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "cell_scale"
    )
    
    // Pulse animation for pattern cells
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )
    
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .scale(scale)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor.copy(alpha = if (showPattern) pulseAlpha else 1f))
            .border(
                width = 2.dp,
                color = when {
                    showSelection || showPattern -> Color.White
                    showSuccess -> Color(0xFF2E7D32)
                    showFailure -> Color(0xFFC62828)
                    else -> Color.Transparent
                },
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(
                enabled = gameState == RotatingGridState.PLAYER_TURN,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        // Show icon for selected/pattern cells
        if (showPattern || showSelection || showSuccess || showFailure) {
            Icon(
                imageVector = when {
                    showSuccess -> Icons.Default.CheckCircle
                    showFailure -> Icons.Default.Cancel
                    else -> Icons.Default.Circle
                },
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

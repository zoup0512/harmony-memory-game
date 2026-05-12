package com.memory.brain.training.games.presentation.screens.game.games.memorygrid

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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.TimerOverlay
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

/**
 * Memory Grid Game Screen - Based on Game1MemoryGridActivity.java
 * 
 * Game Features:
 * - Grid size increases with level (3x3 to 8x8)
 * - Win cells increase with level
 * - 3 lives system
 * - Scoring: 16 + (level * 2)
 * - Game flow: Ready → GridAnimation → ShowChallenge → UserInput
 */
@Composable
fun MemoryGridGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: MemoryGridViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    
    // Handle game over
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == GameFlowState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    // 暂停对话框
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
        onPauseClick = { viewModel.pauseGame() },  // 实现暂停功能
        timerOverlay = {
            // 倒计时覆盖层（Ready阶段）
            if (uiState.showReadyTimer) {
                TimerOverlay(
                    visible = true,
                    timerText = if (uiState.readyTimerSeconds > 0) 
                        uiState.readyTimerSeconds.toString() 
                        else "✓",
                    hintText = "准备好！",
                    showCheckmark = uiState.readyTimerSeconds == 0
                )
            }
        }
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
                // Game state indicator
                GameStateIndicator(gameState = uiState.gameState)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Memory Grid
                MemoryGrid(
                    gridSize = uiState.gridSize,
                    pattern = uiState.pattern,
                    userSelections = uiState.userSelections,
                    gameState = uiState.gameState,
                    onCellClick = { position -> viewModel.onCellClick(position) }
                )
            }
        }
    }
}

/**
 * Game state indicator
 */
@Composable
fun GameStateIndicator(gameState: GameFlowState) {
    val text = when (gameState) {
        GameFlowState.READY -> "准备好！"
        GameFlowState.GRID_ANIMATION -> "注意..."
        GameFlowState.SHOW_CHALLENGE -> "记住这些位置！"
        GameFlowState.USER_INPUT -> "点击你记住的位置"
        GameFlowState.SUCCESS_ANIMATION -> "正确！"
        GameFlowState.FAILURE_ANIMATION -> "错误！"
        GameFlowState.GAME_OVER -> "游戏结束"
    }
    
    val color = when (gameState) {
        GameFlowState.READY -> Color(0xFF2196F3)
        GameFlowState.GRID_ANIMATION -> Color(0xFF9C27B0)
        GameFlowState.SHOW_CHALLENGE -> Color(0xFFFFC107)
        GameFlowState.USER_INPUT -> Color(0xFF4CAF50)
        GameFlowState.SUCCESS_ANIMATION -> Color(0xFF4CAF50)
        GameFlowState.FAILURE_ANIMATION -> Color(0xFFF44336)
        GameFlowState.GAME_OVER -> Color(0xFFF44336)
    }
    
    // Scale animation for state changes
    val scale by animateFloatAsState(
        targetValue = when (gameState) {
            GameFlowState.SUCCESS_ANIMATION -> 1.2f
            GameFlowState.FAILURE_ANIMATION -> 1.2f
            else -> 1f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "stateIndicatorScale"
    )
    
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale),
        shape = RoundedCornerShape(16.dp),
        color = color.copy(alpha = 0.1f),
        shadowElevation = 4.dp
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = color,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

/**
 * Memory Grid Component
 */
@Composable
fun MemoryGrid(
    gridSize: Int,
    pattern: List<Int>,
    userSelections: List<Int>,
    gameState: GameFlowState,
    onCellClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (row in 0 until gridSize) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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

/**
 * Grid Cell Component
 */
@Composable
fun GridCell(
    position: Int,
    isInPattern: Boolean,
    isSelected: Boolean,
    gameState: GameFlowState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Determine if cell should be highlighted
    val showPattern = (gameState == GameFlowState.SHOW_CHALLENGE || 
                      gameState == GameFlowState.SUCCESS_ANIMATION ||
                      gameState == GameFlowState.FAILURE_ANIMATION) && isInPattern
    val showSelection = gameState == GameFlowState.USER_INPUT && isSelected
    
    // Cell background color
    val backgroundColor = when {
        showPattern -> Color(0xFF4CAF50)
        showSelection -> Color(0xFF2196F3)
        else -> Color(0xFFE0E0E0)
    }
    
    // Animation for cell appearance (grid animation state)
    val cellAlpha by animateFloatAsState(
        targetValue = if (gameState == GameFlowState.GRID_ANIMATION) 1f else 1f,
        animationSpec = tween(durationMillis = 400),
        label = "cellAlpha"
    )
    
    // Scale animation for selection/pattern
    val scale by animateFloatAsState(
        targetValue = if (showSelection || showPattern) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "cellScale"
    )
    
    // Pulse animation for success
    val pulseScale by animateFloatAsState(
        targetValue = if (gameState == GameFlowState.SUCCESS_ANIMATION && isInPattern) 1.1f else 1f,
        animationSpec = repeatable(
            iterations = 2,
            animation = tween(durationMillis = 200),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cellPulse"
    )
    
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .scale(scale * pulseScale)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(
                width = 2.dp,
                color = if (showSelection || showPattern) Color.White else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(
                enabled = gameState == GameFlowState.USER_INPUT,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        // Show checkmark for selected/pattern cells
        AnimatedVisibility(
            visible = showPattern || showSelection,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

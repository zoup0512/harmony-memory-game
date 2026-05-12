package com.memory.brain.training.games.presentation.screens.game.games.likeprevious

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun LikePreviousGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: LikePreviousViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    
    LaunchedEffect(Unit) {
        viewModel.startGame()
    }
    
    // Handle game over
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == GameState.GAME_OVER) {
            val score = (uiState.level - 1) * 100
            val stars = when {
                uiState.level >= 20 -> 3
                uiState.level >= 10 -> 2
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
        lives = uiState.lives,
        showProgressBar = true,
        progressBarProgress = (uiState.timeRemaining / 30000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Previous element (if exists)
            if (uiState.showPreviousElement && uiState.previousElement != null) {
                Text(
                    text = "之前的:",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Surface(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ShapeView(
                            shape = uiState.previousElement!!.shape,
                            color = uiState.previousElement!!.color,
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Current element
            Text(
                text = "当前的:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            uiState.currentElement?.let { element ->
                Surface(
                    modifier = Modifier
                        .size(180.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 8.dp
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ShapeView(
                            shape = element.shape,
                            color = element.color,
                            modifier = Modifier.size(120.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Answer buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AnswerButton(
                    text = "相同",
                    onClick = { viewModel.onAnswerSelected(true) },
                    enabled = uiState.gameState == GameState.PLAYING && !uiState.showFeedback && uiState.level > 1,
                    isCorrect = if (uiState.showFeedback) uiState.isCorrectAnswer else null,
                    modifier = Modifier.weight(1f)
                )
                
                AnswerButton(
                    text = "不同",
                    onClick = { viewModel.onAnswerSelected(false) },
                    enabled = uiState.gameState == GameState.PLAYING && !uiState.showFeedback && uiState.level > 1,
                    isCorrect = if (uiState.showFeedback) !uiState.isCorrectAnswer else null,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ShapeView(
    shape: LikePreviousViewModel.Shape,
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        when (shape) {
            LikePreviousViewModel.Shape.CIRCLE -> drawCircle(color)
            LikePreviousViewModel.Shape.SQUARE -> drawRect(color, size = size)
            LikePreviousViewModel.Shape.TRIANGLE -> drawTriangle(color)
            LikePreviousViewModel.Shape.DIAMOND -> drawDiamond(color)
            LikePreviousViewModel.Shape.STAR -> drawStar(color)
            LikePreviousViewModel.Shape.HEXAGON -> drawHexagon(color)
            LikePreviousViewModel.Shape.HEART -> drawHeart(color)
        }
    }
}

private fun DrawScope.drawTriangle(color: Color) {
    val path = Path().apply {
        moveTo(size.width / 2, 0f)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }
    drawPath(path, color)
}

private fun DrawScope.drawDiamond(color: Color) {
    val path = Path().apply {
        moveTo(size.width / 2, 0f)
        lineTo(size.width, size.height / 2)
        lineTo(size.width / 2, size.height)
        lineTo(0f, size.height / 2)
        close()
    }
    drawPath(path, color)
}

private fun DrawScope.drawStar(color: Color) {
    val path = Path()
    val centerX = size.width / 2
    val centerY = size.height / 2
    val outerRadius = size.minDimension / 2
    val innerRadius = outerRadius * 0.4f
    
    for (i in 0 until 10) {
        val angle = Math.PI / 5 * i - Math.PI / 2
        val radius = if (i % 2 == 0) outerRadius else innerRadius
        val x = centerX + (radius * cos(angle)).toFloat()
        val y = centerY + (radius * sin(angle)).toFloat()
        
        if (i == 0) path.moveTo(x, y)
        else path.lineTo(x, y)
    }
    path.close()
    drawPath(path, color)
}

private fun DrawScope.drawHexagon(color: Color) {
    val path = Path()
    val centerX = size.width / 2
    val centerY = size.height / 2
    val radius = size.minDimension / 2
    
    for (i in 0 until 6) {
        val angle = Math.PI / 3 * i
        val x = centerX + (radius * cos(angle)).toFloat()
        val y = centerY + (radius * sin(angle)).toFloat()
        
        if (i == 0) path.moveTo(x, y)
        else path.lineTo(x, y)
    }
    path.close()
    drawPath(path, color)
}

private fun DrawScope.drawHeart(color: Color) {
    val path = Path()
    val width = size.width
    val height = size.height
    
    path.moveTo(width / 2, height * 0.3f)
    
    // Left curve
    path.cubicTo(
        width / 2, height * 0.15f,
        0f, height * 0.15f,
        0f, height * 0.4f
    )
    path.cubicTo(
        0f, height * 0.6f,
        width / 2, height * 0.8f,
        width / 2, height
    )
    
    // Right curve
    path.cubicTo(
        width / 2, height * 0.8f,
        width, height * 0.6f,
        width, height * 0.4f
    )
    path.cubicTo(
        width, height * 0.15f,
        width / 2, height * 0.15f,
        width / 2, height * 0.3f
    )
    
    path.close()
    drawPath(path, color)
}

@Composable
private fun AnswerButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    isCorrect: Boolean?,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (isCorrect) {
        true -> Color(0xFF4CAF50)
        false -> Color(0xFFF44336)
        null -> Color(0xFF2196F3)
    }
    
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

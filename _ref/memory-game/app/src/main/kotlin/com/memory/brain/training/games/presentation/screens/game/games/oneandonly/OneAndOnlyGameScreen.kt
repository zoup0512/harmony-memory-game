package com.memory.brain.training.games.presentation.screens.game.games.oneandonly

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun OneAndOnlyGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: OneAndOnlyViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Handle game over
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == OneAndOnlyState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    // Calculate progress for timer
    val progress = uiState.timeRemaining / 60f
    
    BaseGameScreen(
        game = uiState.game,
        score = uiState.score,
        level = uiState.level,
        lives = 0, // No lives in this game
        timeRemaining = uiState.timeRemaining,
        showProgressBar = true,
        progressBarProgress = progress,
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
                // Game instruction
                GameInstruction(gameState = uiState.gameState)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Elements grid
                ElementsGrid(
                    elements = uiState.elements,
                    gameState = uiState.gameState,
                    onElementClick = { element -> viewModel.onElementClick(element) }
                )
            }
        }
    }
}

@Composable
fun GameInstruction(gameState: OneAndOnlyState) {
    val (text, color) = when (gameState) {
        OneAndOnlyState.LOADING -> "加载中..." to Color.Gray
        OneAndOnlyState.PLAYING -> "找出唯一不同的元素！" to Color(0xFFF44336)
        OneAndOnlyState.LEVEL_COMPLETE -> "正确！继续..." to Color(0xFF4CAF50)
        OneAndOnlyState.SHOW_FAILURE -> "错误！再试试" to Color(0xFFF44336)
        OneAndOnlyState.GAME_OVER -> "时间到！" to Color(0xFFF44336)
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = color,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun ElementsGrid(
    elements: List<Element>,
    gameState: OneAndOnlyState,
    onElementClick: (Element) -> Unit
) {
    // Calculate grid columns based on number of elements
    val columns = when {
        elements.size <= 4 -> 2
        elements.size <= 9 -> 3
        elements.size <= 16 -> 4
        elements.size <= 25 -> 5
        else -> 6
    }
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(elements) { element ->
            ElementItem(
                element = element,
                gameState = gameState,
                onClick = { onElementClick(element) }
            )
        }
    }
}

@Composable
fun ElementItem(
    element: Element,
    gameState: OneAndOnlyState,
    onClick: () -> Unit
) {
    // Animation for correct answer
    val scale by animateFloatAsState(
        targetValue = if (gameState == OneAndOnlyState.LEVEL_COMPLETE && element.isWin) {
            1.2f
        } else {
            1f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "element_scale"
    )
    
    // Pulse animation for playing state
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )
    
    val finalScale = if (gameState == OneAndOnlyState.PLAYING) {
        scale * pulseScale
    } else {
        scale
    }
    
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .scale(finalScale)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(
                width = 3.dp,
                color = if (gameState == OneAndOnlyState.LEVEL_COMPLETE && element.isWin) {
                    Color(0xFF4CAF50)
                } else {
                    Color(0xFFE0E0E0)
                },
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(
                enabled = gameState == OneAndOnlyState.PLAYING,
                onClick = onClick
            )
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        ElementShape(
            element = element,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ElementShape(
    element: Element,
    modifier: Modifier = Modifier
) {
    val color = element.getColor()
    
    Canvas(modifier = modifier) {
        val canvasSize = size.minDimension
        val shapeSize = canvasSize * 0.8f
        val center = Offset(size.width / 2, size.height / 2)
        
        when (element.shape) {
            Element.SHAPE_CIRCLE -> {
                drawCircle(
                    color = color,
                    radius = shapeSize / 2,
                    center = center
                )
            }
            Element.SHAPE_SQUARE -> {
                drawRect(
                    color = color,
                    topLeft = Offset(
                        center.x - shapeSize / 2,
                        center.y - shapeSize / 2
                    ),
                    size = Size(shapeSize, shapeSize)
                )
            }
            Element.SHAPE_TRIANGLE -> {
                drawTriangle(color, center, shapeSize)
            }
            Element.SHAPE_DIAMOND -> {
                drawDiamond(color, center, shapeSize)
            }
        }
    }
}

/**
 * Draw a triangle shape
 */
private fun DrawScope.drawTriangle(color: Color, center: Offset, size: Float) {
    val path = Path().apply {
        // Top point
        moveTo(center.x, center.y - size / 2)
        // Bottom left
        lineTo(center.x - size / 2, center.y + size / 2)
        // Bottom right
        lineTo(center.x + size / 2, center.y + size / 2)
        // Close path
        close()
    }
    drawPath(path, color)
}

/**
 * Draw a diamond (rotated square) shape
 */
private fun DrawScope.drawDiamond(color: Color, center: Offset, size: Float) {
    val path = Path().apply {
        // Top point
        moveTo(center.x, center.y - size / 2)
        // Right point
        lineTo(center.x + size / 2, center.y)
        // Bottom point
        lineTo(center.x, center.y + size / 2)
        // Left point
        lineTo(center.x - size / 2, center.y)
        // Close path
        close()
    }
    drawPath(path, color)
}

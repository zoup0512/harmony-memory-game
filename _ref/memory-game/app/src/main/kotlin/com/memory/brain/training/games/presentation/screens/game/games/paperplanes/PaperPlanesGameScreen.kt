package com.memory.brain.training.games.presentation.screens.game.games.paperplanes

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun PaperPlanesGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: PaperPlanesViewModel = viewModel()
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Instruction text
            Text(
                text = when (uiState.gameType) {
                    PaperPlanesViewModel.GameType.TYPE_MOVING -> "滑动飞机移动的方向"
                    PaperPlanesViewModel.GameType.TYPE_ORIENTATION -> "滑动飞机朝向的方向"
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            
            // Game canvas
            PaperPlanesCanvas(
                uiState = uiState,
                onSwipe = { direction -> viewModel.onSwipe(direction) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun PaperPlanesCanvas(
    uiState: PaperPlanesUiState,
    onSwipe: (PaperPlanesViewModel.SwipeDirection) -> Unit,
    modifier: Modifier = Modifier
) {
    var dragStart by remember { mutableStateOf<Offset?>(null) }
    
    // Animation time
    val infiniteTransition = rememberInfiniteTransition(label = "planes")
    val animationTime by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10000f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )
    
    // Pre-generate positions outside Canvas
    val cloudPositions = remember {
        List(4) {
            Offset(
                Random.nextFloat() * 800f,
                Random.nextFloat() * 600f
            )
        }
    }
    
    val planeCount = 6
    val planePositions = remember {
        List(planeCount) {
            Offset(
                Random.nextFloat() * 800f,
                Random.nextFloat() * 600f
            )
        }
    }
    
    val planeSpeeds = remember(uiState.differentSpeed) {
        List(planeCount) {
            if (uiState.differentSpeed) {
                Random.nextFloat() * 1000f + 1000f
            } else {
                2000f
            }
        }
    }
    
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        dragStart = offset
                    },
                    onDragEnd = {
                        dragStart?.let { _ ->
                            // Determine swipe direction (handled in onDrag)
                        }
                        dragStart = null
                    },
                    onDrag = { change, _ ->
                        change.consume()
                        dragStart?.let { start ->
                            val current = change.position
                            val dx = current.x - start.x
                            val dy = current.y - start.y
                            
                            // Minimum swipe distance
                            if (abs(dx) > 100 || abs(dy) > 100) {
                                val direction = when {
                                    abs(dx) > abs(dy) -> {
                                        if (dx > 0) PaperPlanesViewModel.SwipeDirection.RIGHT
                                        else PaperPlanesViewModel.SwipeDirection.LEFT
                                    }
                                    else -> {
                                        if (dy > 0) PaperPlanesViewModel.SwipeDirection.DOWN
                                        else PaperPlanesViewModel.SwipeDirection.UP
                                    }
                                }
                                onSwipe(direction)
                                dragStart = null
                            }
                        }
                    }
                )
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            
            // Draw clouds
            cloudPositions.forEach { pos ->
                var cloudX = pos.x % width
                var cloudY = pos.y % height
                
                // Animate clouds if enabled
                uiState.movingClouds?.let { direction ->
                    val cloudSpeed = 4000f
                    val offset = (animationTime % cloudSpeed) / cloudSpeed
                    when (direction) {
                        PaperPlanesViewModel.SwipeDirection.RIGHT -> {
                            cloudX = (pos.x + offset * width) % width
                        }
                        PaperPlanesViewModel.SwipeDirection.LEFT -> {
                            cloudX = (pos.x - offset * width + width) % width
                        }
                        else -> {}
                    }
                }
                
                drawCloud(Offset(cloudX, cloudY))
            }
            
            // Draw paper planes
            planePositions.forEachIndexed { index, pos ->
                var planeX = pos.x % width
                var planeY = pos.y % height
                
                val speed = planeSpeeds[index]
                val offset = (animationTime % speed) / speed
                
                // Animate planes based on moving direction
                when (uiState.movingDirection) {
                    PaperPlanesViewModel.SwipeDirection.RIGHT -> {
                        planeX = (pos.x + offset * width) % width
                    }
                    PaperPlanesViewModel.SwipeDirection.LEFT -> {
                        planeX = (pos.x - offset * width + width) % width
                    }
                    PaperPlanesViewModel.SwipeDirection.DOWN -> {
                        planeY = (pos.y + offset * height) % height
                    }
                    PaperPlanesViewModel.SwipeDirection.UP -> {
                        planeY = (pos.y - offset * height + height) % height
                    }
                }
                
                // Determine plane color and rotation
                val planeColor = when (uiState.gameType) {
                    PaperPlanesViewModel.GameType.TYPE_MOVING -> Color(0xFF00BCD4) // Cyan
                    PaperPlanesViewModel.GameType.TYPE_ORIENTATION -> Color(0xFFFF9800) // Orange
                }
                
                val rotation = when (uiState.orientationDirection) {
                    PaperPlanesViewModel.SwipeDirection.UP -> 0f
                    PaperPlanesViewModel.SwipeDirection.RIGHT -> 90f
                    PaperPlanesViewModel.SwipeDirection.DOWN -> 180f
                    PaperPlanesViewModel.SwipeDirection.LEFT -> 270f
                }
                
                drawPaperPlane(Offset(planeX, planeY), planeColor, rotation)
            }
        }
    }
}

private fun DrawScope.drawCloud(position: Offset) {
    val cloudColor = Color(0xFFB0BEC5)
    
    // Draw simple cloud shape
    drawCircle(
        color = cloudColor,
        radius = 20f,
        center = position + Offset(0f, 10f)
    )
    drawCircle(
        color = cloudColor,
        radius = 25f,
        center = position + Offset(25f, 5f)
    )
    drawCircle(
        color = cloudColor,
        radius = 20f,
        center = position + Offset(50f, 10f)
    )
    drawCircle(
        color = cloudColor,
        radius = 18f,
        center = position + Offset(15f, 15f)
    )
    drawCircle(
        color = cloudColor,
        radius = 18f,
        center = position + Offset(35f, 15f)
    )
}

private fun DrawScope.drawPaperPlane(position: Offset, color: Color, rotation: Float) {
    rotate(rotation, pivot = position + Offset(25f, 25f)) {
        val path = Path().apply {
            // Paper plane shape
            moveTo(position.x + 25f, position.y)
            lineTo(position.x + 50f, position.y + 50f)
            lineTo(position.x + 25f, position.y + 40f)
            lineTo(position.x, position.y + 50f)
            close()
        }
        drawPath(path, color)
    }
}

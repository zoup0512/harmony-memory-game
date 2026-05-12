package com.memory.brain.training.games.presentation.screens.game.games.findall

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

@Composable
fun FindAllGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: FindAllViewModel = viewModel()
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
        progressBarProgress = (uiState.timeRemaining / 60000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Target patterns
            Text(
                text = "找到所有:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                uiState.targetPatterns.forEach { pattern ->
                    TargetPatternView(pattern)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Elements grid
            ElementsGrid(
                elements = uiState.elements,
                gridSize = uiState.gridSize,
                onElementClick = { viewModel.onElementClicked(it) }
            )
        }
    }
}

@Composable
private fun TargetPatternView(pattern: FindAllViewModel.TargetPattern) {
    Surface(
        modifier = Modifier.size(60.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DoubleColorCircle(
                centerColor = pattern.centerColor,
                sideColor = pattern.sideColor,
                size = 50.dp
            )
        }
    }
}

@Composable
private fun ElementsGrid(
    elements: List<FindAllViewModel.Element>,
    gridSize: Pair<Int, Int>,
    onElementClick: (FindAllViewModel.Element) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val cellSize = minOf(
            maxWidth / gridSize.second,
            maxHeight / gridSize.first
        ) * 0.8f
        
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (row in 0 until gridSize.first) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (col in 0 until gridSize.second) {
                        val element = elements.find { it.row == row && it.col == col }
                        if (element != null) {
                            ElementCell(
                                element = element,
                                onClick = { onElementClick(element) },
                                size = cellSize
                            )
                        } else {
                            Spacer(modifier = Modifier.size(cellSize))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ElementCell(
    element: FindAllViewModel.Element,
    onClick: () -> Unit,
    size: androidx.compose.ui.unit.Dp
) {
    val borderColor = when {
        element.isClicked -> Color(0xFF4CAF50)
        else -> Color.Transparent
    }
    
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(4.dp, borderColor, CircleShape)
            .clickable(enabled = !element.isClicked) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        DoubleColorCircle(
            centerColor = element.centerColor,
            sideColor = element.sideColor,
            size = size * 0.9f
        )
    }
}

@Composable
private fun DoubleColorCircle(
    centerColor: Color,
    sideColor: Color,
    size: androidx.compose.ui.unit.Dp
) {
    Canvas(modifier = Modifier.size(size)) {
        val radius = this.size.minDimension / 2
        val center = Offset(this.size.width / 2, this.size.height / 2)
        
        // Draw outer circle (side color)
        drawCircle(
            color = sideColor,
            radius = radius,
            center = center
        )
        
        // Draw inner circle (center color)
        drawCircle(
            color = centerColor,
            radius = radius * 0.5f,
            center = center
        )
    }
}

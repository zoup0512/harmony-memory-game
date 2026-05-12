package com.memory.brain.training.games.presentation.screens.game.games.schultztables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

@Composable
fun SchultzTablesGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: SchultzTablesViewModel = viewModel()
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
        progressBarProgress = (uiState.timeRemaining / 50000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Current number indicator
            Text(
                text = "找到: ${uiState.currentNumber}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Numbers grid (8x6 = 48 numbers)
            NumbersGrid(
                elements = uiState.elements,
                onNumberClick = { viewModel.onNumberClicked(it) },
                showFeedback = uiState.showFeedback,
                isCorrect = uiState.isCorrectAnswer
            )
        }
    }
}

@Composable
private fun NumbersGrid(
    elements: List<SchultzTablesViewModel.NumberElement>,
    onNumberClick: (SchultzTablesViewModel.NumberElement) -> Unit,
    showFeedback: Boolean,
    isCorrect: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 8 rows x 6 columns = 48 cells
        elements.chunked(6).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                row.forEach { element ->
                    NumberCell(
                        element = element,
                        onClick = { onNumberClick(element) },
                        showFeedback = showFeedback,
                        isCorrect = isCorrect
                    )
                }
            }
        }
    }
}

@Composable
private fun NumberCell(
    element: SchultzTablesViewModel.NumberElement,
    onClick: () -> Unit,
    showFeedback: Boolean,
    isCorrect: Boolean
) {
    var scale by remember { mutableStateOf(1f) }
    
    LaunchedEffect(element.isClicked) {
        if (element.isClicked) {
            scale = 0.8f
        }
    }
    
    val borderColor = when {
        element.isClicked -> Color(0xFF4CAF50)
        showFeedback && !isCorrect -> Color(0xFFF44336)
        else -> Color.Transparent
    }
    
    val backgroundColor = if (element.isClicked) {
        element.color.copy(alpha = 0.3f)
    } else {
        element.color
    }
    
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(50.dp)
            .scale(scale)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable(enabled = !element.isClicked) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = element.number.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (element.isClicked) Color.Gray else Color.White
        )
    }
}

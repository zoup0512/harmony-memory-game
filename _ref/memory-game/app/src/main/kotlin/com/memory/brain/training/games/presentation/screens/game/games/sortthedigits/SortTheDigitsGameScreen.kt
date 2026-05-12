package com.memory.brain.training.games.presentation.screens.game.games.sortthedigits

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun SortTheDigitsGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: SortTheDigitsViewModel = viewModel()
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
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
    elements: List<SortTheDigitsViewModel.NumberElement>,
    onNumberClick: (SortTheDigitsViewModel.NumberElement) -> Unit,
    showFeedback: Boolean,
    isCorrect: Boolean
) {
    val gridSize = when (elements.size) {
        3 -> 2
        4 -> 2
        5 -> 3
        else -> 3
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        elements.chunked(gridSize).forEach { row ->
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
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun NumberCell(
    element: SortTheDigitsViewModel.NumberElement,
    onClick: () -> Unit,
    showFeedback: Boolean,
    isCorrect: Boolean
) {
    var scale by remember { mutableStateOf(1f) }
    
    LaunchedEffect(element.isClicked) {
        if (element.isClicked) {
            scale = 0.9f
        }
    }
    
    val borderColor = when {
        element.isClicked -> Color(0xFF4CAF50)
        showFeedback && !isCorrect -> Color(0xFFF44336)
        else -> Color.Transparent
    }
    
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(80.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(element.color)
            .border(4.dp, borderColor, CircleShape)
            .clickable(enabled = !element.isClicked) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = element.number.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

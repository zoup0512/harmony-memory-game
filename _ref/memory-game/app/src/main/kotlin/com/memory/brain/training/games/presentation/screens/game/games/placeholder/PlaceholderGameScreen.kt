package com.memory.brain.training.games.presentation.screens.game.games.placeholder

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen

/**
 * Placeholder screen for games not yet fully implemented
 * Shows basic game info and allows testing the game flow
 */
@Composable
fun PlaceholderGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    val viewModel = remember { PlaceholderGameViewModel(gameId, game?.name ?: "游戏") }
    val uiState by viewModel.uiState.collectAsState()
    
    // Handle game over
    LaunchedEffect(uiState.isPlaying, uiState.lives) {
        if (!uiState.isPlaying && uiState.lives <= 0) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    BaseGameScreen(
        game = game,
        score = uiState.score,
        level = uiState.level,
        lives = uiState.lives,
        onNavigateBack = onNavigateBack,
        onPauseClick = { /* TODO */ }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Game info card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "🎮",
                            fontSize = 64.sp
                        )
                        
                        Text(
                            text = uiState.gameName,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        
                        Divider()
                        
                        Text(
                            text = "此游戏正在开发中",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        
                        Text(
                            text = "Game ID: $gameId",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
                
                // Action button
                if (uiState.isPlaying) {
                    Button(
                        onClick = { viewModel.onAction() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3)
                        )
                    ) {
                        Text(
                            text = "继续游戏",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                // Status text
                Text(
                    text = if (uiState.isPlaying) {
                        "游戏进行中..."
                    } else {
                        "游戏结束"
                    },
                    fontSize = 16.sp,
                    color = if (uiState.isPlaying) Color(0xFF4CAF50) else Color(0xFFF44336),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

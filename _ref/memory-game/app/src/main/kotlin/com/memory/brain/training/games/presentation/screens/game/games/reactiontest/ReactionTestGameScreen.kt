package com.memory.brain.training.games.presentation.screens.game.games.reactiontest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReactionTestGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: ReactionTestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == ReactionTestGameState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.game?.name ?: "反应测试") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    Text(
                        text = "回合: ${uiState.round}/${uiState.totalRounds}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clickable { viewModel.onReact() }
                .background(
                    when (uiState.gameState) {
                        ReactionTestGameState.WAITING -> Color(0xFFE53935)
                        ReactionTestGameState.READY -> Color(0xFF43A047)
                        ReactionTestGameState.TOO_EARLY -> Color(0xFFFF9800)
                        else -> MaterialTheme.colorScheme.background
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                val text = when (uiState.gameState) {
                    ReactionTestGameState.LOADING -> "加载中..."
                    ReactionTestGameState.WAITING -> "等待..."
                    ReactionTestGameState.READY -> "点击！"
                    ReactionTestGameState.TOO_EARLY -> "太早了！"
                    ReactionTestGameState.RESULT -> {
                        val lastTime = uiState.reactionTimes.lastOrNull() ?: 0
                        "${lastTime}ms"
                    }
                    ReactionTestGameState.GAME_OVER -> "游戏结束"
                }
                
                Text(
                    text = text,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                if (uiState.reactionTimes.isNotEmpty()) {
                    val avgTime = uiState.reactionTimes.average().toInt()
                    Text(
                        text = "平均: ${avgTime}ms",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

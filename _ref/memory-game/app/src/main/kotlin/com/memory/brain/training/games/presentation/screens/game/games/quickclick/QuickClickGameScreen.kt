package com.memory.brain.training.games.presentation.screens.game.games.quickclick

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.memory.brain.training.games.presentation.screens.game.common.StatCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickClickGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: QuickClickViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == QuickClickGameState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.game?.name ?: "快速点击") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    Text(
                        text = "分数: ${uiState.score}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // 游戏统计
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard(
                    icon = Icons.Default.TrendingUp,
                    label = "关卡",
                    value = uiState.level.toString(),
                    color = Color(0xFF2196F3)
                )
                
                StatCard(
                    icon = Icons.Default.TouchApp,
                    label = "点击",
                    value = "${uiState.targetClicks}/${uiState.requiredClicks}",
                    color = Color(0xFF4CAF50)
                )
                
                StatCard(
                    icon = Icons.Default.Timer,
                    label = "时间",
                    value = "${uiState.timeRemaining}s",
                    color = Color(0xFFF44336)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 游戏区域
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.large
                    )
            ) {
                if (uiState.targetVisible) {
                    Box(
                        modifier = Modifier
                            .offset(
                                x = (uiState.targetX * 300).dp,
                                y = (uiState.targetY * 400).dp
                            )
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF44336))
                            .clickable { viewModel.onTargetClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.TouchApp,
                            contentDescription = "点击目标",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                
                if (!uiState.targetVisible && uiState.gameState == QuickClickGameState.PLAYING) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "准备...",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

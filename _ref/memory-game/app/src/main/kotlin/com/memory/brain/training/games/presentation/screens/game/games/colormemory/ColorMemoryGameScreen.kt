package com.memory.brain.training.games.presentation.screens.game.games.colormemory

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.memory.brain.training.games.presentation.screens.game.common.StatCard
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorMemoryGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: ColorMemoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == ColorMemoryGameState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    if (uiState.isPaused) {
        PauseDialog(
            gameName = uiState.game?.name ?: "颜色记忆",
            onDismiss = { viewModel.resumeGame() },
            onResume = { viewModel.resumeGame() },
            onRestart = { viewModel.restartGame() },
            onExit = onNavigateBack
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.game?.name ?: "颜色记忆") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.pauseGame() }) {
                        Icon(Icons.Default.Pause, contentDescription = "暂停")
                    }
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "分数: ${uiState.score}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                    icon = Icons.Default.Favorite,
                    label = "生命",
                    value = uiState.lives.toString(),
                    color = Color(0xFFF44336)
                )
                
                StatCard(
                    icon = Icons.Default.Timer,
                    label = "时间",
                    value = "${uiState.timeRemaining}s",
                    color = Color(0xFF4CAF50)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 游戏状态指示器
            val stateText = when (uiState.gameState) {
                ColorMemoryGameState.LOADING -> "加载中..."
                ColorMemoryGameState.SHOWING_SEQUENCE -> "记住颜色顺序！"
                ColorMemoryGameState.INPUT_SEQUENCE -> "按顺序点击颜色"
                ColorMemoryGameState.GAME_OVER -> "游戏结束"
            }
            
            val stateColor = when (uiState.gameState) {
                ColorMemoryGameState.LOADING -> MaterialTheme.colorScheme.onSurface
                ColorMemoryGameState.SHOWING_SEQUENCE -> Color(0xFFFFC107)
                ColorMemoryGameState.INPUT_SEQUENCE -> Color(0xFF4CAF50)
                ColorMemoryGameState.GAME_OVER -> Color(0xFFF44336)
            }
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = stateColor.copy(alpha = 0.1f))
            ) {
                Text(
                    text = stateText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = stateColor,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 颜色序列显示区域
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (uiState.gameState == ColorMemoryGameState.SHOWING_SEQUENCE) {
                        uiState.colorSequence.forEachIndexed { index, color ->
                            val isHighlighted = index == uiState.currentDisplayIndex
                            ColorCircle(
                                color = color,
                                isHighlighted = isHighlighted,
                                size = 60.dp
                            )
                        }
                    } else if (uiState.gameState == ColorMemoryGameState.INPUT_SEQUENCE) {
                        uiState.userSequence.forEach { color ->
                            ColorCircle(
                                color = color,
                                isHighlighted = false,
                                size = 60.dp
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 颜色选择区域
            if (uiState.gameState == ColorMemoryGameState.INPUT_SEQUENCE) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(ColorMemoryViewModel.COLORS) { color ->
                        ColorButton(
                            color = color,
                            onClick = { viewModel.onColorSelected(color) }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ColorCircle(
    color: GameColor,
    isHighlighted: Boolean,
    size: androidx.compose.ui.unit.Dp
) {
    val scale by animateFloatAsState(
        targetValue = if (isHighlighted) 1.2f else 1f,
        label = "color_scale"
    )
    
    Box(
        modifier = Modifier
            .size(size)
            .scale(scale)
            .clip(CircleShape)
            .background(Color(color.colorValue))
            .border(
                width = if (isHighlighted) 4.dp else 2.dp,
                color = if (isHighlighted) Color.White else Color.Gray,
                shape = CircleShape
            )
    )
}

@Composable
fun ColorButton(
    color: GameColor,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(Color(color.colorValue))
            .clickable(onClick = onClick)
            .border(2.dp, Color.Gray, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = color.name,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

package com.memory.brain.training.games.presentation.screens.game.games.numbermemory

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.memory.brain.training.games.presentation.screens.game.common.StatCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberMemoryGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: NumberMemoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == NumberMemoryGameState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.game?.name ?: "数字记忆") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
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
                NumberMemoryGameState.LOADING -> "加载中..."
                NumberMemoryGameState.SHOWING_NUMBER -> "记住这个数字！"
                NumberMemoryGameState.INPUT_NUMBER -> "输入你记住的数字"
                NumberMemoryGameState.GAME_OVER -> "游戏结束"
            }
            
            val stateColor = when (uiState.gameState) {
                NumberMemoryGameState.LOADING -> MaterialTheme.colorScheme.onSurface
                NumberMemoryGameState.SHOWING_NUMBER -> Color(0xFFFFC107)
                NumberMemoryGameState.INPUT_NUMBER -> Color(0xFF4CAF50)
                NumberMemoryGameState.GAME_OVER -> Color(0xFFF44336)
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
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 数字显示区域
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when (uiState.gameState) {
                        NumberMemoryGameState.SHOWING_NUMBER -> {
                            Text(
                                text = uiState.currentNumber,
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        NumberMemoryGameState.INPUT_NUMBER -> {
                            Text(
                                text = if (uiState.userInput.isEmpty()) "?" else uiState.userInput,
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        else -> {
                            Text(
                                text = "准备中...",
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 数字键盘
            AnimatedVisibility(visible = uiState.gameState == NumberMemoryGameState.INPUT_NUMBER) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // 第一行：1 2 3
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 1..3) {
                            NumberButton(
                                number = i.toString(),
                                onClick = { viewModel.onNumberInput(it) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    
                    // 第二行：4 5 6
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 4..6) {
                            NumberButton(
                                number = i.toString(),
                                onClick = { viewModel.onNumberInput(it) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    
                    // 第三行：7 8 9
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 7..9) {
                            NumberButton(
                                number = i.toString(),
                                onClick = { viewModel.onNumberInput(it) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    
                    // 第四行：删除 0 提交
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.onDeleteDigit() },
                            modifier = Modifier
                                .weight(1f)
                                .height(64.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF44336)
                            )
                        ) {
                            Icon(Icons.Default.Backspace, contentDescription = "删除")
                        }
                        
                        NumberButton(
                            number = "0",
                            onClick = { viewModel.onNumberInput(it) },
                            modifier = Modifier.weight(1f)
                        )
                        
                        Button(
                            onClick = { viewModel.submitAnswer() },
                            modifier = Modifier
                                .weight(1f)
                                .height(64.dp),
                            enabled = uiState.userInput.length == uiState.currentNumber.length,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50)
                            )
                        ) {
                            Icon(Icons.Default.Check, contentDescription = "提交")
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun NumberButton(
    number: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick(number) },
        modifier = modifier.height(64.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = number,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

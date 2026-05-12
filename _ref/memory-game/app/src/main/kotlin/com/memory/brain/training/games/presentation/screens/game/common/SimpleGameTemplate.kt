package com.memory.brain.training.games.presentation.screens.game.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import com.memory.brain.training.games.domain.model.Game
import com.memory.brain.training.games.presentation.screens.game.common.StatCard

/**
 * 简单游戏模板 - 用于快速创建游戏界面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleGameTemplate(
    game: Game?,
    score: Int,
    level: Int,
    lives: Int,
    timeRemaining: Int,
    onNavigateBack: () -> Unit,
    gameContent: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(game?.name ?: "游戏") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    Text(
                        text = "分数: $score",
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
                    value = level.toString(),
                    color = Color(0xFF2196F3)
                )
                
                StatCard(
                    icon = Icons.Default.Favorite,
                    label = "生命",
                    value = lives.toString(),
                    color = Color(0xFFF44336)
                )
                
                StatCard(
                    icon = Icons.Default.Timer,
                    label = "时间",
                    value = "${timeRemaining}s",
                    color = Color(0xFF4CAF50)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 游戏内容
            gameContent()
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

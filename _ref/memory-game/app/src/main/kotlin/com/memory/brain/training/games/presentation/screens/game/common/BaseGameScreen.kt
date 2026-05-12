package com.memory.brain.training.games.presentation.screens.game.common

import androidx.compose.foundation.background
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
import com.memory.brain.training.games.domain.model.Game

/**
 * 基础游戏屏幕 - 所有游戏共享的UI结构
 * 完全按照旧项目的main_activity.xml布局
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseGameScreen(
    game: Game?,
    score: Int = 0,
    level: Int = 1,
    lives: Int = 3,
    timeRemaining: Int? = null,
    showProgressBar: Boolean = false,
    progressBarProgress: Float = 0f,
    showPauseButton: Boolean = true,
    showSoundButton: Boolean = false,
    soundEnabled: Boolean = true,
    onNavigateBack: () -> Unit,
    onPauseClick: () -> Unit = {},
    onSoundToggle: (Boolean) -> Unit = {},
    timerOverlay: @Composable (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val backgroundColor = Color(0xFFE5F6FE) // 默认背景色
    
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),  // 处理系统栏 insets
        topBar = {
            // 顶部栏 - 对应 panel_header
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 左侧：暂停/声音按钮
                    Box(
                        modifier = Modifier.size(56.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (showPauseButton) {
                            IconButton(onClick = onPauseClick) {
                                Icon(
                                    imageVector = Icons.Default.Pause,
                                    contentDescription = "暂停",
                                    tint = Color.Black
                                )
                            }
                        }
                        
                        if (showSoundButton) {
                            IconButton(onClick = { onSoundToggle(!soundEnabled) }) {
                                Icon(
                                    imageVector = if (soundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                                    contentDescription = "声音",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    // 中间：游戏名称
                    Text(
                        text = game?.name ?: "",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    // 右侧：生命值
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(lives) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "生命",
                                tint = Color(0xFFF44336),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
        ) {
            // 关卡显示 - 对应 level_number
            Text(
                text = "关卡 $level",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            
            // 进度条 - 对应 progressBar (在线模式)
            if (showProgressBar) {
                LinearProgressIndicator(
                    progress = progressBarProgress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    color = Color(0xFF2196F3),
                    trackColor = Color(0xFFE0E0E0)
                )
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }
            
            // 游戏内容区域 - 对应 grid_container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // 游戏内容
                content()
                
                // 计时器覆盖层 - 对应 timerContainer
                timerOverlay?.invoke()
            }
        }
    }
}

/**
 * 计时器覆盖层组件
 */
@Composable
fun TimerOverlay(
    visible: Boolean,
    timerText: String,
    hintText: String = "准备好！",
    showCheckmark: Boolean = false
) {
    if (visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAAE5F6FE)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 圆形计时器
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = MaterialTheme.shapes.large,
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = timerText,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4CAF50)
                        )
                    }
                }
                
                // 提示文字
                Text(
                    text = hintText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                )
                
                // 对勾图标
                if (showCheckmark) {
                    Surface(
                        modifier = Modifier.size(80.dp),
                        shape = MaterialTheme.shapes.large,
                        color = Color.White,
                        shadowElevation = 4.dp
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "正确",
                                tint = Color(0xFF4CAF50),
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

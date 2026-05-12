package com.memory.brain.training.games.presentation.screens.game.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * 暂停对话框
 * 
 * 提供基础功能：
 * - 继续游戏
 * - 重新开始
 * - 退出游戏
 */
@Composable
fun PauseDialog(
    gameName: String,
    onDismiss: () -> Unit,
    onResume: () -> Unit,
    onRestart: () -> Unit,
    onExit: () -> Unit,
    showRestart: Boolean = true
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 标题
                Text(
                    text = "游戏暂停",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                
                Text(
                    text = gameName,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.LightGray
                )
                
                // 继续游戏按钮
                PauseButton(
                    text = "继续游戏",
                    icon = Icons.Default.PlayArrow,
                    onClick = onResume,
                    backgroundColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                )
                
                // 重新开始按钮
                if (showRestart) {
                    PauseButton(
                        text = "重新开始",
                        icon = Icons.Default.Refresh,
                        onClick = onRestart,
                        backgroundColor = Color(0xFF2196F3),
                        contentColor = Color.White
                    )
                }
                
                // 退出游戏按钮
                PauseButton(
                    text = "退出游戏",
                    icon = Icons.Default.ExitToApp,
                    onClick = onExit,
                    backgroundColor = Color(0xFFF44336),
                    contentColor = Color.White
                )
            }
        }
    }
}

/**
 * 暂停对话框按钮
 */
@Composable
private fun PauseButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color,
    contentColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


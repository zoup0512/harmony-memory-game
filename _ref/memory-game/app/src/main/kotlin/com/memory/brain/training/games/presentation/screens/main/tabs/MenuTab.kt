package com.memory.brain.training.games.presentation.screens.main.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MenuTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "菜单",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Menu items
        MenuItemCard(
            icon = Icons.Default.Person,
            title = "个人资料",
            description = "查看和编辑个人信息",
            onClick = { /* TODO */ }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        MenuItemCard(
            icon = Icons.Default.BarChart,
            title = "统计数据",
            description = "查看游戏统计和进度",
            onClick = { /* TODO */ }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        MenuItemCard(
            icon = Icons.Default.EmojiEvents,
            title = "成就",
            description = "查看已解锁的成就",
            onClick = { /* TODO */ }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        MenuItemCard(
            icon = Icons.Default.Leaderboard,
            title = "排行榜",
            description = "查看全球排名",
            onClick = { /* TODO */ }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        MenuItemCard(
            icon = Icons.Default.Settings,
            title = "设置",
            description = "应用设置和偏好",
            onClick = { /* TODO */ }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        MenuItemCard(
            icon = Icons.Default.Info,
            title = "关于",
            description = "应用信息和帮助",
            onClick = { /* TODO */ }
        )
    }
}

@Composable
fun MenuItemCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

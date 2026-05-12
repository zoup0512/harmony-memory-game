package com.memory.brain.training.games.presentation.screens.game

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.main.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun GameResultScreen(
    gameId: Int,
    score: Int,
    stars: Int,
    onPlayAgain: () -> Unit,
    onBackToMain: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val game = remember { GameDataProvider.getAllGames().find { it.id == gameId } }
    
    // Animate stars
    var showStars by remember { mutableStateOf(false) }
    
    // Add stars and coins when screen is shown
    LaunchedEffect(stars) {
        if (stars > 0) {
            mainViewModel.addStarsAndCoins(stars)
        }
    }
    
    LaunchedEffect(Unit) {
        delay(500)
        showStars = true
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Trophy/Result Icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            stars >= 3 -> Color(0xFFFFD700) // Gold
                            stars >= 2 -> Color(0xFFC0C0C0) // Silver
                            stars >= 1 -> Color(0xFFCD7F32) // Bronze
                            else -> Color.Gray
                        }.copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when {
                        stars >= 3 -> Icons.Default.EmojiEvents
                        stars >= 2 -> Icons.Default.MilitaryTech
                        stars >= 1 -> Icons.Default.WorkspacePremium
                        else -> Icons.Default.SentimentDissatisfied
                    },
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = when {
                        stars >= 3 -> Color(0xFFFFD700)
                        stars >= 2 -> Color(0xFFC0C0C0)
                        stars >= 1 -> Color(0xFFCD7F32)
                        else -> Color.Gray
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Result Title
            Text(
                text = when {
                    stars >= 3 -> "完美！"
                    stars >= 2 -> "很好！"
                    stars >= 1 -> "不错！"
                    else -> "继续努力！"
                },
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Game Name
            Text(
                text = game?.name ?: "游戏",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Stars Display
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(3) { index ->
                    StarIcon(
                        filled = index < stars,
                        animate = showStars,
                        delay = index * 200L
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Score Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "得分",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = score.toString(),
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Rewards
            if (stars > 0) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF9C4)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RewardItem(
                            icon = Icons.Default.Star,
                            value = "+$stars",
                            label = "星星",
                            color = Color(0xFFFFC107)
                        )
                        
                        RewardItem(
                            icon = Icons.Default.MonetizationOn,
                            value = "+${stars * 10}",
                            label = "金币",
                            color = Color(0xFFFFD700)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            // Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onPlayAgain,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Icon(Icons.Default.Replay, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("再玩一次", style = MaterialTheme.typography.titleMedium)
                }
                
                OutlinedButton(
                    onClick = onBackToMain,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Icon(Icons.Default.Home, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("返回主页", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun StarIcon(
    filled: Boolean,
    animate: Boolean,
    delay: Long
) {
    var startAnimation by remember { mutableStateOf(false) }
    
    LaunchedEffect(animate) {
        if (animate) {
            delay(delay)
            startAnimation = true
        }
    }
    
    val scale by animateFloatAsState(
        targetValue = if (startAnimation && filled) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "star_scale"
    )
    
    Icon(
        imageVector = if (filled) Icons.Default.Star else Icons.Default.StarBorder,
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)
            .scale(scale),
        tint = if (filled) Color(0xFFFFC107) else Color.Gray
    )
}

@Composable
fun RewardItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black.copy(alpha = 0.6f)
        )
    }
}

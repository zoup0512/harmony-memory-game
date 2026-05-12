package com.memory.brain.training.games.presentation.screens.main.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.Game
import com.memory.brain.training.games.domain.model.GameCategory

@Composable
fun SprintTab(
    onNavigateToGame: (Int) -> Unit,
    viewModel: SprintViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedCategory by remember { mutableStateOf<GameCategory?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Sprint 模式",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Text(
            text = "选择一个游戏快速开始",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Category Filter
        CategoryFilterRow(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Games List
        val games = if (selectedCategory != null) {
            GameDataProvider.getGamesByCategory(selectedCategory!!)
        } else {
            GameDataProvider.getAllGames()
        }
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(games) { game ->
                GameCard(
                    game = game,
                    onClick = { 
                        onNavigateToGame(game.id)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryFilterRow(
    selectedCategory: GameCategory?,
    onCategorySelected: (GameCategory?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // All categories chip
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onCategorySelected(null) },
            label = { Text("全部") }
        )
        
        // Individual category chips (excluding PROMO)
        GameCategory.values().filter { it != GameCategory.PROMO }.forEach { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(getCategoryName(category)) },
                leadingIcon = {
                    Icon(
                        imageVector = getCategoryIcon(category),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            )
        }
    }
}

@Composable
fun GameCard(
    game: Game,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !game.isLocked, onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Game Icon/Category Color
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(getCategoryColor(game.category)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getCategoryIcon(game.category),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Game Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = game.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                // Category badge
                Text(
                    text = getCategoryName(game.category),
                    style = MaterialTheme.typography.labelSmall,
                    color = getCategoryColor(game.category),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            // Lock icon or play button
            if (game.isLocked) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Locked",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${game.requiredStars}★",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun getCategoryColor(category: GameCategory): Color {
    return when (category) {
        GameCategory.MEMORY -> Color(0xFF03A9F4)
        GameCategory.ATTENTION -> Color(0xFF009688)
        GameCategory.SPEED -> Color(0xFFF44336)
        GameCategory.PROBLEM_SOLVING -> Color(0xFF673AB7)
        GameCategory.FLEXIBILITY -> Color(0xFFFFC107)
        GameCategory.IMAGINATION -> Color(0xFF00BCD4)
        GameCategory.PROMO -> Color.Gray
    }
}

@Composable
private fun getCategoryIcon(category: GameCategory): androidx.compose.ui.graphics.vector.ImageVector {
    return when (category) {
        GameCategory.MEMORY -> Icons.Default.Psychology
        GameCategory.ATTENTION -> Icons.Default.Visibility
        GameCategory.SPEED -> Icons.Default.Speed
        GameCategory.PROBLEM_SOLVING -> Icons.Default.Extension
        GameCategory.FLEXIBILITY -> Icons.Default.SwapHoriz
        GameCategory.IMAGINATION -> Icons.Default.Lightbulb
        GameCategory.PROMO -> Icons.Default.Star
    }
}

private fun getCategoryName(category: GameCategory): String {
    return when (category) {
        GameCategory.MEMORY -> "记忆"
        GameCategory.ATTENTION -> "注意力"
        GameCategory.SPEED -> "速度"
        GameCategory.PROBLEM_SOLVING -> "问题解决"
        GameCategory.FLEXIBILITY -> "灵活性"
        GameCategory.IMAGINATION -> "想象力"
        GameCategory.PROMO -> "推广"
    }
}

@Composable
private fun rememberScrollState(): androidx.compose.foundation.ScrollState {
    return androidx.compose.foundation.rememberScrollState()
}

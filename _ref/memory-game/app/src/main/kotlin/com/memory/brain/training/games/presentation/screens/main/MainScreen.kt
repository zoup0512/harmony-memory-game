package com.memory.brain.training.games.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.memory.brain.training.games.presentation.screens.main.tabs.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToGame: (Int) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(MainTab.SPRINT) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Memory Games") },
                actions = {
                    // Coins display
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(Icons.Default.Star, contentDescription = "Stars")
                            Text("${uiState.stars}")
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(Icons.Default.AccountBalanceWallet, contentDescription = "Coins")
                            Text("${uiState.coins}")
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                MainTab.values().forEach { tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title) },
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                MainTab.SPRINT -> SprintTab(onNavigateToGame = onNavigateToGame)
                MainTab.WORKOUT -> WorkoutTab()
                MainTab.CHALLENGE -> ChallengeTab()
                MainTab.ONLINE -> OnlineTab()
                MainTab.MENU -> MenuTab()
            }
        }
    }
}

enum class MainTab(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    SPRINT("Sprint", Icons.Default.PlayArrow),
    WORKOUT("Workout", Icons.Default.FitnessCenter),
    CHALLENGE("Challenge", Icons.Default.EmojiEvents),
    ONLINE("Online", Icons.Default.People),
    MENU("Menu", Icons.Default.Menu)
}

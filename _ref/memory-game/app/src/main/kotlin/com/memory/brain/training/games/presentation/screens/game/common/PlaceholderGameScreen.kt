package com.memory.brain.training.games.presentation.screens.game.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 占位符游戏ViewModel - 用于快速实现游戏基础功能
 */
@HiltViewModel
class PlaceholderGameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val gameId: Int = savedStateHandle.get<Int>("gameId") ?: 1
    
    private val _uiState = MutableStateFlow(PlaceholderGameUiState())
    val uiState: StateFlow<PlaceholderGameUiState> = _uiState.asStateFlow()
    
    private var countdownJob: Job? = null
    
    init {
        loadGame()
    }
    
    private fun loadGame() {
        val game = GameDataProvider.getAllGames().find { it.id == gameId }
        if (game != null) {
            _uiState.update { it.copy(game = game) }
            startGame()
        }
    }
    
    fun startGame() {
        _uiState.update { 
            it.copy(
                gameState = PlaceholderGameState.PLAYING,
                score = 0,
                level = 1,
                lives = 3,
                timeRemaining = 60
            )
        }
        startCountdown()
    }
    
    fun onAction() {
        val newScore = _uiState.value.score + (100 * _uiState.value.level)
        val newLevel = _uiState.value.level + 1
        
        if (newLevel > 10) {
            endGame()
        } else {
            _uiState.update { 
                it.copy(
                    score = newScore,
                    level = newLevel
                )
            }
        }
    }
    
    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (_uiState.value.timeRemaining > 0 && _uiState.value.gameState == PlaceholderGameState.PLAYING) {
                delay(1000)
                _uiState.update { 
                    val newTime = it.timeRemaining - 1
                    if (newTime <= 0) {
                        it.copy(timeRemaining = 0)
                    } else {
                        it.copy(timeRemaining = newTime)
                    }
                }
                
                if (_uiState.value.timeRemaining <= 0) {
                    endGame()
                }
            }
        }
    }
    
    private fun endGame() {
        countdownJob?.cancel()
        _uiState.update { it.copy(gameState = PlaceholderGameState.GAME_OVER) }
    }
    
    fun calculateStars(): Int {
        val score = _uiState.value.score
        return when {
            score >= 1000 -> 3
            score >= 500 -> 2
            score >= 200 -> 1
            else -> 0
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        countdownJob?.cancel()
    }
}

data class PlaceholderGameUiState(
    val game: Game? = null,
    val gameState: PlaceholderGameState = PlaceholderGameState.LOADING,
    val score: Int = 0,
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Int = 60
)

enum class PlaceholderGameState {
    LOADING,
    PLAYING,
    GAME_OVER
}

/**
 * 占位符游戏界面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: PlaceholderGameViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(uiState.gameState) {
        if (uiState.gameState == PlaceholderGameState.GAME_OVER) {
            val stars = viewModel.calculateStars()
            onGameComplete(uiState.score, stars)
        }
    }
    
    SimpleGameTemplate(
        game = uiState.game,
        score = uiState.score,
        level = uiState.level,
        lives = uiState.lives,
        timeRemaining = uiState.timeRemaining,
        onNavigateBack = onNavigateBack
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = uiState.game?.name ?: "游戏",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "游戏玩法开发中...",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Button(
                        onClick = { viewModel.onAction() },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("完成关卡")
                    }
                }
            }
        }
    }
}

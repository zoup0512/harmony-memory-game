package com.memory.brain.training.games.presentation.screens.game.games.quickclick

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
import kotlin.random.Random

@HiltViewModel
class QuickClickViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val gameId: Int = savedStateHandle.get<Int>("gameId") ?: 8
    
    private val _uiState = MutableStateFlow(QuickClickUiState())
    val uiState: StateFlow<QuickClickUiState> = _uiState.asStateFlow()
    
    private var countdownJob: Job? = null
    private var targetJob: Job? = null
    
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
                gameState = QuickClickGameState.PLAYING,
                score = 0,
                level = 1,
                timeRemaining = 30,
                targetClicks = 0,
                requiredClicks = 10
            )
        }
        spawnTarget()
        startCountdown()
    }
    
    private fun spawnTarget() {
        targetJob?.cancel()
        
        val x = Random.nextFloat()
        val y = Random.nextFloat()
        
        _uiState.update { 
            it.copy(
                targetX = x,
                targetY = y,
                targetVisible = true
            )
        }
        
        // 目标在1-2秒后消失
        targetJob = viewModelScope.launch {
            delay(1500)
            if (_uiState.value.targetVisible) {
                _uiState.update { it.copy(targetVisible = false) }
                delay(300)
                if (_uiState.value.gameState == QuickClickGameState.PLAYING) {
                    spawnTarget()
                }
            }
        }
    }
    
    fun onTargetClick() {
        if (!_uiState.value.targetVisible) return
        
        val newClicks = _uiState.value.targetClicks + 1
        val newScore = _uiState.value.score + (10 * _uiState.value.level)
        
        _uiState.update { 
            it.copy(
                targetClicks = newClicks,
                score = newScore,
                targetVisible = false
            )
        }
        
        if (newClicks >= _uiState.value.requiredClicks) {
            levelUp()
        } else {
            viewModelScope.launch {
                delay(300)
                spawnTarget()
            }
        }
    }
    
    private fun levelUp() {
        val newLevel = _uiState.value.level + 1
        
        if (newLevel > 10) {
            endGame()
        } else {
            _uiState.update { 
                it.copy(
                    level = newLevel,
                    targetClicks = 0,
                    requiredClicks = 10 + (newLevel * 2)
                )
            }
            viewModelScope.launch {
                delay(500)
                spawnTarget()
            }
        }
    }
    
    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (_uiState.value.timeRemaining > 0 && _uiState.value.gameState == QuickClickGameState.PLAYING) {
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
        targetJob?.cancel()
        _uiState.update { it.copy(gameState = QuickClickGameState.GAME_OVER) }
    }
    
    fun calculateStars(): Int {
        val score = _uiState.value.score
        return when {
            score >= 500 -> 3
            score >= 300 -> 2
            score >= 150 -> 1
            else -> 0
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        countdownJob?.cancel()
        targetJob?.cancel()
    }
}

data class QuickClickUiState(
    val game: Game? = null,
    val gameState: QuickClickGameState = QuickClickGameState.LOADING,
    val score: Int = 0,
    val level: Int = 1,
    val timeRemaining: Int = 30,
    val targetClicks: Int = 0,
    val requiredClicks: Int = 10,
    val targetX: Float = 0.5f,
    val targetY: Float = 0.5f,
    val targetVisible: Boolean = false
)

enum class QuickClickGameState {
    LOADING,
    PLAYING,
    GAME_OVER
}

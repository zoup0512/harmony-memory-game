package com.memory.brain.training.games.presentation.screens.game.games.placeholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.domain.model.GameProgression3
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Placeholder ViewModel for games not yet fully implemented
 * Provides basic game loop functionality
 */
class PlaceholderGameViewModel(
    private val gameId: Int,
    private val gameName: String
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(PlaceholderGameUiState())
    val uiState: StateFlow<PlaceholderGameUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression3()
    private var gameJob: Job? = null
    
    init {
        startGame()
    }
    
    fun startGame() {
        progression.startGame()
        
        _uiState.value = PlaceholderGameUiState(
            gameId = gameId,
            gameName = gameName,
            level = progression.getLevelNumber(),
            score = 0,
            lives = 3,
            isPlaying = true
        )
        
        startGameLoop()
    }
    
    private fun startGameLoop() {
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            while (_uiState.value.isPlaying && _uiState.value.lives > 0) {
                delay(2000) // Wait 2 seconds per "round"
                
                // Simulate a round
                val success = Random.nextBoolean()
                
                if (success) {
                    // Success: increase score and level
                    val newScore = _uiState.value.score + (10 + _uiState.value.level * 2)
                    progression.nextLevel()
                    
                    _uiState.value = _uiState.value.copy(
                        score = newScore,
                        level = progression.getLevelNumber()
                    )
                } else {
                    // Failure: lose a life
                    val newLives = _uiState.value.lives - 1
                    
                    if (newLives <= 0) {
                        _uiState.value = _uiState.value.copy(
                            lives = 0,
                            isPlaying = false
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            lives = newLives
                        )
                    }
                }
            }
        }
    }
    
    fun onAction() {
        // Placeholder action - just advance the game
        val newScore = _uiState.value.score + 10
        progression.nextLevel()
        
        _uiState.value = _uiState.value.copy(
            score = newScore,
            level = progression.getLevelNumber()
        )
    }
    
    fun calculateStars(): Int {
        val level = _uiState.value.level
        return when {
            level >= 15 -> 3
            level >= 10 -> 2
            level >= 5 -> 1
            else -> 0
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        gameJob?.cancel()
    }
}

data class PlaceholderGameUiState(
    val gameId: Int = 0,
    val gameName: String = "",
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val isPlaying: Boolean = false
)

package com.memory.brain.training.games.presentation.screens.game.games.hexagons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.domain.model.GameProgression1
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Game 2: Hexagons (六边形)
 * Hexagonal grid memory game - similar to Memory Grid but with hexagonal cells
 * Based on: Game3HexagonsActivity.java
 */
class HexagonsViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(HexagonsUiState())
    val uiState: StateFlow<HexagonsUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression1()
    private var gameJob: Job? = null
    
    init {
        startGame()
    }
    
    fun startGame() {
        progression.startGame()
        startLevel()
    }
    
    private fun startLevel() {
        val gridSize = progression.getCurrentGridSize()
        val winCells = progression.getCurrentWinCells()
        
        // Generate hexagonal grid positions
        val totalCells = gridSize * gridSize
        val challengeCells = generateChallengeCells(totalCells, winCells)
        
        _uiState.value = HexagonsUiState(
            level = progression.getLevelNumber(),
            score = _uiState.value.score,
            lives = _uiState.value.lives,
            gridSize = gridSize,
            challengeCells = challengeCells,
            userSelectedCells = emptySet(),
            gameState = GameState.SHOWING_CHALLENGE,
            isPlaying = true
        )
        
        // Show challenge for 2 seconds, then hide
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            delay(2000)
            _uiState.value = _uiState.value.copy(
                gameState = GameState.USER_INPUT
            )
        }
    }
    
    private fun generateChallengeCells(totalCells: Int, count: Int): Set<Int> {
        val cells = mutableSetOf<Int>()
        while (cells.size < count) {
            cells.add(Random.nextInt(totalCells))
        }
        return cells
    }
    
    fun onCellClick(cellIndex: Int) {
        if (_uiState.value.gameState != GameState.USER_INPUT) return
        
        val currentSelected = _uiState.value.userSelectedCells.toMutableSet()
        
        if (currentSelected.contains(cellIndex)) {
            currentSelected.remove(cellIndex)
        } else {
            currentSelected.add(cellIndex)
        }
        
        _uiState.value = _uiState.value.copy(
            userSelectedCells = currentSelected
        )
        
        // Check if user has selected enough cells
        if (currentSelected.size == _uiState.value.challengeCells.size) {
            checkAnswer()
        }
    }
    
    private fun checkAnswer() {
        val isCorrect = _uiState.value.userSelectedCells == _uiState.value.challengeCells
        
        if (isCorrect) {
            handleSuccess()
        } else {
            handleFailure()
        }
    }
    
    private fun handleSuccess() {
        val scoreGain = 16 + (progression.getLevelNumber() * 2)
        val newScore = _uiState.value.score + scoreGain
        
        _uiState.value = _uiState.value.copy(
            score = newScore,
            gameState = GameState.SUCCESS_FEEDBACK
        )
        
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            delay(500)
            progression.nextLevel()
            startLevel()
        }
    }
    
    private fun handleFailure() {
        val newLives = _uiState.value.lives - 1
        
        _uiState.value = _uiState.value.copy(
            lives = newLives,
            gameState = GameState.FAILURE_FEEDBACK
        )
        
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            delay(1000)
            
            if (newLives <= 0) {
                _uiState.value = _uiState.value.copy(
                    isPlaying = false
                )
            } else {
                startLevel()
            }
        }
    }
    
    fun pauseGame() {
        gameJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        // No need to restart timer for this game
    }
    
    fun restartGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        startGame()
    }
    
    fun calculateStars(): Int {
        val level = progression.getLevelNumber()
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

data class HexagonsUiState(
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val gridSize: Int = 3,
    val challengeCells: Set<Int> = emptySet(),
    val userSelectedCells: Set<Int> = emptySet(),
    val gameState: GameState = GameState.SHOWING_CHALLENGE,
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    SHOWING_CHALLENGE,
    USER_INPUT,
    SUCCESS_FEEDBACK,
    FAILURE_FEEDBACK
}

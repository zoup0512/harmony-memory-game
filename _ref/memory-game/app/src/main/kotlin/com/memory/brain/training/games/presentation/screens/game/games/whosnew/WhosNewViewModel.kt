package com.memory.brain.training.games.presentation.screens.game.games.whosnew

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
 * Game 3: Who's New (谁是新的)
 * Identify the newly added element
 * Based on: Game4WhoNewActivity.java
 */
class WhosNewViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(WhosNewUiState())
    val uiState: StateFlow<WhosNewUiState> = _uiState.asStateFlow()
    
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
        
        // Start with empty cells, will add one by one
        _uiState.value = WhosNewUiState(
            level = progression.getLevelNumber(),
            score = _uiState.value.score,
            lives = _uiState.value.lives,
            gridSize = gridSize,
            highlightedCells = emptySet(),
            newCellIndex = -1,
            gameState = GameState.SHOWING_PATTERN,
            isPlaying = true
        )
        
        // Show pattern by adding cells one by one
        showPattern(winCells)
    }
    
    private fun showPattern(cellCount: Int) {
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            val cells = mutableSetOf<Int>()
            val totalCells = _uiState.value.gridSize * _uiState.value.gridSize
            
            // Add cells one by one
            repeat(cellCount) {
                delay(800)
                var newCell: Int
                do {
                    newCell = Random.nextInt(totalCells)
                } while (cells.contains(newCell))
                
                cells.add(newCell)
                
                _uiState.value = _uiState.value.copy(
                    highlightedCells = cells.toSet(),
                    newCellIndex = newCell
                )
            }
            
            // Hide and add one more cell
            delay(800)
            _uiState.value = _uiState.value.copy(
                gameState = GameState.HIDING
            )
            
            delay(800)
            
            // Add the new cell
            var finalNewCell: Int
            do {
                finalNewCell = Random.nextInt(totalCells)
            } while (cells.contains(finalNewCell))
            
            cells.add(finalNewCell)
            
            _uiState.value = _uiState.value.copy(
                highlightedCells = cells.toSet(),
                newCellIndex = finalNewCell,
                gameState = GameState.USER_INPUT
            )
        }
    }
    
    fun onCellClick(cellIndex: Int) {
        if (_uiState.value.gameState != GameState.USER_INPUT) return
        
        if (cellIndex == _uiState.value.newCellIndex) {
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
        if (_uiState.value.gameState == GameState.USER_INPUT) {
            // Resume from user input state, no need to restart pattern
        } else if (_uiState.value.gameState == GameState.SHOWING_PATTERN) {
            // Continue showing pattern
            val remainingCells = _uiState.value.highlightedCells.size
            val totalCells = progression.getCurrentWinCells()
            if (remainingCells < totalCells) {
                showPattern(totalCells)
            }
        }
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

data class WhosNewUiState(
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val gridSize: Int = 3,
    val highlightedCells: Set<Int> = emptySet(),
    val newCellIndex: Int = -1,
    val gameState: GameState = GameState.SHOWING_PATTERN,
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    SHOWING_PATTERN,
    HIDING,
    USER_INPUT,
    SUCCESS_FEEDBACK,
    FAILURE_FEEDBACK
}

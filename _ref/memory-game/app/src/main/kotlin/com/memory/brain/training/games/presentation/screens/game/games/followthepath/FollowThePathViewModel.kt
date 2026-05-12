package com.memory.brain.training.games.presentation.screens.game.games.followthepath

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
 * Game 5: Follow the Path (跟随路径)
 * Remember and follow the path sequence
 * Based on: Game6FollowThePathActivity.java
 */
class FollowThePathViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(FollowThePathUiState())
    val uiState: StateFlow<FollowThePathUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression1()
    private var gameJob: Job? = null
    private var pathSequence = listOf<Int>()
    private var userSequence = mutableListOf<Int>()
    
    init {
        startGame()
    }
    
    fun startGame() {
        progression.startGame()
        startLevel()
    }
    
    private fun startLevel() {
        val gridSize = progression.getCurrentGridSize()
        val pathLength = progression.getCurrentWinCells()
        
        // Generate random path sequence
        pathSequence = generatePath(gridSize * gridSize, pathLength)
        userSequence.clear()
        
        _uiState.value = FollowThePathUiState(
            level = progression.getLevelNumber(),
            score = _uiState.value.score,
            lives = _uiState.value.lives,
            gridSize = gridSize,
            pathSequence = pathSequence,
            currentPathIndex = 0,
            userSequence = emptyList(),
            gameState = GameState.SHOWING_PATH,
            isPlaying = true
        )
        
        // Show path sequence
        showPathSequence()
    }
    
    private fun generatePath(totalCells: Int, length: Int): List<Int> {
        val path = mutableListOf<Int>()
        val available = (0 until totalCells).toMutableList()
        
        repeat(length) {
            val index = Random.nextInt(available.size)
            path.add(available[index])
            available.removeAt(index)
        }
        
        return path
    }
    
    private fun showPathSequence() {
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            // Show each cell in sequence
            pathSequence.forEachIndexed { index, cellIndex ->
                _uiState.value = _uiState.value.copy(
                    currentPathIndex = index,
                    gameState = GameState.SHOWING_PATH
                )
                delay(800)
            }
            
            // Hide path and enable user input
            delay(500)
            _uiState.value = _uiState.value.copy(
                currentPathIndex = -1,
                gameState = GameState.USER_INPUT
            )
        }
    }
    
    fun onCellClick(cellIndex: Int) {
        if (_uiState.value.gameState != GameState.USER_INPUT) return
        
        userSequence.add(cellIndex)
        val currentIndex = userSequence.size - 1
        
        _uiState.value = _uiState.value.copy(
            userSequence = userSequence.toList()
        )
        
        // Check if the clicked cell is correct
        if (pathSequence[currentIndex] != cellIndex) {
            // Wrong cell clicked
            handleFailure()
        } else if (userSequence.size == pathSequence.size) {
            // All cells clicked correctly
            handleSuccess()
        }
        // Otherwise, continue waiting for more clicks
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
        if (_uiState.value.gameState == GameState.SHOWING_PATH) {
            // Continue showing path from where we left off
            showPathSequence()
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

data class FollowThePathUiState(
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val gridSize: Int = 3,
    val pathSequence: List<Int> = emptyList(),
    val currentPathIndex: Int = -1,
    val userSequence: List<Int> = emptyList(),
    val gameState: GameState = GameState.SHOWING_PATH,
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    SHOWING_PATH,
    USER_INPUT,
    SUCCESS_FEEDBACK,
    FAILURE_FEEDBACK
}

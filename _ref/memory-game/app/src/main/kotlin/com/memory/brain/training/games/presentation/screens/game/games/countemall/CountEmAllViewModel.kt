package com.memory.brain.training.games.presentation.screens.game.games.countemall

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
 * Game 19: Count'em All (数一数)
 * Count the number of items displayed
 * Based on: Game5CountAllActivity.java
 */
class CountEmAllViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(CountEmAllUiState())
    val uiState: StateFlow<CountEmAllUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression1()
    private var gameJob: Job? = null
    
    // Emoji items to display
    private val availableEmojis = listOf(
        "⭐", "🌟", "✨", "💫", "🔥", "💧", "❄️", "⚡", "☀️", "🌙",
        "🍎", "🍊", "🍋", "🍌", "🍉", "🍇", "🍓", "🍒", "🍑", "🥝"
    )
    
    init {
        startGame()
    }
    
    fun startGame() {
        progression.startGame()
        startLevel()
    }
    
    private fun startLevel() {
        val gridSize = progression.getCurrentGridSize()
        val itemCount = progression.getCurrentWinCells()
        
        // Select random emoji
        val emoji = availableEmojis.random()
        
        // Generate grid with items
        val grid = generateGrid(gridSize, itemCount, emoji)
        
        // Generate answer options
        val options = generateOptions(itemCount)
        
        _uiState.value = CountEmAllUiState(
            level = progression.getLevelNumber(),
            score = _uiState.value.score,
            lives = _uiState.value.lives,
            gridSize = gridSize,
            grid = grid,
            correctCount = itemCount,
            options = options,
            gameState = GameState.SHOWING_ITEMS,
            isPlaying = true
        )
        
        // Show items then hide and show options
        showItems()
    }
    
    private fun generateGrid(gridSize: Int, itemCount: Int, emoji: String): List<String> {
        val totalCells = gridSize * gridSize
        val grid = MutableList(totalCells) { "" }
        
        // Place items randomly
        val positions = (0 until totalCells).shuffled().take(itemCount)
        positions.forEach { pos ->
            grid[pos] = emoji
        }
        
        return grid
    }
    
    private fun generateOptions(correctCount: Int): List<Int> {
        val options = mutableListOf<Int>()
        
        // Determine correct answer position
        val correctPosition = if (correctCount <= 3) {
            correctCount - 1
        } else {
            Random.nextInt(4)
        }
        
        // Generate 4 options
        for (i in 0 until 4) {
            if (i == correctPosition) {
                options.add(correctCount)
            } else {
                options.add(correctCount + (i - correctPosition))
            }
        }
        
        return options
    }
    
    private fun showItems() {
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            delay(2000) // Show for 2 seconds
            
            _uiState.value = _uiState.value.copy(
                gameState = GameState.HIDING
            )
            
            delay(500) // Hide for 0.5 seconds
            
            _uiState.value = _uiState.value.copy(
                gameState = GameState.SHOWING_OPTIONS
            )
        }
    }
    
    fun onOptionClick(option: Int) {
        if (_uiState.value.gameState != GameState.SHOWING_OPTIONS) return
        
        if (option == _uiState.value.correctCount) {
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
        if (_uiState.value.gameState == GameState.SHOWING_ITEMS) {
            showItems()
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

data class CountEmAllUiState(
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val gridSize: Int = 3,
    val grid: List<String> = emptyList(),
    val correctCount: Int = 0,
    val options: List<Int> = emptyList(),
    val gameState: GameState = GameState.SHOWING_ITEMS,
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    SHOWING_ITEMS,
    HIDING,
    SHOWING_OPTIONS,
    SUCCESS_FEEDBACK,
    FAILURE_FEEDBACK
}

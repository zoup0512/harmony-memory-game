package com.memory.brain.training.games.presentation.screens.game.games.pyramids

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
 * Game 17: Pyramids (金字塔)
 * Find the different pyramid
 * Based on: Game18PyramidsActivity.java
 */
class PyramidsViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(PyramidsUiState())
    val uiState: StateFlow<PyramidsUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression3()
    private var gameJob: Job? = null
    private var timerJob: Job? = null
    
    // Available colors for pyramids
    private val availableColors = listOf(
        0xFFF44336, // Red
        0xFF2196F3, // Blue
        0xFF4CAF50, // Green
        0xFFFFC107, // Amber
        0xFF9C27B0, // Purple
        0xFFFF9800, // Orange
        0xFF00BCD4, // Cyan
        0xFFFF5722  // Deep Orange
    )
    
    init {
        startGame()
    }
    
    fun startGame() {
        progression.startGame()
        startLevel()
    }
    
    private fun startLevel() {
        val level = progression.getLevelNumber()
        val circleCount = getCircleCount(level)
        
        // Generate 3 pyramids
        val pyramids = generatePyramids(circleCount, level)
        
        // Select different pyramid
        val differentIndex = Random.nextInt(3)
        
        _uiState.value = PyramidsUiState(
            level = level,
            score = _uiState.value.score,
            lives = _uiState.value.lives,
            pyramids = pyramids,
            differentIndex = differentIndex,
            gameState = GameState.USER_INPUT,
            isPlaying = true,
            timeRemaining = 60000L
        )
        
        // Start timer
        startTimer()
    }
    
    private fun getCircleCount(level: Int): Int {
        return when {
            level <= 1 -> 3
            level <= 3 -> 3
            level <= 7 -> 4
            level <= 13 -> 5
            level <= 18 -> 6
            level <= 23 -> 7
            else -> 8
        }
    }
    
    private fun generatePyramids(circleCount: Int, level: Int): List<Pyramid> {
        val pyramids = mutableListOf<Pyramid>()
        
        // Generate sizes (decreasing from 1.0 to smaller)
        val sizes = generateSizes(circleCount)
        
        // Generate 3 pyramids with different color patterns
        repeat(3) {
            val colors = generateColors(circleCount, pyramids, level)
            pyramids.add(Pyramid(colors, sizes))
        }
        
        return pyramids
    }
    
    private fun generateSizes(count: Int): List<Float> {
        val sizes = mutableListOf<Float>()
        sizes.add(1.0f)
        
        val oneStep = 1.0f / count
        for (i in 1 until count) {
            val size = ((count - i).toFloat() / count) + (oneStep / 2f) - 
                       (Random.nextDouble(oneStep.toDouble() * 0.8).toFloat())
            sizes.add(size.coerceIn(0.2f, 1.0f))
        }
        
        return sizes
    }
    
    private fun generateColors(count: Int, existingPyramids: List<Pyramid>, level: Int): List<Long> {
        var colors: List<Long>
        var attempts = 0
        
        do {
            colors = getRandomColors(count)
            attempts++
        } while (!isValidColors(colors, existingPyramids, level) && attempts < 100)
        
        return colors
    }
    
    private fun getRandomColors(count: Int): List<Long> {
        val colors = mutableListOf<Long>()
        val shuffled = availableColors.shuffled()
        
        // Repeat colors if needed
        var index = 0
        repeat(count) {
            colors.add(shuffled[index % shuffled.size].toLong())
            index++
        }
        
        return colors.shuffled()
    }
    
    private fun isValidColors(colors: List<Long>, existingPyramids: List<Pyramid>, level: Int): Boolean {
        // No adjacent same colors
        for (i in 1 until colors.size) {
            if (colors[i] == colors[i - 1]) {
                return false
            }
        }
        
        // Check against existing pyramids
        for (existing in existingPyramids) {
            // Apply level-specific constraints
            if (level > 5) {
                if (level <= 8) {
                    // Last color must be same
                    if (colors.last() != existing.colors.last()) {
                        return false
                    }
                } else if (level <= 18) {
                    // First and last must be same
                    if (colors.first() != existing.colors.first()) {
                        return false
                    }
                    if (colors.last() != existing.colors.last()) {
                        return false
                    }
                } else {
                    // First, second-to-last, and last must be same
                    if (colors.first() != existing.colors.first()) {
                        return false
                    }
                    if (colors[colors.size - 2] != existing.colors[existing.colors.size - 2]) {
                        return false
                    }
                    if (colors.last() != existing.colors.last()) {
                        return false
                    }
                }
            }
            
            // Not all colors can be the same
            if (colors == existing.colors) {
                return false
            }
        }
        
        return true
    }
    
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            val duration = 60000L
            
            while (_uiState.value.isPlaying && _uiState.value.gameState == GameState.USER_INPUT) {
                val elapsed = System.currentTimeMillis() - startTime
                val remaining = duration - elapsed
                
                if (remaining <= 0) {
                    handleTimeout()
                    break
                }
                
                _uiState.value = _uiState.value.copy(
                    timeRemaining = remaining
                )
                
                delay(100)
            }
        }
    }
    
    fun onPyramidClick(index: Int) {
        if (_uiState.value.gameState != GameState.USER_INPUT) return
        
        timerJob?.cancel()
        
        if (index == _uiState.value.differentIndex) {
            handleSuccess()
        } else {
            handleFailure()
        }
    }
    
    private fun handleSuccess() {
        val scoreGain = 8 + (progression.getLevelNumber() * 2)
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
    
    private fun handleTimeout() {
        handleFailure()
    }
    
    fun pauseGame() {
        timerJob?.cancel()
        gameJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        if (_uiState.value.gameState == GameState.USER_INPUT) {
            startTimer()
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
        timerJob?.cancel()
    }
}

data class Pyramid(
    val colors: List<Long>,
    val sizes: List<Float>
)

data class PyramidsUiState(
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val pyramids: List<Pyramid> = emptyList(),
    val differentIndex: Int = -1,
    val gameState: GameState = GameState.USER_INPUT,
    val isPlaying: Boolean = false,
    val timeRemaining: Long = 60000L,
    val isPaused: Boolean = false
)

enum class GameState {
    USER_INPUT,
    SUCCESS_FEEDBACK,
    FAILURE_FEEDBACK
}

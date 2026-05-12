package com.memory.brain.training.games.presentation.screens.game.games.schultztables

import androidx.compose.ui.graphics.Color
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
 * Game 22: Schultz Tables
 * 按顺序点击数字（1, 2, 3, ...）
 * 50秒计时，尽可能完成更多关卡
 */
class SchultzTablesViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(SchultzTablesUiState())
    val uiState: StateFlow<SchultzTablesUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var gameStartTime = 0L
    private val gameDuration = 50000L // 50 seconds
    
    private var currentNumber = 1
    private var totalNumbers = 48
    
    private val availableColors = listOf(
        Color(0xFFE91E63), // Pink
        Color(0xFF9C27B0), // Purple
        Color(0xFF3F51B5), // Indigo
        Color(0xFF2196F3), // Blue
        Color(0xFF00BCD4), // Cyan
        Color(0xFF4CAF50), // Green
        Color(0xFFFFEB3B), // Yellow
        Color(0xFFFF9800), // Orange
        Color(0xFFFF5722), // Deep Orange
        Color(0xFF795548)  // Brown
    )
    
    data class NumberElement(
        val number: Int,
        val color: Color,
        val isClicked: Boolean = false
    )
    
    fun startGame() {
        progression.startGame()
        gameStartTime = System.currentTimeMillis()
        currentNumber = 1
        totalNumbers = 48
        startTimer()
        startLevel()
    }
    
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(50)
                val elapsed = System.currentTimeMillis() - gameStartTime
                val remaining = gameDuration - elapsed
                
                if (remaining <= 0) {
                    _uiState.value = _uiState.value.copy(
                        timeRemaining = 0,
                        gameState = GameState.GAME_OVER
                    )
                    break
                }
                
                _uiState.value = _uiState.value.copy(
                    timeRemaining = remaining
                )
            }
        }
    }
    
    private fun startLevel() {
        val level = progression.getLevelNumber()
        val elements = generateElements()
        
        _uiState.value = _uiState.value.copy(
            level = level,
            elements = elements,
            currentNumber = currentNumber,
            gameState = GameState.PLAYING,
            showFeedback = false
        )
    }
    
    private fun generateElements(): List<NumberElement> {
        // Generate numbers 1-48
        val numbers = (1..totalNumbers).toList()
        
        // Generate colors
        val colors = availableColors.shuffled()
        
        // Create elements with colors
        val elements = numbers.mapIndexed { index, number ->
            val color = colors[index % colors.size]
            NumberElement(number, color)
        }
        
        return elements.shuffled()
    }
    
    fun onNumberClicked(element: NumberElement) {
        if (_uiState.value.gameState != GameState.PLAYING) return
        if (element.isClicked) return
        
        val isCorrect = element.number == currentNumber
        
        if (isCorrect) {
            // Correct click
            val updatedElements = _uiState.value.elements.map {
                if (it.number == element.number) it.copy(isClicked = true)
                else it
            }
            
            currentNumber++
            
            _uiState.value = _uiState.value.copy(
                elements = updatedElements,
                currentNumber = currentNumber
            )
            
            // Check if level complete (all numbers clicked)
            if (currentNumber > totalNumbers) {
                _uiState.value = _uiState.value.copy(
                    showFeedback = true,
                    isCorrectAnswer = true
                )
                
                viewModelScope.launch {
                    delay(500)
                    progression.nextLevel()
                    currentNumber = 1
                    startLevel()
                }
            }
        } else {
            // Wrong click
            _uiState.value = _uiState.value.copy(
                showFeedback = true,
                isCorrectAnswer = false
            )
            
            viewModelScope.launch {
                delay(800)
                
                val newLives = _uiState.value.lives - 1
                if (newLives <= 0) {
                    _uiState.value = _uiState.value.copy(
                        lives = 0,
                        gameState = GameState.GAME_OVER
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        lives = newLives
                    )
                    currentNumber = 1
                    startLevel()
                }
            }
        }
    }
    
    fun pauseGame() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        if (_uiState.value.gameState == GameState.PLAYING) {
            startTimer()
        }
    }
    
    fun restartGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        startGame()
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}

data class SchultzTablesUiState(
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Long = 50000L,
    val elements: List<SchultzTablesViewModel.NumberElement> = emptyList(),
    val currentNumber: Int = 1,
    val gameState: GameState = GameState.IDLE,
    val showFeedback: Boolean = false,
    val isCorrectAnswer: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    IDLE, PLAYING, GAME_OVER
}

package com.memory.brain.training.games.presentation.screens.game.games.sortthedigits

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
 * Game 13: Sort the Digits
 * 按顺序点击数字（从小到大）
 * 60秒计时，尽可能完成更多关卡
 */
class SortTheDigitsViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(SortTheDigitsUiState())
    val uiState: StateFlow<SortTheDigitsUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var gameStartTime = 0L
    private val gameDuration = 60000L // 60 seconds
    
    private var currentClickIndex = 0
    
    data class NumberElement(
        val number: Int,
        val color: Color,
        val order: Int, // Correct order (0-based)
        val isClicked: Boolean = false
    )
    
    fun startGame() {
        progression.startGame()
        gameStartTime = System.currentTimeMillis()
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
        val elements = generateElements(level)
        currentClickIndex = 0
        
        _uiState.value = _uiState.value.copy(
            level = level,
            elements = elements,
            gameState = GameState.PLAYING,
            showFeedback = false
        )
    }
    
    private fun generateElements(level: Int): List<NumberElement> {
        // Determine parameters based on level
        val (numbersCount, colorsCount, minNumber, maxNumber) = when {
            level <= 3 -> Params(3, 1, 1, 9)
            level <= 10 -> Params(4, 2, 1, 15)
            level <= 15 -> Params(5, 3, -5, 20)
            else -> Params(6, 4, -10, 30)
        }
        
        // Generate random numbers
        val numbers = (minNumber..maxNumber).toList().shuffled().take(numbersCount)
        
        // Generate colors
        val colors = getColors(colorsCount)
        
        // Create elements with sorted order
        val sortedNumbers = numbers.sorted()
        val elements = numbers.mapIndexed { index, number ->
            val order = sortedNumbers.indexOf(number)
            val color = colors[index % colors.size]
            NumberElement(number, color, order)
        }
        
        return elements.shuffled()
    }
    
    private fun getColors(count: Int): List<Color> {
        val availableColors = listOf(
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
        return availableColors.shuffled().take(count)
    }
    
    fun onNumberClicked(element: NumberElement) {
        if (_uiState.value.gameState != GameState.PLAYING) return
        if (element.isClicked) return
        
        val isCorrect = element.order == currentClickIndex
        
        if (isCorrect) {
            // Correct click
            val updatedElements = _uiState.value.elements.map {
                if (it.number == element.number) it.copy(isClicked = true)
                else it
            }
            
            currentClickIndex++
            
            _uiState.value = _uiState.value.copy(
                elements = updatedElements
            )
            
            // Check if level complete
            if (currentClickIndex >= updatedElements.size) {
                _uiState.value = _uiState.value.copy(
                    showFeedback = true,
                    isCorrectAnswer = true
                )
                
                viewModelScope.launch {
                    delay(800)
                    progression.nextLevel()
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

data class SortTheDigitsUiState(
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Long = 60000L,
    val elements: List<SortTheDigitsViewModel.NumberElement> = emptyList(),
    val gameState: GameState = GameState.IDLE,
    val showFeedback: Boolean = false,
    val isCorrectAnswer: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    IDLE, PLAYING, GAME_OVER
}

private data class Params(
    val numbersCount: Int,
    val colorsCount: Int,
    val minNumber: Int,
    val maxNumber: Int
)

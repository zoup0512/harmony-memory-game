package com.memory.brain.training.games.presentation.screens.game.games.likeprevious

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
 * Game 15: Like Previous
 * 判断当前元素是否与之前的元素相同
 * 30秒计时，尽可能完成更多关卡
 */
class LikePreviousViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(LikePreviousUiState())
    val uiState: StateFlow<LikePreviousUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var gameStartTime = 0L
    private val gameDuration = 30000L // 30 seconds
    
    private var previousElement: Element? = null
    
    // 7 shapes
    enum class Shape {
        CIRCLE, SQUARE, TRIANGLE, DIAMOND, STAR, HEXAGON, HEART
    }
    
    // Available colors
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
    
    data class Element(
        val shape: Shape,
        val color: Color,
        val isSameAsPrevious: Boolean
    )
    
    fun startGame() {
        progression.startGame()
        gameStartTime = System.currentTimeMillis()
        previousElement = null
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
        val element = generateNextElement(level)
        
        _uiState.value = _uiState.value.copy(
            level = level,
            currentElement = element,
            previousElement = previousElement,
            gameState = GameState.PLAYING,
            showFeedback = false,
            showPreviousElement = previousElement != null
        )
        
        // First level auto-advances
        if (level == 1) {
            viewModelScope.launch {
                delay(800)
                previousElement = element
                progression.nextLevel()
                startLevel()
            }
        }
    }
    
    private fun generateNextElement(level: Int): Element {
        // Determine parameters based on level
        val (colorsCount, figuresCount) = when {
            level <= 5 -> 4 to 7
            level <= 10 -> 4 to 5
            level <= 15 -> 4 to 4
            else -> 1 to 3
        }
        
        // 50% chance to be same as previous
        val isSame = previousElement != null && Random.nextBoolean()
        
        return if (previousElement == null) {
            // First element
            val colors = availableColors.shuffled().take(colorsCount)
            val shapes = Shape.values().toList().shuffled().take(figuresCount)
            Element(
                shape = shapes[0],
                color = colors[0],
                isSameAsPrevious = false
            )
        } else if (isSame) {
            // Same as previous
            Element(
                shape = previousElement!!.shape,
                color = previousElement!!.color,
                isSameAsPrevious = true
            )
        } else {
            // Different from previous
            val colors = availableColors.shuffled().take(colorsCount)
            val shapes = Shape.values().toList().shuffled().take(figuresCount)
            
            // Ensure different
            val newColor: Color
            val newShape: Shape
            
            // Change shape or color (or both)
            if (colorsCount > 1) {
                // Find different color
                newColor = colors.find { it != previousElement!!.color } ?: colors[0]
            } else {
                // Same color, must change shape
                newColor = previousElement!!.color
            }
            
            // Find different shape
            newShape = shapes.find { it != previousElement!!.shape } ?: shapes[0]
            
            Element(
                shape = newShape,
                color = newColor,
                isSameAsPrevious = false
            )
        }
    }
    
    fun onAnswerSelected(isSame: Boolean) {
        if (_uiState.value.gameState != GameState.PLAYING) return
        
        val currentElement = _uiState.value.currentElement ?: return
        val isCorrect = isSame == currentElement.isSameAsPrevious
        
        _uiState.value = _uiState.value.copy(
            showFeedback = true,
            isCorrectAnswer = isCorrect
        )
        
        viewModelScope.launch {
            delay(800)
            
            if (isCorrect) {
                // Correct answer - next level
                previousElement = currentElement
                progression.nextLevel()
                startLevel()
            } else {
                // Wrong answer - lose a life
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
                    // Restart with new element
                    previousElement = currentElement
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

data class LikePreviousUiState(
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Long = 30000L,
    val currentElement: LikePreviousViewModel.Element? = null,
    val previousElement: LikePreviousViewModel.Element? = null,
    val showPreviousElement: Boolean = false,
    val gameState: GameState = GameState.IDLE,
    val showFeedback: Boolean = false,
    val isCorrectAnswer: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    IDLE, PLAYING, GAME_OVER
}

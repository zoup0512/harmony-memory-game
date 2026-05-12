package com.memory.brain.training.games.presentation.screens.game.games.allthesame

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
 * Game 12: All the Same
 * 判断所有元素是否相同
 * 60秒计时，尽可能完成更多关卡
 */
class AllTheSameViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(AllTheSameUiState())
    val uiState: StateFlow<AllTheSameUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var gameStartTime = 0L
    private val gameDuration = 60000L // 60 seconds
    
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
        val color: Color
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
        
        _uiState.value = _uiState.value.copy(
            level = level,
            elements = elements,
            gameState = GameState.PLAYING,
            showFeedback = false
        )
    }
    
    private fun generateElements(level: Int): List<Element> {
        val elementCount = level + 4 // 5-54 elements
        
        // Determine element types based on level
        val (element1, element2) = when {
            level <= 5 -> {
                // Different shape and different color
                val shape1 = Shape.values().random()
                val shape2 = Shape.values().filter { it != shape1 }.random()
                val color1 = availableColors.random()
                val color2 = availableColors.filter { it != color1 }.random()
                Element(shape1, color1) to Element(shape2, color2)
            }
            level <= 10 -> {
                // Same shape, different color
                val shape = Shape.values().random()
                val color1 = availableColors.random()
                val color2 = availableColors.filter { it != color1 }.random()
                Element(shape, color1) to Element(shape, color2)
            }
            else -> {
                // Different shape, same color
                val shape1 = Shape.values().random()
                val shape2 = Shape.values().filter { it != shape1 }.random()
                val color = availableColors.random()
                Element(shape1, color) to Element(shape2, color)
            }
        }
        
        // Determine group distribution (35%-65%)
        val randomValue = 0.35 + (Random.nextDouble() * 0.30)
        val group1Count = (elementCount * randomValue).toInt().coerceIn(
            (elementCount * 0.35).toInt(),
            (elementCount * 0.65).toInt()
        )
        val group2Count = elementCount - group1Count
        
        // Create element list
        val result = mutableListOf<Element>()
        repeat(group1Count) { result.add(element1) }
        repeat(group2Count) { result.add(element2) }
        
        return result.shuffled()
    }
    
    fun onAnswerSelected(allSame: Boolean) {
        if (_uiState.value.gameState != GameState.PLAYING) return
        
        val elements = _uiState.value.elements
        val actuallyAllSame = elements.all { 
            it.shape == elements[0].shape && it.color == elements[0].color 
        }
        
        val isCorrect = allSame == actuallyAllSame
        
        _uiState.value = _uiState.value.copy(
            showFeedback = true,
            isCorrectAnswer = isCorrect
        )
        
        viewModelScope.launch {
            delay(800)
            
            if (isCorrect) {
                // Correct answer - next level
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
                        lives = newLives,
                        showFeedback = false
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

data class AllTheSameUiState(
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Long = 60000L,
    val elements: List<AllTheSameViewModel.Element> = emptyList(),
    val gameState: GameState = GameState.IDLE,
    val showFeedback: Boolean = false,
    val isCorrectAnswer: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    IDLE, PLAYING, GAME_OVER
}

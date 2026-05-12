package com.memory.brain.training.games.presentation.screens.game.games.findall

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
 * Game 18: Find All
 * 找到所有目标元素（双色圆圈）
 * 60秒计时，尽可能完成更多关卡
 */
class FindAllViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(FindAllUiState())
    val uiState: StateFlow<FindAllUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var gameStartTime = 0L
    private val gameDuration = 60000L // 60 seconds
    
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
        val centerColor: Color,
        val sideColor: Color,
        val row: Int,
        val col: Int,
        val isTarget: Boolean,
        val isClicked: Boolean = false
    )
    
    data class TargetPattern(
        val centerColor: Color,
        val sideColor: Color
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
        val (elements, targets, gridSize) = generateLevel(level)
        
        _uiState.value = _uiState.value.copy(
            level = level,
            elements = elements,
            targetPatterns = targets,
            gridSize = gridSize,
            gameState = GameState.PLAYING,
            showFeedback = false
        )
    }
    
    private fun generateLevel(level: Int): Triple<List<Element>, List<TargetPattern>, Pair<Int, Int>> {
        val elementCount = level + 2
        val numberOfGroups = when {
            level <= 5 -> 3
            level <= 10 -> 4
            else -> 6
        }
        val guessElementCount = if (level <= 7) 1 else 2
        
        val gridSize = when {
            level <= 5 -> 3 to 3
            level <= 8 -> 4 to 4
            level <= 12 -> 5 to 5
            else -> 5 to 7
        }
        
        // Generate color pairs
        val colors = availableColors.shuffled().take(6)
        val colorPairs = mutableListOf<Pair<Color, Color>>()
        for (centerColor in colors) {
            for (sideColor in colors) {
                if (centerColor != sideColor) {
                    colorPairs.add(centerColor to sideColor)
                }
            }
        }
        colorPairs.shuffle()
        val selectedPairs = colorPairs.take(numberOfGroups)
        
        // Select target patterns
        val targetPatterns = selectedPairs.take(guessElementCount).map {
            TargetPattern(it.first, it.second)
        }
        
        // Generate grid positions
        val positions = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until gridSize.first) {
            for (j in 0 until gridSize.second) {
                positions.add(i to j)
            }
        }
        positions.shuffle()
        
        // Create elements
        val elements = mutableListOf<Element>()
        for (i in 0 until elementCount) {
            val colorPair = selectedPairs[i % numberOfGroups]
            val position = positions[i]
            val isTarget = targetPatterns.any { 
                it.centerColor == colorPair.first && it.sideColor == colorPair.second 
            }
            
            elements.add(
                Element(
                    centerColor = colorPair.first,
                    sideColor = colorPair.second,
                    row = position.first,
                    col = position.second,
                    isTarget = isTarget
                )
            )
        }
        
        return Triple(elements, targetPatterns, gridSize)
    }
    
    fun onElementClicked(element: Element) {
        if (_uiState.value.gameState != GameState.PLAYING) return
        if (element.isClicked) return
        
        if (element.isTarget) {
            // Correct click
            val updatedElements = _uiState.value.elements.map {
                if (it.row == element.row && it.col == element.col) {
                    it.copy(isClicked = true)
                } else it
            }
            
            _uiState.value = _uiState.value.copy(
                elements = updatedElements
            )
            
            // Check if all targets found
            val allTargetsFound = updatedElements.filter { it.isTarget }.all { it.isClicked }
            if (allTargetsFound) {
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

data class FindAllUiState(
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Long = 60000L,
    val elements: List<FindAllViewModel.Element> = emptyList(),
    val targetPatterns: List<FindAllViewModel.TargetPattern> = emptyList(),
    val gridSize: Pair<Int, Int> = 3 to 3,
    val gameState: GameState = GameState.IDLE,
    val showFeedback: Boolean = false,
    val isCorrectAnswer: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    IDLE, PLAYING, GAME_OVER
}

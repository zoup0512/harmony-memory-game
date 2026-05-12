package com.memory.brain.training.games.presentation.screens.game.games.colors

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
 * Colors Game ViewModel - Stroop Effect Game
 * Based on Game20ColorsActivity.java
 * 
 * Game Rules:
 * - Show a color word (e.g., "RED") in a certain color
 * - Player judges if the word matches the color
 * - Two buttons: Match / Don't Match
 * - 30 second timer total
 * - No lives system
 * - Scoring: 8 points (no level multiplier)
 */
class ColorsViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(ColorsUiState())
    val uiState: StateFlow<ColorsUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression3()
    private var timerJob: Job? = null
    private var startTime: Long = 0
    private val totalGameTime = 30000L // 30 seconds
    
    // Available colors
    private val colorNames = listOf("RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE")
    private val colors = listOf(
        Color(0xFFF44336), // Red
        Color(0xFF2196F3), // Blue
        Color(0xFF4CAF50), // Green
        Color(0xFFFFEB3B), // Yellow
        Color(0xFF9C27B0), // Purple
        Color(0xFFFF9800)  // Orange
    )
    
    init {
        startGame()
    }
    
    fun startGame() {
        progression.startGame()
        startTime = System.currentTimeMillis()
        
        _uiState.value = ColorsUiState(
            level = progression.getLevelNumber(),
            score = 0,
            timeRemaining = totalGameTime,
            isGameOver = false,
            showCorrectHint = false
        )
        
        generateNewQuestion()
        startTimer()
    }
    
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(50)
                val elapsed = System.currentTimeMillis() - startTime
                val remaining = totalGameTime - elapsed
                
                if (remaining <= 0) {
                    // Time's up
                    _uiState.value = _uiState.value.copy(
                        timeRemaining = 0,
                        isGameOver = true
                    )
                    timerJob?.cancel()
                    break
                } else {
                    _uiState.value = _uiState.value.copy(
                        timeRemaining = remaining
                    )
                }
            }
        }
    }
    
    private fun generateNewQuestion() {
        val wordIndex = Random.nextInt(colorNames.size)
        val colorIndex = Random.nextInt(colors.size)
        
        val word = colorNames[wordIndex]
        val color = colors[colorIndex]
        val isMatch = wordIndex == colorIndex
        
        _uiState.value = _uiState.value.copy(
            currentWord = word,
            currentColor = color,
            isMatch = isMatch
        )
    }
    
    fun onMatchClicked() {
        handleAnswer(true)
    }
    
    fun onDontMatchClicked() {
        handleAnswer(false)
    }
    
    private fun handleAnswer(playerSaysMatch: Boolean) {
        val currentState = _uiState.value
        
        // Check if time is still remaining
        if (currentState.timeRemaining <= 0) {
            return
        }
        
        val isCorrect = playerSaysMatch == currentState.isMatch
        
        if (isCorrect) {
            // Correct answer
            val newScore = currentState.score + 8 // Fixed 8 points per correct answer
            
            // Show correct hint animation
            _uiState.value = currentState.copy(
                score = newScore,
                showCorrectHint = true
            )
            
            // Hide hint after 500ms
            viewModelScope.launch {
                delay(500)
                _uiState.value = _uiState.value.copy(showCorrectHint = false)
            }
            
            // Progress to next level
            progression.nextLevel()
            _uiState.value = _uiState.value.copy(
                level = progression.getLevelNumber()
            )
            
            // Generate next question
            generateNewQuestion()
        } else {
            // Wrong answer - game over
            timerJob?.cancel()
            _uiState.value = currentState.copy(
                isGameOver = true
            )
        }
    }
    
    fun pauseGame() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        if (!_uiState.value.isGameOver) {
            startTimer()
        }
    }
    
    fun restartGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        startGame()
    }
    
    fun retryGame() {
        startGame()
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}

/**
 * UI State for Colors Game
 */
data class ColorsUiState(
    val level: Int = 1,
    val score: Int = 0,
    val timeRemaining: Long = 30000L,
    val currentWord: String = "",
    val currentColor: Color = Color.Red,
    val isMatch: Boolean = false,
    val showCorrectHint: Boolean = false,
    val isGameOver: Boolean = false,
    val isPaused: Boolean = false
)

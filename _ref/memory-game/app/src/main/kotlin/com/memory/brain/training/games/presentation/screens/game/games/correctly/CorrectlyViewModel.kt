package com.memory.brain.training.games.presentation.screens.game.games.correctly

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.Game
import com.memory.brain.training.games.domain.model.GameProgression12
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CorrectlyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val gameId: Int = savedStateHandle.get<Int>("gameId") ?: 25
    
    private val _uiState = MutableStateFlow(CorrectlyUiState())
    val uiState: StateFlow<CorrectlyUiState> = _uiState.asStateFlow()
    
    private var questionTimerJob: Job? = null
    private var gameTimerJob: Job? = null
    private val progression = GameProgression12()
    
    companion object {
        private const val GAME_TIME = 60000L // 60 seconds total
    }
    
    init {
        loadGame()
    }
    
    private fun loadGame() {
        val game = GameDataProvider.getAllGames().find { it.id == gameId }
        if (game != null) {
            _uiState.update { it.copy(game = game) }
            startGame()
        }
    }
    
    fun startGame() {
        progression.startGame()
        _uiState.update { 
            it.copy(
                gameState = CorrectlyState.PLAYING,
                score = 0,
                level = 1,
                lives = 3,
                totalTimeRemaining = 60
            )
        }
        startGameTimer()
        generateQuestion()
    }
    
    private fun generateQuestion() {
        val levelNumber = progression.getLevelNumber()
        val equation = generateEquation(levelNumber)
        val timeLimit = progression.getTimeLimit()
        
        _uiState.update { 
            it.copy(
                equation = equation,
                questionTimeRemaining = (timeLimit / 1000).toInt(),
                level = levelNumber,
                gameState = CorrectlyState.PLAYING
            )
        }
        
        startQuestionTimer(timeLimit)
    }
    
    /**
     * Generate equation based on level
     * Logic from Game11CorrectlyActivity.java
     */
    private fun generateEquation(levelNumber: Int): Equation {
        return when {
            levelNumber <= 3 -> generateType1Equation()
            levelNumber <= 5 -> generateType2Equation()
            levelNumber <= 7 -> generateType3Equation()
            levelNumber <= 9 -> generateType4Equation()
            levelNumber <= 11 -> generateType5Equation()
            levelNumber <= 15 -> generateType6Equation()
            levelNumber <= 20 -> generateType7Equation()
            else -> generateType8Equation()
        }
    }
    
    // Type 1: Simple addition/subtraction (4-10 ± 1-7)
    private fun generateType1Equation(): Equation {
        val x1 = randInt(4, 10)
        val x2 = randInt(1, 7)
        val z = randInt(1, 2)
        
        val (expression, result) = if (Random.nextBoolean()) {
            "$x1 + $x2 = " to (x1 + x2)
        } else {
            "$x1 - $x2 = " to (x1 - x2)
        }
        
        val isCorrect = Random.nextBoolean()
        val displayResult = if (isCorrect) result else result + (if (Random.nextBoolean()) z else -z)
        
        return Equation(expression + displayResult, isCorrect)
    }
    
    // Type 2: Larger numbers (9-25 ± 5-15)
    private fun generateType2Equation(): Equation {
        val x1 = randInt(9, 25)
        val x2 = randInt(5, 15)
        val z = randInt(1, 3)
        
        val (expression, result) = if (Random.nextBoolean()) {
            "$x1 + $x2 = " to (x1 + x2)
        } else {
            "$x1 - $x2 = " to (x1 - x2)
        }
        
        val isCorrect = Random.nextBoolean()
        val displayResult = if (isCorrect) result else result + (if (Random.nextBoolean()) z else -z)
        
        return Equation(expression + displayResult, isCorrect)
    }
    
    // Type 3: Multiplication (2-8 × 3-6)
    private fun generateType3Equation(): Equation {
        val x1 = randInt(2, 8)
        val x2 = randInt(3, 6)
        val z = randInt(1, 2)
        
        val expression = "$x1 × $x2 = "
        val result = x1 * x2
        
        val isCorrect = Random.nextBoolean()
        val displayResult = if (isCorrect) result else result + (if (Random.nextBoolean()) z else -z)
        
        return Equation(expression + displayResult, isCorrect)
    }
    
    // Type 4: Three operations (4-10 ± 2-6 ± 3-8)
    private fun generateType4Equation(): Equation {
        val x1 = randInt(4, 10)
        val x2 = randInt(2, 6)
        val x3 = randInt(3, 8)
        val z = randInt(1, 3)
        
        val op1 = if (Random.nextBoolean()) "+" else "-"
        val op2 = if (Random.nextBoolean()) "+" else "-"
        
        val result = when {
            op1 == "+" && op2 == "+" -> x1 + x2 + x3
            op1 == "+" && op2 == "-" -> x1 + x2 - x3
            op1 == "-" && op2 == "+" -> x1 - x2 + x3
            else -> x1 - x2 - x3
        }
        
        val expression = "$x1 $op1 $x2 $op2 $x3 = "
        
        val isCorrect = Random.nextBoolean()
        val displayResult = if (isCorrect) result else result + (if (Random.nextBoolean()) z else -z)
        
        return Equation(expression + displayResult, isCorrect)
    }
    
    // Type 5: Larger three operations (15-31 ± 11-20 ± 8-22)
    private fun generateType5Equation(): Equation {
        val x1 = randInt(15, 31)
        val x2 = randInt(11, 20)
        val x3 = randInt(8, 22)
        val z = randNumber(1, 3, 10, 20)
        
        val op1 = if (Random.nextBoolean()) "+" else "-"
        val op2 = if (Random.nextBoolean()) "+" else "-"
        
        val result = when {
            op1 == "+" && op2 == "+" -> x1 + x2 + x3
            op1 == "+" && op2 == "-" -> x1 + x2 - x3
            op1 == "-" && op2 == "+" -> x1 - x2 + x3
            else -> x1 - x2 - x3
        }
        
        val expression = "$x1 $op1 $x2 $op2 $x3 = "
        
        val isCorrect = Random.nextBoolean()
        val displayResult = if (isCorrect) result else result + (if (Random.nextBoolean()) z else -z)
        
        return Equation(expression + displayResult, isCorrect)
    }
    
    // Type 6: Multiplication + addition (9-20 × 2-9 ± 15-30)
    private fun generateType6Equation(): Equation {
        val x1 = randInt(9, 20)
        val x2 = randInt(2, 9)
        val x3 = randInt(15, 30)
        val z = randNumber(1, 3, 5, 10)
        
        val op = if (Random.nextBoolean()) "+" else "-"
        
        val result = if (op == "+") {
            (x1 * x2) + x3
        } else {
            (x1 * x2) - x3
        }
        
        val expression = "$x1 × $x2 $op $x3 = "
        
        val isCorrect = Random.nextBoolean()
        val displayResult = if (isCorrect) result else result + (if (Random.nextBoolean()) z else -z)
        
        return Equation(expression + displayResult, isCorrect)
    }
    
    // Type 7: Two multiplications (6-12 × 7-15 ± 5-11 × 8-16)
    private fun generateType7Equation(): Equation {
        val x1 = randInt(6, 12)
        val x2 = randInt(7, 15)
        val x3 = randInt(5, 11)
        val x4 = randInt(8, 16)
        val z = randNumber(1, 3, 10, 30)
        
        val op = if (Random.nextBoolean()) "+" else "-"
        
        val result = if (op == "+") {
            (x1 * x2) + (x3 * x4)
        } else {
            (x1 * x2) - (x3 * x4)
        }
        
        val expression = "$x1 × $x2 $op $x3 × $x4 = "
        
        val isCorrect = Random.nextBoolean()
        val displayResult = if (isCorrect) result else result + (if (Random.nextBoolean()) z else -z)
        
        return Equation(expression + displayResult, isCorrect)
    }
    
    // Type 8: Complex (21-33 × 21-33 ± 21-33 × 21-33 ± 100-300)
    private fun generateType8Equation(): Equation {
        val x1 = randInt(21, 33)
        val x2 = randInt(21, 33)
        val x3 = randInt(21, 33)
        val x4 = randInt(21, 33)
        val x5 = randInt(100, 300)
        val z = randNumber(1, 3, 20, 40)
        
        val op1 = if (Random.nextBoolean()) "+" else "-"
        val op2 = if (Random.nextBoolean()) "+" else "-"
        
        val result = when {
            op1 == "+" && op2 == "+" -> (x1 * x2) + (x3 * x4) + x5
            op1 == "+" && op2 == "-" -> (x1 * x2) + (x3 * x4) - x5
            op1 == "-" && op2 == "+" -> (x1 * x2) - (x3 * x4) + x5
            else -> (x1 * x2) - (x3 * x4) - x5
        }
        
        val expression = "$x1 × $x2 $op1 $x3 × $x4 $op2 $x5 = "
        
        val isCorrect = Random.nextBoolean()
        val displayResult = if (isCorrect) result else result + (if (Random.nextBoolean()) z else -z)
        
        return Equation(expression + displayResult, isCorrect)
    }
    
    private fun randInt(min: Int, max: Int): Int {
        return Random.nextInt(max - min + 1) + min
    }
    
    private fun randNumber(vararg numbers: Int): Int {
        return numbers[Random.nextInt(numbers.size)]
    }
    
    fun onAnswerClick(playerAnswer: Boolean) {
        if (_uiState.value.gameState != CorrectlyState.PLAYING) {
            return
        }
        
        questionTimerJob?.cancel()
        
        val isCorrect = playerAnswer == _uiState.value.equation.isCorrect
        
        if (isCorrect) {
            // Correct answer!
            val levelScore = calculateLevelScore(progression.getLevelNumber())
            val newScore = _uiState.value.score + levelScore
            
            _uiState.update { 
                it.copy(
                    score = newScore,
                    gameState = CorrectlyState.SHOW_SUCCESS
                )
            }
            
            // Move to next level after brief delay
            viewModelScope.launch {
                delay(500)
                
                // Check if time is still remaining
                if (_uiState.value.totalTimeRemaining > 0) {
                    progression.nextLevel()
                    generateQuestion()
                }
            }
        } else {
            // Wrong answer
            val newLives = _uiState.value.lives - 1
            
            if (newLives <= 0) {
                endGame()
            } else {
                _uiState.update { 
                    it.copy(
                        lives = newLives,
                        gameState = CorrectlyState.SHOW_FAILURE
                    )
                }
                
                // Show same question again after delay
                viewModelScope.launch {
                    delay(1000)
                    if (_uiState.value.totalTimeRemaining > 0) {
                        _uiState.update { it.copy(gameState = CorrectlyState.PLAYING) }
                        startQuestionTimer(progression.getTimeLimit())
                    }
                }
            }
        }
    }
    
    private fun calculateLevelScore(level: Int): Int {
        // Scoring from old project: 4 + (level * 2)
        return 4 + (level * 2)
    }
    
    private fun startQuestionTimer(timeLimit: Long) {
        questionTimerJob?.cancel()
        
        val startTime = System.currentTimeMillis()
        
        questionTimerJob = viewModelScope.launch {
            while (_uiState.value.questionTimeRemaining > 0) {
                delay(100)
                
                val elapsed = System.currentTimeMillis() - startTime
                val remaining = ((timeLimit - elapsed) / 1000).toInt()
                
                if (remaining <= 0) {
                    // Time's up for this question - treat as wrong answer
                    onAnswerClick(false) // Force wrong answer
                    break
                } else {
                    _uiState.update { it.copy(questionTimeRemaining = remaining) }
                }
            }
        }
    }
    
    private fun startGameTimer() {
        gameTimerJob?.cancel()
        
        val startTime = System.currentTimeMillis()
        
        gameTimerJob = viewModelScope.launch {
            while (_uiState.value.totalTimeRemaining > 0) {
                delay(1000)
                
                val elapsed = System.currentTimeMillis() - startTime
                val remaining = ((GAME_TIME - elapsed) / 1000).toInt()
                
                if (remaining <= 0) {
                    _uiState.update { 
                        it.copy(
                            totalTimeRemaining = 0,
                            gameState = CorrectlyState.GAME_OVER
                        )
                    }
                    break
                } else {
                    _uiState.update { it.copy(totalTimeRemaining = remaining) }
                }
            }
        }
    }
    
    private fun endGame() {
        questionTimerJob?.cancel()
        gameTimerJob?.cancel()
        _uiState.update { it.copy(gameState = CorrectlyState.GAME_OVER) }
    }
    
    fun pauseGame() {
        questionTimerJob?.cancel()
        gameTimerJob?.cancel()
        _uiState.update { it.copy(isPaused = true) }
    }
    
    fun resumeGame() {
        _uiState.update { it.copy(isPaused = false) }
        if (_uiState.value.gameState == CorrectlyState.PLAYING) {
            startQuestionTimer(progression.getTimeLimit())
            startGameTimer()
        }
    }
    
    fun restartGame() {
        _uiState.update { it.copy(isPaused = false) }
        startGame()
    }
    
    fun calculateStars(): Int {
        val level = progression.getLevelNumber()
        return when {
            level >= 20 -> 3
            level >= 12 -> 2
            level >= 6 -> 1
            else -> 0
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        questionTimerJob?.cancel()
        gameTimerJob?.cancel()
    }
}

data class CorrectlyUiState(
    val game: Game? = null,
    val gameState: CorrectlyState = CorrectlyState.LOADING,
    val score: Int = 0,
    val level: Int = 1,
    val lives: Int = 3,
    val totalTimeRemaining: Int = 60,
    val questionTimeRemaining: Int = 4,
    val equation: Equation = Equation("", true),
    val isPaused: Boolean = false
)

enum class CorrectlyState {
    LOADING,
    PLAYING,
    SHOW_SUCCESS,
    SHOW_FAILURE,
    GAME_OVER
}

data class Equation(
    val text: String,
    val isCorrect: Boolean
)

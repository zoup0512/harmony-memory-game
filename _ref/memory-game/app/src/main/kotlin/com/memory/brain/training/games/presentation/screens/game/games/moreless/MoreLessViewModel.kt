package com.memory.brain.training.games.presentation.screens.game.games.moreless

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
 * Game 11: More, Less
 * 比较两个数学表达式的大小
 */
class MoreLessViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(MoreLessUiState())
    val uiState: StateFlow<MoreLessUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var levelStartTime = 0L
    private var levelDuration = 6000L
    
    fun startGame() {
        progression.startGame()
        startLevel()
    }
    
    private fun startLevel() {
        val level = progression.getLevelNumber()
        
        // Set level duration
        levelDuration = if (level <= 10) 6000L else 10000L
        
        // Generate expressions based on level
        val (expr1, expr2, correct) = generateExpressions(level)
        
        _uiState.value = _uiState.value.copy(
            level = level,
            expression1 = expr1,
            expression2 = expr2,
            correctAnswer = correct,
            selectedAnswer = null,
            gameState = GameState.PLAYING,
            showFeedback = false,
            timeRemaining = levelDuration
        )
        
        startLevelTimer()
    }
    
    private fun startLevelTimer() {
        timerJob?.cancel()
        levelStartTime = System.currentTimeMillis()
        
        timerJob = viewModelScope.launch {
            while (true) {
                delay(20)
                val elapsed = System.currentTimeMillis() - levelStartTime
                val remaining = levelDuration - elapsed
                
                if (remaining <= 0) {
                    _uiState.value = _uiState.value.copy(
                        timeRemaining = 0
                    )
                    onTimeout()
                    break
                }
                
                _uiState.value = _uiState.value.copy(
                    timeRemaining = remaining
                )
            }
        }
    }
    
    private fun onTimeout() {
        _uiState.value = _uiState.value.copy(
            gameState = GameState.GAME_OVER
        )
    }
    
    fun onAnswerSelected(answer: ComparisonResult) {
        if (_uiState.value.gameState != GameState.PLAYING) return
        
        timerJob?.cancel()
        
        val isCorrect = answer == _uiState.value.correctAnswer
        
        _uiState.value = _uiState.value.copy(
            selectedAnswer = answer,
            showFeedback = true,
            isCorrectAnswer = isCorrect
        )
        
        viewModelScope.launch {
            delay(800)
            
            if (isCorrect) {
                progression.nextLevel()
                startLevel()
            } else {
                _uiState.value = _uiState.value.copy(
                    gameState = GameState.GAME_OVER
                )
            }
        }
    }
    
    private fun generateExpressions(level: Int): Triple<String, String, ComparisonResult> {
        return when (level) {
            1, 2 -> levelType1()
            3 -> levelType2()
            4 -> levelType3()
            5, 6 -> levelType4(10, 20, 4, 9, 2, 5, 1, 3)
            7 -> levelType4(15, 30, 5, 12, 1, 5, 1, 3)
            8, 9 -> levelType4(20, 40, 5, 20, 1, 4, 1, 4)
            10, 11 -> levelType4(20, 50, 15, 40, 1, 5, 1, 5)
            12, 13 -> levelType4(30, 70, 25, 55, 1, 7, 1, 7)
            14, 15 -> levelType5(7, 11, 6, 12, 1, 2, 1, 2)
            16 -> levelType5(9, 13, 8, 14, 1, 3, 1, 3)
            17 -> levelType6(5, 11, 5, 9, 1, 1)
            in 18..20 -> levelType7(30, 49, 5, 9, 5, 9, 4, 5, 1, 3, 1, 3)
            21 -> levelType7(70, 99, 5, 12, 5, 9, 4, 10, 1, 3, 1, 3)
            in 22..24 -> levelType7(110, 149, 8, 15, 6, 12, 4, 10, 1, 3, 1, 3)
            else -> levelType7(200, 300, 11, 20, 11, 30, 4, 10, 2, 5, 3, 6)
        }
    }
    
    // Level Type 1: Simple numbers
    private fun levelType1(): Triple<String, String, ComparisonResult> {
        val x1 = rand(1, 10)
        val x2 = rand(1, 10)
        return Triple("$x1", "$x2", compare(x1, x2))
    }
    
    // Level Type 2: Addition
    private fun levelType2(): Triple<String, String, ComparisonResult> {
        val x1 = rand(1, 10)
        val y1 = rand(1, 10)
        val x2 = rand(1, 10)
        val y2 = rand(1, 10)
        val rez1 = x1 + y1
        val rez2 = x2 + y2
        return Triple("$x1 + $y1", "$x2 + $y2", compare(rez1, rez2))
    }
    
    // Level Type 3: Addition with controlled difference
    private fun levelType3(): Triple<String, String, ComparisonResult> {
        val x1 = rand(5, 20)
        val y1 = rand(5, 20)
        val f1 = rand(2, 3)
        val f2 = rand(3, 4)
        val sign1 = Random.nextBoolean()
        val sign2 = !sign1
        val x2 = if (sign1) x1 + f1 else x1 - f1
        val y2 = if (sign2) y1 + f2 else y1 - f2
        val rez1 = x1 + y1
        val rez2 = x2 + y2
        return Triple("$x1 + $y1", "$x2 + $y2", compare(rez1, rez2))
    }
    
    // Level Type 4: Addition or Subtraction
    private fun levelType4(
        x1Min: Int, x1Max: Int, y1Min: Int, y1Max: Int,
        f1Min: Int, f1Max: Int, f2Min: Int, f2Max: Int
    ): Triple<String, String, ComparisonResult> {
        val x1 = rand(x1Min, x1Max)
        val y1 = rand(y1Min, y1Max)
        val f1 = rand(f1Min, f1Max)
        val f2 = rand(f2Min, f2Max)
        val sign1 = Random.nextBoolean()
        val sign2 = !sign1
        val x2 = if (sign1) x1 + f1 else x1 - f1
        val y2 = if (sign2) y1 + f2 else y1 - f2
        
        val (rez1, rez2, str1, str2) = if (Random.nextBoolean()) {
            val r1 = x1 + y1
            val r2 = x2 + y2
            Quad(r1, r2, "$x1 + $y1", "$x2 + $y2")
        } else {
            val r1 = x1 - y1
            val r2 = x2 - y2
            Quad(r1, r2, "$x1 - $y1", "$x2 - $y2")
        }
        
        return Triple(str1, str2, compare(rez1, rez2))
    }
    
    // Level Type 5: Multiplication
    private fun levelType5(
        x1Min: Int, x1Max: Int, y1Min: Int, y1Max: Int,
        f1Min: Int, f1Max: Int, f2Min: Int, f2Max: Int
    ): Triple<String, String, ComparisonResult> {
        val x1 = rand(x1Min, x1Max)
        val y1 = rand(y1Min, y1Max)
        val f1 = rand(f1Min, f1Max)
        val f2 = rand(f2Min, f2Max)
        val sign1 = Random.nextBoolean()
        val sign2 = !sign1
        val x2 = if (sign1) x1 + f1 else x1 - f1
        val y2 = if (sign2) y1 + f2 else y1 - f2
        val rez1 = x1 * y1
        val rez2 = x2 * y2
        return Triple("$x1 × $y1", "$x2 × $y2", compare(rez1, rez2))
    }
    
    // Level Type 6: Division
    private fun levelType6(
        y1Min: Int, y1Max: Int, z1Min: Int, z1Max: Int,
        f1: Int, f2: Int
    ): Triple<String, String, ComparisonResult> {
        val y1 = rand(y1Min, y1Max)
        val z1 = rand(z1Min, z1Max)
        val sign1 = Random.nextBoolean()
        val y2 = if (!sign1) y1 + f2 else y1 - f2
        val z2 = if (sign1) z1 + f1 else z1 - f1
        val x2 = y2 * z2
        return Triple("${y1 * z1} ÷ $y1", "$x2 ÷ $y2", compare(z1, z2))
    }
    
    // Level Type 7: Complex expressions
    private fun levelType7(
        x1Min: Int, x1Max: Int, y1Min: Int, y1Max: Int,
        z1Min: Int, z1Max: Int, f1Min: Int, f1Max: Int,
        f2Min: Int, f2Max: Int, f3Min: Int, f3Max: Int
    ): Triple<String, String, ComparisonResult> {
        val x1 = rand(x1Min, x1Max)
        val y1 = rand(y1Min, y1Max)
        val z1 = rand(z1Min, z1Max)
        val f1 = rand(f1Min, f1Max)
        val f2 = rand(f2Min, f2Max)
        val f3 = rand(f3Min, f3Max)
        val sign1 = Random.nextBoolean()
        val sign2 = Random.nextBoolean()
        val sign3 = !sign2
        val x2 = if (sign1) x1 + f1 else x1 - f1
        val y2 = if (sign2) y1 + f2 else y1 - f2
        val z2 = if (sign3) z1 + f3 else z1 - f3
        
        val (rez1, rez2, str1, str2) = if (Random.nextBoolean()) {
            val r1 = x1 + (y1 * z1)
            val r2 = x2 + (y2 * z2)
            Quad(r1, r2, "$x1 + $y1 × $z1", "$x2 + $y2 × $z2")
        } else {
            val r1 = x1 - (y1 * z1)
            val r2 = x2 - (y2 * z2)
            Quad(r1, r2, "$x1 - $y1 × $z1", "$x2 - $y2 × $z2")
        }
        
        return Triple(str1, str2, compare(rez1, rez2))
    }
    
    private fun rand(min: Int, max: Int): Int {
        return Random.nextInt(max - min + 1) + min
    }
    
    private fun compare(a: Int, b: Int): ComparisonResult {
        return when {
            a > b -> ComparisonResult.GREATER
            a < b -> ComparisonResult.LESS
            else -> ComparisonResult.EQUAL
        }
    }
    
    private data class Quad<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
    
    fun pauseGame() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        if (_uiState.value.gameState == GameState.PLAYING) {
            startLevelTimer()
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

data class MoreLessUiState(
    val level: Int = 1,
    val expression1: String = "",
    val expression2: String = "",
    val correctAnswer: ComparisonResult = ComparisonResult.EQUAL,
    val selectedAnswer: ComparisonResult? = null,
    val timeRemaining: Long = 6000L,
    val gameState: GameState = GameState.IDLE,
    val showFeedback: Boolean = false,
    val isCorrectAnswer: Boolean = false,
    val isPaused: Boolean = false
)

enum class ComparisonResult {
    GREATER, EQUAL, LESS
}

enum class GameState {
    IDLE, PLAYING, GAME_OVER
}

package com.memory.brain.training.games.presentation.screens.game.games.numbermemory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NumberMemoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val gameId: Int = savedStateHandle.get<Int>("gameId") ?: 2
    
    private val _uiState = MutableStateFlow(NumberMemoryUiState())
    val uiState: StateFlow<NumberMemoryUiState> = _uiState.asStateFlow()
    
    private var countdownJob: Job? = null
    private var displayJob: Job? = null
    
    companion object {
        private const val MAX_LEVELS = 10
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
        _uiState.update { 
            it.copy(
                gameState = NumberMemoryGameState.SHOWING_NUMBER,
                score = 0,
                level = 1,
                lives = 3,
                timeRemaining = 60
            )
        }
        generateNumber()
        startCountdown()
    }
    
    private fun generateNumber() {
        displayJob?.cancel()
        
        // 数字长度随关卡增加：3 + level
        val numberLength = 3 + _uiState.value.level
        val number = buildString {
            repeat(numberLength) {
                append(Random.nextInt(0, 10))
            }
        }
        
        _uiState.update { 
            it.copy(
                currentNumber = number,
                userInput = "",
                gameState = NumberMemoryGameState.SHOWING_NUMBER
            )
        }
        
        // 显示时间：2秒 + 关卡 * 300ms
        val displayTime = 2000L + (_uiState.value.level * 300L)
        
        displayJob = viewModelScope.launch {
            delay(displayTime)
            _uiState.update { it.copy(gameState = NumberMemoryGameState.INPUT_NUMBER) }
        }
    }
    
    fun onNumberInput(digit: String) {
        if (_uiState.value.gameState != NumberMemoryGameState.INPUT_NUMBER) return
        
        val currentInput = _uiState.value.userInput
        if (currentInput.length < _uiState.value.currentNumber.length) {
            _uiState.update { it.copy(userInput = currentInput + digit) }
        }
    }
    
    fun onDeleteDigit() {
        if (_uiState.value.gameState != NumberMemoryGameState.INPUT_NUMBER) return
        
        val currentInput = _uiState.value.userInput
        if (currentInput.isNotEmpty()) {
            _uiState.update { it.copy(userInput = currentInput.dropLast(1)) }
        }
    }
    
    fun submitAnswer() {
        if (_uiState.value.gameState != NumberMemoryGameState.INPUT_NUMBER) return
        
        val isCorrect = _uiState.value.userInput == _uiState.value.currentNumber
        
        if (isCorrect) {
            val newScore = _uiState.value.score + (100 * _uiState.value.level)
            val newLevel = _uiState.value.level + 1
            
            if (newLevel > MAX_LEVELS) {
                _uiState.update { 
                    it.copy(
                        score = newScore,
                        gameState = NumberMemoryGameState.GAME_OVER
                    )
                }
                countdownJob?.cancel()
            } else {
                _uiState.update { 
                    it.copy(
                        score = newScore,
                        level = newLevel,
                        userInput = ""
                    )
                }
                
                viewModelScope.launch {
                    delay(500)
                    generateNumber()
                }
            }
        } else {
            val newLives = _uiState.value.lives - 1
            
            if (newLives <= 0) {
                endGame()
            } else {
                _uiState.update { 
                    it.copy(
                        lives = newLives,
                        userInput = ""
                    )
                }
                
                viewModelScope.launch {
                    delay(500)
                    _uiState.update { it.copy(gameState = NumberMemoryGameState.SHOWING_NUMBER) }
                    delay(2000L)
                    _uiState.update { it.copy(gameState = NumberMemoryGameState.INPUT_NUMBER) }
                }
            }
        }
    }
    
    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (_uiState.value.timeRemaining > 0 && _uiState.value.gameState != NumberMemoryGameState.GAME_OVER) {
                delay(1000)
                _uiState.update { 
                    val newTime = it.timeRemaining - 1
                    if (newTime <= 0) {
                        it.copy(timeRemaining = 0)
                    } else {
                        it.copy(timeRemaining = newTime)
                    }
                }
                
                if (_uiState.value.timeRemaining <= 0) {
                    endGame()
                }
            }
        }
    }
    
    private fun endGame() {
        countdownJob?.cancel()
        displayJob?.cancel()
        _uiState.update { it.copy(gameState = NumberMemoryGameState.GAME_OVER) }
    }
    
    fun pauseGame() {
        countdownJob?.cancel()
        displayJob?.cancel()
        _uiState.update { it.copy(isPaused = true) }
    }
    
    fun resumeGame() {
        _uiState.update { it.copy(isPaused = false) }
        if (_uiState.value.gameState == NumberMemoryGameState.SHOWING_NUMBER) {
            displayJob = viewModelScope.launch {
                delay(2000L)
                _uiState.update { it.copy(gameState = NumberMemoryGameState.INPUT_NUMBER) }
            }
        }
        startCountdown()
    }
    
    fun restartGame() {
        _uiState.update { it.copy(isPaused = false) }
        startGame()
    }
    
    fun calculateStars(): Int {
        val score = _uiState.value.score
        return when {
            score >= 1000 -> 3
            score >= 500 -> 2
            score >= 200 -> 1
            else -> 0
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        countdownJob?.cancel()
        displayJob?.cancel()
    }
}

data class NumberMemoryUiState(
    val game: Game? = null,
    val gameState: NumberMemoryGameState = NumberMemoryGameState.LOADING,
    val score: Int = 0,
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Int = 60,
    val currentNumber: String = "",
    val userInput: String = "",
    val isPaused: Boolean = false
)

enum class NumberMemoryGameState {
    LOADING,
    SHOWING_NUMBER,
    INPUT_NUMBER,
    GAME_OVER
}

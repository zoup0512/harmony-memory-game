package com.memory.brain.training.games.presentation.screens.game.games.colormemory

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
class ColorMemoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val gameId: Int = savedStateHandle.get<Int>("gameId") ?: 3
    
    private val _uiState = MutableStateFlow(ColorMemoryUiState())
    val uiState: StateFlow<ColorMemoryUiState> = _uiState.asStateFlow()
    
    private var countdownJob: Job? = null
    private var displayJob: Job? = null
    
    companion object {
        private const val MAX_LEVELS = 10
        val COLORS = listOf(
            GameColor("红色", 0xFFF44336),
            GameColor("蓝色", 0xFF2196F3),
            GameColor("绿色", 0xFF4CAF50),
            GameColor("黄色", 0xFFFFC107),
            GameColor("紫色", 0xFF9C27B0),
            GameColor("橙色", 0xFFFF9800),
            GameColor("青色", 0xFF00BCD4),
            GameColor("粉色", 0xFFE91E63)
        )
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
                gameState = ColorMemoryGameState.SHOWING_SEQUENCE,
                score = 0,
                level = 1,
                lives = 3,
                timeRemaining = 60
            )
        }
        generateSequence()
        startCountdown()
    }
    
    private fun generateSequence() {
        displayJob?.cancel()
        
        // 序列长度：3 + level
        val sequenceLength = 3 + _uiState.value.level
        val sequence = List(sequenceLength) { COLORS.random() }
        
        _uiState.update { 
            it.copy(
                colorSequence = sequence,
                userSequence = emptyList(),
                currentDisplayIndex = 0,
                gameState = ColorMemoryGameState.SHOWING_SEQUENCE
            )
        }
        
        displaySequence(sequence)
    }
    
    private fun displaySequence(sequence: List<GameColor>) {
        displayJob = viewModelScope.launch {
            for (index in sequence.indices) {
                _uiState.update { it.copy(currentDisplayIndex = index) }
                delay(800) // 每个颜色显示800ms
            }
            _uiState.update { 
                it.copy(
                    currentDisplayIndex = -1,
                    gameState = ColorMemoryGameState.INPUT_SEQUENCE
                )
            }
        }
    }
    
    fun onColorSelected(color: GameColor) {
        if (_uiState.value.gameState != ColorMemoryGameState.INPUT_SEQUENCE) return
        
        val newSequence = _uiState.value.userSequence + color
        _uiState.update { it.copy(userSequence = newSequence) }
        
        // 检查是否完成输入
        if (newSequence.size == _uiState.value.colorSequence.size) {
            checkAnswer()
        }
    }
    
    private fun checkAnswer() {
        val isCorrect = _uiState.value.userSequence == _uiState.value.colorSequence
        
        if (isCorrect) {
            val newScore = _uiState.value.score + (100 * _uiState.value.level)
            val newLevel = _uiState.value.level + 1
            
            if (newLevel > MAX_LEVELS) {
                _uiState.update { 
                    it.copy(
                        score = newScore,
                        gameState = ColorMemoryGameState.GAME_OVER
                    )
                }
                countdownJob?.cancel()
            } else {
                _uiState.update { 
                    it.copy(
                        score = newScore,
                        level = newLevel
                    )
                }
                
                viewModelScope.launch {
                    delay(500)
                    generateSequence()
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
                        userSequence = emptyList()
                    )
                }
                
                viewModelScope.launch {
                    delay(500)
                    displaySequence(_uiState.value.colorSequence)
                }
            }
        }
    }
    
    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (_uiState.value.timeRemaining > 0 && _uiState.value.gameState != ColorMemoryGameState.GAME_OVER) {
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
        _uiState.update { it.copy(gameState = ColorMemoryGameState.GAME_OVER) }
    }
    
    fun pauseGame() {
        countdownJob?.cancel()
        displayJob?.cancel()
        _uiState.update { it.copy(isPaused = true) }
    }
    
    fun resumeGame() {
        _uiState.update { it.copy(isPaused = false) }
        if (_uiState.value.gameState == ColorMemoryGameState.SHOWING_SEQUENCE) {
            displaySequence(_uiState.value.colorSequence)
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

data class GameColor(
    val name: String,
    val colorValue: Long
)

data class ColorMemoryUiState(
    val game: Game? = null,
    val gameState: ColorMemoryGameState = ColorMemoryGameState.LOADING,
    val score: Int = 0,
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Int = 60,
    val colorSequence: List<GameColor> = emptyList(),
    val userSequence: List<GameColor> = emptyList(),
    val currentDisplayIndex: Int = -1,
    val isPaused: Boolean = false
)

enum class ColorMemoryGameState {
    LOADING,
    SHOWING_SEQUENCE,
    INPUT_SEQUENCE,
    GAME_OVER
}

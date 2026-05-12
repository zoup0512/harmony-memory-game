package com.memory.brain.training.games.presentation.screens.game.games.memorygrid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.domain.model.GameProgression1
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Memory Grid ViewModel - Based on Game1MemoryGridActivity.java
 * 
 * Game Rules:
 * - Grid size increases with level (3x3 to 8x8)
 * - Win cells increase with level
 * - 3 lives system
 * - Scoring: 16 + (level * 2)
 * - Game flow: Ready → GridAnimation → ShowChallenge → UserInput
 */
class MemoryGridViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(MemoryGridUiState())
    val uiState: StateFlow<MemoryGridUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression1()
    private var gameFlowJob: Job? = null
    
    init {
        startGame()
    }
    
    fun startGame() {
        progression.startGame()
        
        _uiState.value = MemoryGridUiState(
            level = progression.getLevelNumber(),
            score = 0,
            lives = 3,
            gameState = GameFlowState.READY,
            gridSize = progression.getCurrentGridSize(),
            winCells = progression.getCurrentWinCells(),
            pattern = emptyList(),
            userSelections = emptyList()
        )
        
        startGameFlow()
    }
    
    private fun startGameFlow() {
        gameFlowJob?.cancel()
        gameFlowJob = viewModelScope.launch {
            // State 1: Ready (800ms) - 显示倒计时
            _uiState.value = _uiState.value.copy(
                gameState = GameFlowState.READY,
                showReadyTimer = true,
                readyTimerSeconds = 3
            )
            
            // 倒计时动画：3, 2, 1
            repeat(3) { index ->
                delay(267)  // 800ms / 3 ≈ 267ms per count
                _uiState.value = _uiState.value.copy(
                    readyTimerSeconds = 3 - index - 1
                )
            }
            
            _uiState.value = _uiState.value.copy(showReadyTimer = false)
            
            // State 2: Grid Animation (1000ms)
            _uiState.value = _uiState.value.copy(gameState = GameFlowState.GRID_ANIMATION)
            delay(1000)
            
            // State 3: Show Challenge
            generatePattern()
            _uiState.value = _uiState.value.copy(gameState = GameFlowState.SHOW_CHALLENGE)
            val showDuration = 1200L + (_uiState.value.level * 200L)
            delay(showDuration)
            
            // State 4: User Input
            _uiState.value = _uiState.value.copy(gameState = GameFlowState.USER_INPUT)
        }
    }
    
    private fun generatePattern() {
        val gridSize = progression.getCurrentGridSize()
        val winCells = progression.getCurrentWinCells()
        val totalCells = gridSize * gridSize
        
        val positions = mutableSetOf<Int>()
        while (positions.size < winCells) {
            positions.add(Random.nextInt(totalCells))
        }
        
        _uiState.value = _uiState.value.copy(
            gridSize = gridSize,
            winCells = winCells,
            pattern = positions.toList(),
            userSelections = emptyList()
        )
    }
    
    fun onCellClick(position: Int) {
        if (_uiState.value.gameState != GameFlowState.USER_INPUT) {
            return
        }
        
        val currentSelections = _uiState.value.userSelections.toMutableList()
        
        if (position in currentSelections) {
            // Deselect
            currentSelections.remove(position)
        } else {
            // Select
            currentSelections.add(position)
            
            // Check if this is a correct cell
            if (position in _uiState.value.pattern) {
                // Correct cell clicked
                if (currentSelections.size == _uiState.value.winCells) {
                    // All correct cells selected
                    onSuccessfulCompletion()
                }
            } else {
                // Wrong cell clicked
                onFailure()
                return
            }
        }
        
        _uiState.value = _uiState.value.copy(userSelections = currentSelections)
    }
    
    private fun onSuccessfulCompletion() {
        // Calculate score: 16 + (level * 2)
        val levelScore = 16 + (_uiState.value.level * 2)
        val newScore = _uiState.value.score + levelScore
        
        _uiState.value = _uiState.value.copy(
            score = newScore,
            gameState = GameFlowState.SUCCESS_ANIMATION
        )
        
        // Move to next level after animation
        viewModelScope.launch {
            delay(500)
            progression.nextLevel()
            
            _uiState.value = _uiState.value.copy(
                level = progression.getLevelNumber()
            )
            
            startGameFlow()
        }
    }
    
    private fun onFailure() {
        val newLives = _uiState.value.lives - 1
        
        if (newLives <= 0) {
            // Game over
            _uiState.value = _uiState.value.copy(
                lives = 0,
                gameState = GameFlowState.GAME_OVER
            )
            gameFlowJob?.cancel()
        } else {
            // Lose a life and restart level
            _uiState.value = _uiState.value.copy(
                lives = newLives,
                gameState = GameFlowState.FAILURE_ANIMATION
            )
            
            // Restart level after animation
            viewModelScope.launch {
                delay(1000)
                startGameFlow()
            }
        }
    }
    
    fun calculateStars(): Int {
        val level = _uiState.value.level
        return when {
            level >= 15 -> 3
            level >= 11 -> 2
            level >= 4 -> 1
            else -> 0
        }
    }
    
    fun retryGame() {
        startGame()
    }
    
    fun pauseGame() {
        // 暂停游戏流程
        gameFlowJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        // 恢复游戏
        _uiState.value = _uiState.value.copy(isPaused = false)
        
        // 根据当前游戏状态决定是否需要重新启动流程
        when (_uiState.value.gameState) {
            GameFlowState.USER_INPUT -> {
                // 用户输入阶段，不需要重新启动流程
                // 用户可以继续点击
            }
            GameFlowState.GAME_OVER -> {
                // 游戏结束，不恢复
            }
            else -> {
                // 其他阶段，重新启动游戏流程
                startGameFlow()
            }
        }
    }
    
    fun restartGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        startGame()
    }
    
    override fun onCleared() {
        super.onCleared()
        gameFlowJob?.cancel()
    }
}

/**
 * UI State for Memory Grid Game
 */
data class MemoryGridUiState(
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val gameState: GameFlowState = GameFlowState.READY,
    val gridSize: Int = 3,
    val winCells: Int = 3,
    val pattern: List<Int> = emptyList(),
    val userSelections: List<Int> = emptyList(),
    val readyTimerSeconds: Int = 0,  // 倒计时秒数（Ready阶段）
    val showReadyTimer: Boolean = false,  // 是否显示倒计时
    val isPaused: Boolean = false  // 是否暂停
)

/**
 * Game Flow States - Based on old project
 */
enum class GameFlowState {
    READY,              // "Ready!" text shown
    GRID_ANIMATION,     // Cells animate in
    SHOW_CHALLENGE,     // Pattern shown
    USER_INPUT,         // Player selects cells
    SUCCESS_ANIMATION,  // Success feedback
    FAILURE_ANIMATION,  // Failure feedback
    GAME_OVER          // Game ended
}

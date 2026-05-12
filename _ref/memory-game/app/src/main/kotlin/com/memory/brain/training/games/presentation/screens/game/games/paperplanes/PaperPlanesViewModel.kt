package com.memory.brain.training.games.presentation.screens.game.games.paperplanes

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
 * Game 14: Paper Planes
 * 纸飞机方向游戏
 * 30秒计时，根据飞机移动方向或朝向滑动
 */
class PaperPlanesViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(PaperPlanesUiState())
    val uiState: StateFlow<PaperPlanesUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var gameStartTime = 0L
    private val gameDuration = 30000L // 30 seconds
    
    private var movingClouds: SwipeDirection? = null
    
    enum class GameType {
        TYPE_MOVING,      // 根据飞机移动方向滑动
        TYPE_ORIENTATION  // 根据飞机朝向滑动
    }
    
    enum class SwipeDirection {
        LEFT, RIGHT, UP, DOWN
    }
    
    data class Plane(
        val x: Float,
        val y: Float,
        val rotation: Float // 0=UP, 90=RIGHT, 180=DOWN, 270=LEFT
    )
    
    fun startGame() {
        progression.startGame()
        gameStartTime = System.currentTimeMillis()
        movingClouds = null
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
        
        // Level 16+: 添加移动云朵
        if (level > 15 && movingClouds == null) {
            movingClouds = if (Random.nextBoolean()) SwipeDirection.LEFT else SwipeDirection.RIGHT
        }
        
        // Level 11+: 不同速度
        val differentSpeed = level > 10
        
        val gameType = if (Random.nextBoolean()) GameType.TYPE_MOVING else GameType.TYPE_ORIENTATION
        val movingDirection = SwipeDirection.values().random()
        val orientationDirection = SwipeDirection.values().random()
        
        _uiState.value = _uiState.value.copy(
            level = level,
            gameType = gameType,
            movingDirection = movingDirection,
            orientationDirection = orientationDirection,
            movingClouds = movingClouds,
            differentSpeed = differentSpeed,
            gameState = GameState.PLAYING,
            showFeedback = false
        )
    }
    
    fun onSwipe(direction: SwipeDirection) {
        if (_uiState.value.gameState != GameState.PLAYING) return
        
        val isCorrect = when (_uiState.value.gameType) {
            GameType.TYPE_MOVING -> direction == _uiState.value.movingDirection
            GameType.TYPE_ORIENTATION -> direction == _uiState.value.orientationDirection
        }
        
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

data class PaperPlanesUiState(
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Long = 30000L,
    val gameType: PaperPlanesViewModel.GameType = PaperPlanesViewModel.GameType.TYPE_MOVING,
    val movingDirection: PaperPlanesViewModel.SwipeDirection = PaperPlanesViewModel.SwipeDirection.RIGHT,
    val orientationDirection: PaperPlanesViewModel.SwipeDirection = PaperPlanesViewModel.SwipeDirection.UP,
    val movingClouds: PaperPlanesViewModel.SwipeDirection? = null,
    val differentSpeed: Boolean = false,
    val gameState: GameState = GameState.IDLE,
    val showFeedback: Boolean = false,
    val isCorrectAnswer: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    IDLE, PLAYING, GAME_OVER
}

package com.memory.brain.training.games.presentation.screens.game.games.imagevortex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.domain.model.GameProgression1
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Game 6: Image Vortex (图像漩涡)
 * Image memory game with vortex animation
 * Based on: Game7ImageVortexActivity.java
 */
class ImageVortexViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(ImageVortexUiState())
    val uiState: StateFlow<ImageVortexUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression1()
    private var gameJob: Job? = null
    private var timerJob: Job? = null
    
    // Emoji images to use instead of drawable resources
    private val availableEmojis = listOf(
        "🍎", "🍊", "🍋", "🍌", "🍉", "🍇", "🍓", "🍒", "🍑", "🥝",
        "🍍", "🥥", "🥑", "🍆", "🥕", "🌽", "🌶️", "🥒", "🥬", "🥦",
        "🍄", "🥜", "🌰", "🍞", "🥐", "🥖", "🥨", "🥯", "🥞", "🧇",
        "🧀", "🍖", "🍗", "🥩", "🥓", "🍔", "🍟", "🍕", "🌭", "🥪",
        "🌮", "🌯", "🥙", "🧆", "🥚", "🍳", "🥘", "🍲", "🥣", "🥗"
    )
    
    private var usedEmojis = mutableListOf<String>()
    private var targetEmoji = ""
    
    init {
        startGame()
    }
    
    fun startGame() {
        progression.startGame()
        usedEmojis.clear()
        startLevel()
    }
    
    private fun startLevel() {
        val gridSize = progression.getCurrentGridSize()
        val imageCount = progression.getCurrentWinCells()
        
        // Select emojis for this level
        val levelEmojis = selectEmojis(imageCount)
        usedEmojis.addAll(levelEmojis)
        
        // Select target emoji (the one to find)
        targetEmoji = levelEmojis.random()
        
        // Generate grid with emojis
        val grid = generateGrid(gridSize, levelEmojis)
        
        _uiState.value = ImageVortexUiState(
            level = progression.getLevelNumber(),
            score = _uiState.value.score,
            lives = _uiState.value.lives,
            gridSize = gridSize,
            grid = grid,
            targetEmoji = targetEmoji,
            gameState = GameState.SHOWING_IMAGES,
            isPlaying = true,
            timeRemaining = 60000L
        )
        
        // Show images then hide
        showImages()
        
        // Start timer
        startTimer()
    }
    
    private fun selectEmojis(count: Int): List<String> {
        val remaining = availableEmojis.filter { !usedEmojis.contains(it) }.toMutableList()
        
        if (remaining.size < count) {
            // Reset if we run out
            usedEmojis.clear()
            return availableEmojis.shuffled().take(count)
        }
        
        return remaining.shuffled().take(count)
    }
    
    private fun generateGrid(gridSize: Int, emojis: List<String>): List<String> {
        val totalCells = gridSize * gridSize
        val grid = MutableList(totalCells) { "" }
        
        // Place emojis randomly
        val positions = (0 until totalCells).shuffled().take(emojis.size)
        emojis.forEachIndexed { index, emoji ->
            grid[positions[index]] = emoji
        }
        
        return grid
    }
    
    private fun showImages() {
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            delay(2000) // Show for 2 seconds
            
            _uiState.value = _uiState.value.copy(
                gameState = GameState.USER_INPUT
            )
        }
    }
    
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            val duration = 60000L
            
            while (_uiState.value.isPlaying && _uiState.value.gameState == GameState.USER_INPUT) {
                val elapsed = System.currentTimeMillis() - startTime
                val remaining = duration - elapsed
                
                if (remaining <= 0) {
                    handleTimeout()
                    break
                }
                
                _uiState.value = _uiState.value.copy(
                    timeRemaining = remaining
                )
                
                delay(100)
            }
        }
    }
    
    fun onCellClick(cellIndex: Int) {
        if (_uiState.value.gameState != GameState.USER_INPUT) return
        
        val clickedEmoji = _uiState.value.grid[cellIndex]
        
        if (clickedEmoji == targetEmoji) {
            handleSuccess()
        } else {
            handleFailure()
        }
    }
    
    private fun handleSuccess() {
        timerJob?.cancel()
        
        val scoreGain = 16 + (progression.getLevelNumber() * 2)
        val newScore = _uiState.value.score + scoreGain
        
        _uiState.value = _uiState.value.copy(
            score = newScore,
            gameState = GameState.SUCCESS_FEEDBACK
        )
        
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            delay(500)
            progression.nextLevel()
            startLevel()
        }
    }
    
    private fun handleFailure() {
        timerJob?.cancel()
        
        val newLives = _uiState.value.lives - 1
        
        _uiState.value = _uiState.value.copy(
            lives = newLives,
            gameState = GameState.FAILURE_FEEDBACK
        )
        
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            delay(1000)
            
            if (newLives <= 0) {
                _uiState.value = _uiState.value.copy(
                    isPlaying = false
                )
            } else {
                startLevel()
            }
        }
    }
    
    private fun handleTimeout() {
        handleFailure()
    }
    
    fun pauseGame() {
        timerJob?.cancel()
        gameJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        if (_uiState.value.gameState == GameState.USER_INPUT) {
            startTimer()
        }
    }
    
    fun restartGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        startGame()
    }
    
    fun calculateStars(): Int {
        val level = progression.getLevelNumber()
        return when {
            level >= 15 -> 3
            level >= 10 -> 2
            level >= 5 -> 1
            else -> 0
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        gameJob?.cancel()
        timerJob?.cancel()
    }
}

data class ImageVortexUiState(
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val gridSize: Int = 3,
    val grid: List<String> = emptyList(),
    val targetEmoji: String = "",
    val gameState: GameState = GameState.SHOWING_IMAGES,
    val isPlaying: Boolean = false,
    val timeRemaining: Long = 60000L,
    val isPaused: Boolean = false
)

enum class GameState {
    SHOWING_IMAGES,
    USER_INPUT,
    SUCCESS_FEEDBACK,
    FAILURE_FEEDBACK
}

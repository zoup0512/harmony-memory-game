package com.memory.brain.training.games.presentation.screens.game.games.findthepicture

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
 * Game 8: Find the Picture (找图片)
 * Find the previously seen picture
 * Based on: Game9FindThePictureActivity.java
 */
class FindThePictureViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(FindThePictureUiState())
    val uiState: StateFlow<FindThePictureUiState> = _uiState.asStateFlow()
    
    private val progression = GameProgression1()
    private var gameJob: Job? = null
    
    // Emoji pictures to use
    private val availableEmojis = listOf(
        "😀", "😃", "😄", "😁", "😆", "😅", "🤣", "😂", "🙂", "🙃",
        "😉", "😊", "😇", "🥰", "😍", "🤩", "😘", "😗", "😚", "😙",
        "😋", "😛", "😜", "🤪", "😝", "🤑", "🤗", "🤭", "🤫", "🤔",
        "🤐", "🤨", "😐", "😑", "😶", "😏", "😒", "🙄", "😬", "🤥",
        "😌", "😔", "😪", "🤤", "😴", "😷", "🤒", "🤕", "🤢", "🤮"
    )
    
    private var usedEmojis = mutableListOf<String>()
    private var targetEmojiIndex = 0
    
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
        val pictureCount = progression.getCurrentWinCells()
        
        // Select emojis for this level
        val levelEmojis = selectEmojis(pictureCount)
        
        // Select target emoji (the one to find)
        targetEmojiIndex = Random.nextInt(levelEmojis.size)
        val targetEmoji = levelEmojis[targetEmojiIndex]
        
        // Generate grid with emojis
        val grid = generateGrid(gridSize, levelEmojis)
        
        _uiState.value = FindThePictureUiState(
            level = progression.getLevelNumber(),
            score = _uiState.value.score,
            lives = _uiState.value.lives,
            gridSize = gridSize,
            grid = grid,
            targetEmoji = targetEmoji,
            gameState = GameState.SHOWING_PICTURES,
            isPlaying = true
        )
        
        // Show pictures then hide and show target
        showPictures()
    }
    
    private fun selectEmojis(count: Int): List<String> {
        val remaining = availableEmojis.filter { !usedEmojis.contains(it) }.toMutableList()
        
        if (remaining.size < count) {
            // Reset if we run out
            usedEmojis.clear()
            return availableEmojis.shuffled().take(count)
        }
        
        val selected = remaining.shuffled().take(count)
        usedEmojis.addAll(selected)
        return selected
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
    
    private fun showPictures() {
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            delay(2400) // Show for 2.4 seconds
            
            _uiState.value = _uiState.value.copy(
                gameState = GameState.HIDING
            )
            
            delay(800) // Hide for 0.8 seconds
            
            _uiState.value = _uiState.value.copy(
                gameState = GameState.USER_INPUT
            )
        }
    }
    
    fun onCellClick(cellIndex: Int) {
        if (_uiState.value.gameState != GameState.USER_INPUT) return
        
        val clickedEmoji = _uiState.value.grid[cellIndex]
        
        if (clickedEmoji == _uiState.value.targetEmoji) {
            handleSuccess()
        } else if (clickedEmoji.isNotEmpty()) {
            handleFailure()
        }
    }
    
    private fun handleSuccess() {
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
    
    fun pauseGame() {
        gameJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        if (_uiState.value.gameState == GameState.SHOWING_PICTURES) {
            showPictures()
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
    }
}

data class FindThePictureUiState(
    val level: Int = 1,
    val score: Int = 0,
    val lives: Int = 3,
    val gridSize: Int = 3,
    val grid: List<String> = emptyList(),
    val targetEmoji: String = "",
    val gameState: GameState = GameState.SHOWING_PICTURES,
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    SHOWING_PICTURES,
    HIDING,
    USER_INPUT,
    SUCCESS_FEEDBACK,
    FAILURE_FEEDBACK
}

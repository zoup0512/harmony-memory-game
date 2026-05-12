package com.memory.brain.training.games.presentation.screens.game.games.oneandonly

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.Game
import com.memory.brain.training.games.domain.model.GameProgression3
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class OneAndOnlyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val gameId: Int = savedStateHandle.get<Int>("gameId") ?: 24
    
    private val _uiState = MutableStateFlow(OneAndOnlyUiState())
    val uiState: StateFlow<OneAndOnlyUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private val progression = GameProgression3()
    
    companion object {
        private const val GAME_TIME = 60000L // 60 seconds
        private const val MAX_LEVEL = 46
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
                gameState = OneAndOnlyState.PLAYING,
                score = 0,
                level = 1,
                timeRemaining = 60
            )
        }
        startTimer()
        generateLevel()
    }
    
    private fun generateLevel() {
        val levelNumber = progression.getLevelNumber()
        
        // Limit to max level
        val actualLevel = if (levelNumber > MAX_LEVEL) MAX_LEVEL else levelNumber
        
        // Generate elements for this level
        val elements = generateElements(actualLevel)
        
        _uiState.update { 
            it.copy(
                elements = elements,
                level = levelNumber,
                gameState = OneAndOnlyState.PLAYING
            )
        }
    }
    
    /**
     * Generate elements based on level
     * Logic from Game10OneAndOnlyActivity.java
     */
    private fun generateElements(levelNumber: Int): List<Element> {
        val numberOfGroups = getNumberOfGroups(levelNumber)
        val totalElements = levelNumber + 1
        
        // Create groups array: [1, 2, 2, 2, ...] (first is unique)
        val groups = IntArray(numberOfGroups + 1) { 2 }
        groups[0] = 1 // Unique element
        
        // Distribute remaining elements
        var remaining = totalElements - (numberOfGroups * 2) - 1
        while (remaining > 0) {
            val randomGroup = Random.nextInt(numberOfGroups) + 1
            groups[randomGroup]++
            remaining--
        }
        
        // Get available colors for this level
        val colors = getColors(levelNumber)
        
        // Create random elements (one per group)
        val randomElements = mutableListOf<Element>()
        for (i in 0 until groups.size) {
            val color = colors[i % colors.size]
            val shape = Random.nextInt(4) // 4 shapes
            randomElements.add(Element(color, shape))
        }
        
        // Shuffle until we get a valid set
        do {
            randomElements.shuffle()
        } while (!isCorrectSet(randomElements, groups.size, levelNumber))
        
        // Mark the first element as the winning element
        randomElements[0] = randomElements[0].copy(isWin = true)
        
        // Build final list by duplicating elements according to groups
        val result = mutableListOf<Element>()
        for (i in groups.indices) {
            repeat(groups[i]) {
                result.add(randomElements[i])
            }
        }
        
        // Shuffle final list
        result.shuffle()
        
        return result
    }
    
    /**
     * Check if the element set is valid
     * The winning element must be unique by color OR shape
     */
    private fun isCorrectSet(
        randomElements: List<Element>,
        groupsCount: Int,
        levelNumber: Int
    ): Boolean {
        val winElement = randomElements[0]
        
        var isUniqueByColor = levelNumber < 7
        var isUniqueByShape = levelNumber < 12
        
        val colors = mutableSetOf<Int>()
        colors.add(winElement.color)
        
        // Check against other group representatives
        for (i in 1 until groupsCount) {
            val element = randomElements[i]
            colors.add(element.color)
            
            if (winElement.color == element.color) {
                isUniqueByColor = true
            }
            if (winElement.shape == element.shape) {
                isUniqueByShape = true
            }
        }
        
        // Check color count
        val requiredColorCount = if (levelNumber <= 3) 2 else 3
        val hasCorrectColorCount = colors.size == requiredColorCount
        
        return isUniqueByColor && isUniqueByShape && hasCorrectColorCount
    }
    
    /**
     * Get number of groups (different element types) for level
     */
    private fun getNumberOfGroups(levelNumber: Int): Int {
        return when {
            levelNumber <= 2 -> 1
            levelNumber <= 5 -> 2
            levelNumber <= 10 -> 3
            else -> 4
        }
    }
    
    /**
     * Get available colors for level
     */
    private fun getColors(levelNumber: Int): List<Int> {
        val numberOfColors = if (levelNumber <= 3) 2 else 3
        
        val allColors = listOf(
            Element.COLOR_RED,
            Element.COLOR_BLUE,
            Element.COLOR_GREEN,
            Element.COLOR_YELLOW,
            Element.COLOR_PURPLE,
            Element.COLOR_ORANGE
        ).shuffled()
        
        return allColors.take(numberOfColors)
    }
    
    fun onElementClick(element: Element) {
        if (_uiState.value.gameState != OneAndOnlyState.PLAYING) {
            return
        }
        
        if (element.isWin) {
            // Correct answer!
            val levelScore = calculateLevelScore(progression.getLevelNumber())
            val newScore = _uiState.value.score + levelScore
            
            _uiState.update { 
                it.copy(
                    score = newScore,
                    gameState = OneAndOnlyState.LEVEL_COMPLETE
                )
            }
            
            // Move to next level after brief delay
            viewModelScope.launch {
                delay(500)
                
                // Check if time is still remaining
                if (_uiState.value.timeRemaining > 0) {
                    progression.nextLevel()
                    generateLevel()
                }
            }
        } else {
            // Wrong answer - show briefly then continue
            _uiState.update { 
                it.copy(gameState = OneAndOnlyState.SHOW_FAILURE)
            }
            
            viewModelScope.launch {
                delay(300)
                if (_uiState.value.timeRemaining > 0) {
                    _uiState.update { it.copy(gameState = OneAndOnlyState.PLAYING) }
                }
            }
        }
    }
    
    private fun calculateLevelScore(level: Int): Int {
        // Scoring from old project: 8 + (level * 2)
        return 8 + (level * 2)
    }
    
    private fun startTimer() {
        timerJob?.cancel()
        
        val startTime = System.currentTimeMillis()
        
        timerJob = viewModelScope.launch {
            while (_uiState.value.timeRemaining > 0) {
                delay(100) // Update every 100ms for smooth progress bar
                
                val elapsed = System.currentTimeMillis() - startTime
                val remaining = ((GAME_TIME - elapsed) / 1000).toInt()
                
                if (remaining <= 0) {
                    _uiState.update { 
                        it.copy(
                            timeRemaining = 0,
                            gameState = OneAndOnlyState.GAME_OVER
                        )
                    }
                    break
                } else {
                    _uiState.update { it.copy(timeRemaining = remaining) }
                }
            }
        }
    }
    
    fun pauseGame() {
        timerJob?.cancel()
        _uiState.update { it.copy(isPaused = true) }
    }
    
    fun resumeGame() {
        _uiState.update { it.copy(isPaused = false) }
        startTimer()
    }
    
    fun restartGame() {
        _uiState.update { it.copy(isPaused = false) }
        startGame()
    }
    
    fun calculateStars(): Int {
        val level = progression.getLevelNumber()
        return when {
            level >= 30 -> 3
            level >= 20 -> 2
            level >= 10 -> 1
            else -> 0
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}

data class OneAndOnlyUiState(
    val game: Game? = null,
    val gameState: OneAndOnlyState = OneAndOnlyState.LOADING,
    val score: Int = 0,
    val level: Int = 1,
    val timeRemaining: Int = 60,
    val elements: List<Element> = emptyList(),
    val isPaused: Boolean = false
)

enum class OneAndOnlyState {
    LOADING,
    PLAYING,
    LEVEL_COMPLETE,
    SHOW_FAILURE,
    GAME_OVER
}

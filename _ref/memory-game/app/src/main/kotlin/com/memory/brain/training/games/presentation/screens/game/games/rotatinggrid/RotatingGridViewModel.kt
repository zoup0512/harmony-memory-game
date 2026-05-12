package com.memory.brain.training.games.presentation.screens.game.games.rotatinggrid

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.Game
import com.memory.brain.training.games.domain.model.GameProgression2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RotatingGridViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val gameId: Int = savedStateHandle.get<Int>("gameId") ?: 2
    
    private val _uiState = MutableStateFlow(RotatingGridUiState())
    val uiState: StateFlow<RotatingGridUiState> = _uiState.asStateFlow()
    
    private var countdownJob: Job? = null
    private var gameFlowJob: Job? = null
    private val progression = GameProgression2()
    
    // Rotation angles from old project
    private val rotationAngles = listOf(90f, -180f, 270f, -90f, 180f, -270f)
    
    companion object {
        private const val READY_DURATION = 800L
        private const val GRID_ANIMATION_DURATION = 1000L
        private const val SHOW_CHALLENGE_BASE_DURATION = 1200L
        private const val ROTATION_DURATION = 2000L
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
                gameState = RotatingGridState.READY,
                score = 0,
                level = 1,
                lives = 3,
                timeRemaining = 60
            )
        }
        startCountdown()
        startLevel()
    }
    
    private fun startLevel() {
        gameFlowJob?.cancel()
        
        val gridSize = progression.getCurrentGridSize()
        val winCells = progression.getCurrentWinCells()
        
        // Generate random pattern
        val positions = mutableSetOf<Int>()
        while (positions.size < winCells) {
            positions.add(Random.nextInt(gridSize * gridSize))
        }
        
        // Select random rotation angle
        val rotationAngle = rotationAngles.random()
        
        _uiState.update { 
            it.copy(
                gridSize = gridSize,
                pattern = positions.toList(),
                userSelections = emptyList(),
                rotationAngle = rotationAngle,
                level = progression.getLevelNumber()
            )
        }
        
        // Start game flow
        gameFlowJob = viewModelScope.launch {
            // 1. Ready state
            _uiState.update { it.copy(gameState = RotatingGridState.READY) }
            delay(READY_DURATION)
            
            // 2. Grid animation
            _uiState.update { it.copy(gameState = RotatingGridState.GRID_ANIMATION) }
            delay(GRID_ANIMATION_DURATION)
            
            // 3. Show challenge
            _uiState.update { it.copy(gameState = RotatingGridState.SHOW_CHALLENGE) }
            val challengeDuration = SHOW_CHALLENGE_BASE_DURATION + (progression.getLevelNumber() * 200L)
            delay(challengeDuration)
            
            // 4. Rotation
            _uiState.update { it.copy(gameState = RotatingGridState.ROTATING) }
            delay(ROTATION_DURATION)
            
            // 5. User input
            _uiState.update { it.copy(gameState = RotatingGridState.PLAYER_TURN) }
        }
    }
    
    fun onCellClick(position: Int) {
        if (_uiState.value.gameState != RotatingGridState.PLAYER_TURN) {
            return
        }
        
        val currentSelections = _uiState.value.userSelections.toMutableList()
        
        if (position in currentSelections) {
            currentSelections.remove(position)
        } else {
            currentSelections.add(position)
        }
        
        _uiState.update { it.copy(userSelections = currentSelections) }
    }
    
    fun submitAnswer() {
        if (_uiState.value.gameState != RotatingGridState.PLAYER_TURN) {
            return
        }
        
        val pattern = _uiState.value.pattern.toSet()
        val userSelections = _uiState.value.userSelections.toSet()
        
        // Calculate rotated positions
        val rotatedPattern = rotatePattern(
            pattern = pattern,
            gridSize = _uiState.value.gridSize,
            angle = _uiState.value.rotationAngle
        )
        
        val isCorrect = rotatedPattern == userSelections
        
        if (isCorrect) {
            // Correct answer
            val levelScore = calculateLevelScore(progression.getLevelNumber())
            val newScore = _uiState.value.score + levelScore
            
            _uiState.update { 
                it.copy(
                    score = newScore,
                    gameState = RotatingGridState.LEVEL_COMPLETE
                )
            }
            
            // Move to next level after delay
            viewModelScope.launch {
                delay(500)
                progression.nextLevel()
                startLevel()
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
                        userSelections = emptyList(),
                        gameState = RotatingGridState.SHOW_FAILURE
                    )
                }
                
                // Restart same level after delay
                viewModelScope.launch {
                    delay(1000)
                    startLevel()
                }
            }
        }
    }
    
    /**
     * Rotate pattern positions based on angle
     * Matches the rotation logic from Game2RotatingGridActivity
     */
    private fun rotatePattern(pattern: Set<Int>, gridSize: Int, angle: Float): Set<Int> {
        return pattern.map { position ->
            val row = position / gridSize
            val col = position % gridSize
            
            val (newRow, newCol) = when (angle) {
                90f -> Pair(col, gridSize - 1 - row)
                -90f, 270f -> Pair(gridSize - 1 - col, row)
                180f, -180f -> Pair(gridSize - 1 - row, gridSize - 1 - col)
                -270f -> Pair(col, gridSize - 1 - row)
                else -> Pair(row, col)
            }
            
            newRow * gridSize + newCol
        }.toSet()
    }
    
    private fun calculateLevelScore(level: Int): Int {
        // Scoring from old project: 10 + (level * 2)
        return 10 + (level * 2)
    }
    
    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (_uiState.value.timeRemaining > 0 && _uiState.value.gameState != RotatingGridState.GAME_OVER) {
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
        gameFlowJob?.cancel()
        _uiState.update { it.copy(gameState = RotatingGridState.GAME_OVER) }
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
        countdownJob?.cancel()
        gameFlowJob?.cancel()
    }
}

data class RotatingGridUiState(
    val game: Game? = null,
    val gameState: RotatingGridState = RotatingGridState.LOADING,
    val score: Int = 0,
    val level: Int = 1,
    val lives: Int = 3,
    val timeRemaining: Int = 60,
    val gridSize: Int = 3,
    val pattern: List<Int> = emptyList(),
    val userSelections: List<Int> = emptyList(),
    val rotationAngle: Float = 0f
)

enum class RotatingGridState {
    LOADING,
    READY,
    GRID_ANIMATION,
    SHOW_CHALLENGE,
    ROTATING,
    PLAYER_TURN,
    LEVEL_COMPLETE,
    SHOW_FAILURE,
    GAME_OVER
}

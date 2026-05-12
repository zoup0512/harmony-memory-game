package com.memory.brain.training.games.presentation.screens.game.games.symmetry

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
 * Game 20: Symmetry
 * 判断图案是否对称，点击对称的单元格
 */
class SymmetryViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(SymmetryUiState())
    val uiState: StateFlow<SymmetryUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var gameStartTime = 0L
    private val gameDuration = 80000L
    
    data class CellData(
        val row: Int,
        val col: Int,
        val isSymmetric: Boolean,
        val isClicked: Boolean = false
    )
    
    fun startGame() {
        progression.startGame()
        gameStartTime = System.currentTimeMillis()
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
        val (width, height) = getGridSize(level)
        val targetCount = minOf(level + 2, (width * height) / 3)
        
        // Generate symmetric pattern
        val cells = generateSymmetricPattern(width, height, targetCount)
        
        _uiState.value = _uiState.value.copy(
            level = level,
            gridWidth = width,
            gridHeight = height,
            cells = cells,
            gameState = GameState.PLAYING,
            showFeedback = false
        )
    }
    
    private fun getGridSize(level: Int): Pair<Int, Int> {
        return when {
            level <= 2 -> 4 to 3
            level <= 4 -> 4 to 4
            level <= 8 -> 6 to 5
            level <= 10 -> 6 to 6
            level <= 13 -> 8 to 7
            else -> 8 to 8
        }
    }
    
    private fun generateSymmetricPattern(width: Int, height: Int, targetCount: Int): List<CellData> {
        val cells = mutableListOf<CellData>()
        val symmetricCells = mutableSetOf<Pair<Int, Int>>()
        
        // Generate random symmetric cells
        repeat(targetCount) {
            val col = Random.nextInt(width / 2)
            val row = Random.nextInt(height)
            symmetricCells.add(row to col)
            symmetricCells.add(row to (width - 1 - col))
        }
        
        for (row in 0 until height) {
            for (col in 0 until width) {
                cells.add(
                    CellData(
                        row = row,
                        col = col,
                        isSymmetric = symmetricCells.contains(row to col)
                    )
                )
            }
        }
        
        return cells
    }
    
    fun onCellClicked(cell: CellData) {
        if (_uiState.value.gameState != GameState.PLAYING) return
        if (cell.isClicked) return
        
        val updatedCells = _uiState.value.cells.map {
            if (it.row == cell.row && it.col == cell.col) {
                it.copy(isClicked = true)
            } else it
        }
        
        _uiState.value = _uiState.value.copy(cells = updatedCells)
        
        if (cell.isSymmetric) {
            // Check if all symmetric cells clicked
            val allSymmetricClicked = updatedCells.filter { it.isSymmetric }.all { it.isClicked }
            if (allSymmetricClicked) {
                _uiState.value = _uiState.value.copy(
                    showFeedback = true,
                    isCorrectAnswer = true
                )
                
                viewModelScope.launch {
                    delay(800)
                    progression.nextLevel()
                    startLevel()
                }
            }
        } else {
            // Wrong cell
            _uiState.value = _uiState.value.copy(
                showFeedback = true,
                isCorrectAnswer = false
            )
            
            viewModelScope.launch {
                delay(800)
                
                val newLives = _uiState.value.lives - 1
                if (newLives <= 0) {
                    _uiState.value = _uiState.value.copy(
                        lives = 0,
                        gameState = GameState.GAME_OVER
                    )
                } else {
                    _uiState.value = _uiState.value.copy(lives = newLives)
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

data class SymmetryUiState(
    val level: Int = 1,
    val lives: Int = 3,
    val gridWidth: Int = 4,
    val gridHeight: Int = 3,
    val cells: List<SymmetryViewModel.CellData> = emptyList(),
    val timeRemaining: Long = 80000L,
    val gameState: GameState = GameState.IDLE,
    val showFeedback: Boolean = false,
    val isCorrectAnswer: Boolean = false,
    val isPaused: Boolean = false
)

enum class GameState {
    IDLE, PLAYING, GAME_OVER
}

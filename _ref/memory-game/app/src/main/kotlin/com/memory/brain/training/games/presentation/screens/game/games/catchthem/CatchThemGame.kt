package com.memory.brain.training.games.presentation.screens.game.games.catchthem

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.GameProgression1
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

// Enums and Data Classes
enum class CatchThemPhase {
    SHOW_TARGETS,
    SHUFFLING,
    USER_INPUT,
    SHOW_SOLUTION
}

data class CatchThemUiState(
    val level: Int = 1,
    val gridSize: Int = 3,
    val visibleCells: Set<Int> = emptySet(),
    val targetCells: Set<Int> = emptySet(),
    val clickedCells: Set<Int> = emptySet(),
    val shuffleMapping: Map<Int, Int> = emptyMap(),
    val phase: CatchThemPhase = CatchThemPhase.SHOW_TARGETS,
    val lives: Int = 3,
    val timeRemaining: Long = 60000L,
    val isGameOver: Boolean = false,
    val isPaused: Boolean = false
)

// ViewModel
class CatchThemViewModel : ViewModel() {
    
    private val progression = GameProgression1()
    
    private val _uiState = MutableStateFlow(CatchThemUiState())
    val uiState: StateFlow<CatchThemUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var levelStartTime = 0L
    private val levelDuration = 60000L
    
    private var lives = 3
    
    fun startGame() {
        progression.startGame()
        lives = 3
        startLevel()
    }
    
    private fun startLevel() {
        val gridSize = progression.getCurrentGridSize()
        val winCells = progression.getCurrentWinCells()
        val visibleCells = winCells + 2
        
        val totalCells = gridSize * gridSize
        val allIndices = (0 until totalCells).toMutableList()
        allIndices.shuffle()
        
        val visibleIndices = allIndices.take(visibleCells).toSet()
        val targetIndices = visibleIndices.take(winCells).toSet()
        val shuffleMapping = generateShuffleMapping(totalCells)
        
        _uiState.value = CatchThemUiState(
            level = progression.getLevelNumber(),
            gridSize = gridSize,
            visibleCells = visibleIndices,
            targetCells = targetIndices,
            clickedCells = emptySet(),
            shuffleMapping = shuffleMapping,
            phase = CatchThemPhase.SHOW_TARGETS,
            lives = lives,
            timeRemaining = levelDuration,
            isGameOver = false
        )
        
        viewModelScope.launch {
            delay(2000)
            _uiState.value = _uiState.value.copy(phase = CatchThemPhase.SHUFFLING)
            delay(1200)
            _uiState.value = _uiState.value.copy(phase = CatchThemPhase.USER_INPUT)
            startLevelTimer()
        }
    }
    
    private fun generateShuffleMapping(totalCells: Int): Map<Int, Int> {
        val indices = (0 until totalCells).toMutableList()
        val shuffled = indices.toMutableList()
        shuffled.shuffle()
        return indices.zip(shuffled).toMap()
    }
    
    private fun startLevelTimer() {
        timerJob?.cancel()
        levelStartTime = System.currentTimeMillis()
        
        timerJob = viewModelScope.launch {
            while (true) {
                delay(100)
                val elapsed = System.currentTimeMillis() - levelStartTime
                val remaining = levelDuration - elapsed
                
                if (remaining <= 0) {
                    _uiState.value = _uiState.value.copy(timeRemaining = 0, isGameOver = true)
                    break
                }
                
                _uiState.value = _uiState.value.copy(timeRemaining = remaining)
            }
        }
    }
    
    fun onCellClicked(index: Int) {
        val state = _uiState.value
        
        if (state.phase != CatchThemPhase.USER_INPUT || state.isGameOver) return
        
        val originalIndex = state.shuffleMapping.entries.find { it.value == index }?.key ?: return
        
        if (originalIndex !in state.visibleCells || originalIndex in state.clickedCells) return
        
        val newClickedCells = state.clickedCells + originalIndex
        val isTarget = originalIndex in state.targetCells
        
        if (isTarget) {
            _uiState.value = state.copy(clickedCells = newClickedCells)
            
            if (newClickedCells.containsAll(state.targetCells)) {
                onLevelComplete()
            }
        } else {
            lives--
            
            if (lives <= 0) {
                timerJob?.cancel()
                _uiState.value = state.copy(
                    lives = lives,
                    phase = CatchThemPhase.SHOW_SOLUTION,
                    isGameOver = true
                )
            } else {
                _uiState.value = state.copy(lives = lives, clickedCells = newClickedCells)
            }
        }
    }
    
    private fun onLevelComplete() {
        timerJob?.cancel()
        
        viewModelScope.launch {
            delay(500)
            progression.nextLevel()
            startLevel()
        }
    }
    
    fun pauseGame() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        if (_uiState.value.phase == CatchThemPhase.USER_INPUT && !_uiState.value.isGameOver) {
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

// Composable Screen
@Composable
fun CatchThemGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: CatchThemViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    
    LaunchedEffect(Unit) {
        viewModel.startGame()
    }
    
    LaunchedEffect(uiState.isGameOver) {
        if (uiState.isGameOver) {
            val score = (uiState.level - 1) * 100
            val stars = when {
                uiState.level >= 15 -> 3
                uiState.level >= 8 -> 2
                else -> 1
            }
            onGameComplete(score, stars)
        }
    }
    
    if (uiState.isPaused) {
        PauseDialog(
            gameName = game?.name ?: "",
            onDismiss = { viewModel.resumeGame() },
            onResume = { viewModel.resumeGame() },
            onRestart = { viewModel.restartGame() },
            onExit = onNavigateBack
        )
    }
    
    BaseGameScreen(
        game = game,
        level = uiState.level,
        lives = uiState.lives,
        showProgressBar = true,
        progressBarProgress = (uiState.timeRemaining / 60000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CatchThemGrid(
                gridSize = uiState.gridSize,
                visibleCells = uiState.visibleCells,
                targetCells = uiState.targetCells,
                clickedCells = uiState.clickedCells,
                shuffleMapping = uiState.shuffleMapping,
                phase = uiState.phase,
                onCellClicked = { viewModel.onCellClicked(it) }
            )
        }
    }
}

@Composable
private fun CatchThemGrid(
    gridSize: Int,
    visibleCells: Set<Int>,
    targetCells: Set<Int>,
    clickedCells: Set<Int>,
    shuffleMapping: Map<Int, Int>,
    phase: CatchThemPhase,
    onCellClicked: (Int) -> Unit
) {
    val cellSize = 60.dp
    val cellSpacing = 8.dp
    val gridWidth = (cellSize * gridSize) + (cellSpacing * (gridSize - 1))
    
    Box(
        modifier = Modifier
            .size(gridWidth)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        for (row in 0 until gridSize) {
            for (col in 0 until gridSize) {
                val index = row * gridSize + col
                val originalIndex = shuffleMapping.entries.find { it.value == index }?.key ?: index
                
                if (originalIndex in visibleCells) {
                    CatchThemCell(
                        index = index,
                        originalIndex = originalIndex,
                        gridSize = gridSize,
                        cellSize = cellSize,
                        cellSpacing = cellSpacing,
                        isTarget = originalIndex in targetCells,
                        isClicked = originalIndex in clickedCells,
                        phase = phase,
                        shuffleMapping = shuffleMapping,
                        onClick = { onCellClicked(index) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CatchThemCell(
    index: Int,
    originalIndex: Int,
    gridSize: Int,
    cellSize: androidx.compose.ui.unit.Dp,
    cellSpacing: androidx.compose.ui.unit.Dp,
    isTarget: Boolean,
    isClicked: Boolean,
    phase: CatchThemPhase,
    shuffleMapping: Map<Int, Int>,
    onClick: () -> Unit
) {
    val originalRow = originalIndex / gridSize
    val originalCol = originalIndex % gridSize
    
    val shuffledIndex = shuffleMapping[originalIndex] ?: originalIndex
    val shuffledRow = shuffledIndex / gridSize
    val shuffledCol = shuffledIndex % gridSize
    
    val targetRow = if (phase == CatchThemPhase.SHUFFLING || 
                        phase == CatchThemPhase.USER_INPUT || 
                        phase == CatchThemPhase.SHOW_SOLUTION) {
        shuffledRow
    } else {
        originalRow
    }
    
    val targetCol = if (phase == CatchThemPhase.SHUFFLING || 
                        phase == CatchThemPhase.USER_INPUT || 
                        phase == CatchThemPhase.SHOW_SOLUTION) {
        shuffledCol
    } else {
        originalCol
    }
    
    val animatedRow by animateFloatAsState(
        targetValue = targetRow.toFloat(),
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
        label = "rowAnimation"
    )
    
    val animatedCol by animateFloatAsState(
        targetValue = targetCol.toFloat(),
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
        label = "colAnimation"
    )
    
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 300),
        label = "fadeIn"
    )
    
    val offsetX = (animatedCol * (cellSize.value + cellSpacing.value)).roundToInt()
    val offsetY = (animatedRow * (cellSize.value + cellSpacing.value)).roundToInt()
    
    val backgroundColor = when {
        phase == CatchThemPhase.SHOW_SOLUTION && isTarget && !isClicked -> Color(0xFFFF5252)
        isClicked && isTarget -> Color(0xFF4CAF50)
        isClicked && !isTarget -> Color(0xFFF44336)
        phase == CatchThemPhase.SHOW_TARGETS && isTarget -> Color(0xFF2196F3)
        else -> Color(0xFF90CAF9)
    }
    
    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX, offsetY) }
            .size(cellSize)
            .alpha(alpha)
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .clickable(enabled = phase == CatchThemPhase.USER_INPUT && !isClicked) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        if (isClicked && isTarget) {
            Text(
                text = "✓",
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}

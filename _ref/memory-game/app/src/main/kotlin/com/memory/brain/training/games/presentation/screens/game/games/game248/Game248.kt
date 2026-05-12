package com.memory.brain.training.games.presentation.screens.game.games.game248

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.pow
import kotlin.random.Random

// Data Classes and Enums
data class Cell(
    val x: Int,
    val y: Int,
    val colorType: Int
)

data class Game248UiState(
    val grid: List<List<Int>> = List(6) { List(6) { Random.nextInt(4) } },
    val selectedCells: List<Cell> = emptyList(),
    val currentScore: Int = 0,
    val totalScore: Long = 0,
    val timeRemaining: Long = 60000L,
    val isGameOver: Boolean = false,
    val showScoreAnimation: Boolean = false,
    val isPaused: Boolean = false
)

// ViewModel
class Game248ViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(Game248UiState())
    val uiState: StateFlow<Game248UiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var levelStartTime = 0L
    private val levelDuration = 60000L
    
    fun startGame() {
        _uiState.value = Game248UiState(
            grid = generateGrid(),
            timeRemaining = levelDuration
        )
        startTimer()
    }
    
    private fun generateGrid(): List<List<Int>> {
        return List(6) { List(6) { Random.nextInt(4) } }
    }
    
    private fun startTimer() {
        timerJob?.cancel()
        levelStartTime = System.currentTimeMillis()
        
        timerJob = viewModelScope.launch {
            while (true) {
                delay(100)
                val elapsed = System.currentTimeMillis() - levelStartTime
                val remaining = levelDuration - elapsed
                
                if (remaining <= 0) {
                    _uiState.value = _uiState.value.copy(
                        timeRemaining = 0,
                        isGameOver = true
                    )
                    break
                }
                
                _uiState.value = _uiState.value.copy(timeRemaining = remaining)
            }
        }
    }
    
    fun onCellTouched(x: Int, y: Int) {
        if (x < 1 || x > 4 || y < 1 || y > 4) return
        
        val state = _uiState.value
        val colorType = state.grid[x][y]
        
        if (state.selectedCells.isEmpty()) {
            _uiState.value = state.copy(
                selectedCells = listOf(Cell(x, y, colorType))
            )
        } else {
            val lastCell = state.selectedCells.last()
            
            if (lastCell.x == x && lastCell.y == y) return
            
            if (lastCell.colorType == colorType && isAdjacent(lastCell, x, y)) {
                val cellToAdd = Cell(x, y, colorType)
                
                if (state.selectedCells.contains(cellToAdd)) {
                    val index = state.selectedCells.indexOf(cellToAdd)
                    _uiState.value = state.copy(
                        selectedCells = state.selectedCells.take(index + 1)
                    )
                } else {
                    _uiState.value = state.copy(
                        selectedCells = state.selectedCells + cellToAdd
                    )
                }
            }
        }
    }
    
    fun onTouchReleased() {
        val state = _uiState.value
        
        if (state.selectedCells.size > 1) {
            val score = calculateScore(state.selectedCells.size)
            clearSelectedCells()
            
            _uiState.value = state.copy(
                currentScore = score,
                showScoreAnimation = true
            )
            
            viewModelScope.launch {
                delay(300)
                _uiState.value = _uiState.value.copy(
                    totalScore = _uiState.value.totalScore + score,
                    showScoreAnimation = false,
                    currentScore = 0
                )
            }
        } else {
            _uiState.value = state.copy(selectedCells = emptyList())
        }
    }
    
    private fun clearSelectedCells() {
        val state = _uiState.value
        val newGrid = state.grid.map { it.toMutableList() }.toMutableList()
        
        state.selectedCells.forEach { cell ->
            newGrid[cell.x][cell.y] = Random.nextInt(4)
        }
        
        _uiState.value = state.copy(
            grid = newGrid,
            selectedCells = emptyList()
        )
    }
    
    private fun isAdjacent(cell: Cell, x: Int, y: Int): Boolean {
        val dx = abs(cell.x - x)
        val dy = abs(cell.y - y)
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1)
    }
    
    private fun calculateScore(length: Int): Int {
        return if (length < 10) {
            2.0.pow(length).toInt()
        } else {
            ((length - 9) * 512) + 512
        }
    }
    
    fun moveRowLeft(row: Int) {
        val state = _uiState.value
        val newGrid = state.grid.map { it.toMutableList() }.toMutableList()
        
        val temp = newGrid[1][row]
        for (i in 1 until 5) {
            newGrid[i][row] = newGrid[i + 1][row]
        }
        newGrid[5][row] = temp
        
        _uiState.value = state.copy(grid = newGrid)
    }
    
    fun moveRowRight(row: Int) {
        val state = _uiState.value
        val newGrid = state.grid.map { it.toMutableList() }.toMutableList()
        
        val temp = newGrid[5][row]
        for (i in 5 downTo 2) {
            newGrid[i][row] = newGrid[i - 1][row]
        }
        newGrid[1][row] = temp
        
        _uiState.value = state.copy(grid = newGrid)
    }
    
    fun moveColumnUp(col: Int) {
        val state = _uiState.value
        val newGrid = state.grid.map { it.toMutableList() }.toMutableList()
        
        val temp = newGrid[col][1]
        for (j in 1 until 5) {
            newGrid[col][j] = newGrid[col][j + 1]
        }
        newGrid[col][5] = temp
        
        _uiState.value = state.copy(grid = newGrid)
    }
    
    fun moveColumnDown(col: Int) {
        val state = _uiState.value
        val newGrid = state.grid.map { it.toMutableList() }.toMutableList()
        
        val temp = newGrid[col][5]
        for (j in 5 downTo 2) {
            newGrid[col][j] = newGrid[col][j - 1]
        }
        newGrid[col][1] = temp
        
        _uiState.value = state.copy(grid = newGrid)
    }
    
    fun pauseGame() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        _uiState.value = _uiState.value.copy(isPaused = false)
        if (!_uiState.value.isGameOver) {
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

// Composable Screen
@Composable
fun Game248GameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: Game248ViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    
    LaunchedEffect(Unit) {
        viewModel.startGame()
    }
    
    LaunchedEffect(uiState.isGameOver) {
        if (uiState.isGameOver) {
            val score = (uiState.totalScore / 10).toInt()
            val stars = when {
                uiState.totalScore >= 5000 -> 3
                uiState.totalScore >= 2000 -> 2
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
        level = 1,
        lives = 0,
        showProgressBar = true,
        progressBarProgress = (uiState.timeRemaining / 60000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Score display
            Text(
                text = "Score: ${uiState.totalScore}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
            
            // Current score animation
            if (uiState.showScoreAnimation && uiState.currentScore > 0) {
                val scale by animateFloatAsState(
                    targetValue = 0.5f,
                    animationSpec = tween(300),
                    label = "scoreScale"
                )
                
                Text(
                    text = "+${uiState.currentScore}",
                    fontSize = (32 * (1 - scale * 0.5f)).sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.padding(8.dp)
                )
            }
            
            // Game grid
            Game248Grid(
                grid = uiState.grid,
                selectedCells = uiState.selectedCells,
                onCellTouched = { x, y -> viewModel.onCellTouched(x, y) },
                onTouchReleased = { viewModel.onTouchReleased() },
                onMoveRowLeft = { viewModel.moveRowLeft(it) },
                onMoveRowRight = { viewModel.moveRowRight(it) },
                onMoveColumnUp = { viewModel.moveColumnUp(it) },
                onMoveColumnDown = { viewModel.moveColumnDown(it) }
            )
        }
    }
}

@Composable
private fun Game248Grid(
    grid: List<List<Int>>,
    selectedCells: List<Cell>,
    onCellTouched: (Int, Int) -> Unit,
    onTouchReleased: () -> Unit,
    onMoveRowLeft: (Int) -> Unit,
    onMoveRowRight: (Int) -> Unit,
    onMoveColumnUp: (Int) -> Unit,
    onMoveColumnDown: (Int) -> Unit
) {
    val cellColors = listOf(
        Color(0xFFE57373), // Red
        Color(0xFF64B5F6), // Blue
        Color(0xFF81C784), // Green
        Color(0xFFFFD54F)  // Yellow
    )
    
    val cellSize = 50.dp
    val spacing = 4.dp
    val gridSize = 6
    
    var dragPosition by remember { mutableStateOf<Offset?>(null) }
    
    Box(
        modifier = Modifier
            .size((cellSize + spacing) * gridSize + spacing)
            .background(Color(0xFF37474F))
            .padding(spacing)
    ) {
        // Draw grid and arrows
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            for (y in 0 until gridSize) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    for (x in 0 until gridSize) {
                        when {
                            // Corner cells (empty)
                            (x == 0 && y == 0) || (x == 0 && y == 5) ||
                            (x == 5 && y == 0) || (x == 5 && y == 5) -> {
                                Spacer(modifier = Modifier.size(cellSize))
                            }
                            // Arrow buttons
                            x == 0 -> ArrowButton(
                                icon = Icons.Default.KeyboardArrowLeft,
                                onClick = { onMoveRowLeft(y) },
                                size = cellSize
                            )
                            x == 5 -> ArrowButton(
                                icon = Icons.Default.KeyboardArrowRight,
                                onClick = { onMoveRowRight(y) },
                                size = cellSize
                            )
                            y == 0 -> ArrowButton(
                                icon = Icons.Default.KeyboardArrowUp,
                                onClick = { onMoveColumnUp(x) },
                                size = cellSize
                            )
                            y == 5 -> ArrowButton(
                                icon = Icons.Default.KeyboardArrowDown,
                                onClick = { onMoveColumnDown(x) },
                                size = cellSize
                            )
                            // Game cells
                            else -> {
                                val colorType = grid[x][y]
                                val isSelected = selectedCells.any { it.x == x && it.y == y }
                                
                                Box(
                                    modifier = Modifier
                                        .size(cellSize)
                                        .background(
                                            if (isSelected) Color.White else cellColors[colorType],
                                            shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
        
        // Touch detection overlay
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            val x = ((offset.x - spacing.toPx()) / (cellSize.toPx() + spacing.toPx())).toInt()
                            val y = ((offset.y - spacing.toPx()) / (cellSize.toPx() + spacing.toPx())).toInt()
                            if (x in 1..4 && y in 1..4) {
                                onCellTouched(x, y)
                                dragPosition = offset
                            }
                        },
                        onDrag = { change, _ ->
                            val offset = change.position
                            val x = ((offset.x - spacing.toPx()) / (cellSize.toPx() + spacing.toPx())).toInt()
                            val y = ((offset.y - spacing.toPx()) / (cellSize.toPx() + spacing.toPx())).toInt()
                            if (x in 1..4 && y in 1..4) {
                                onCellTouched(x, y)
                                dragPosition = offset
                            }
                        },
                        onDragEnd = {
                            onTouchReleased()
                            dragPosition = null
                        },
                        onDragCancel = {
                            onTouchReleased()
                            dragPosition = null
                        }
                    )
                }
        ) {
            // Draw connection path
            if (selectedCells.size > 1) {
                val path = Path()
                val cellSizePx = cellSize.toPx()
                val spacingPx = spacing.toPx()
                val colorType = selectedCells.first().colorType
                val pathColor = when (colorType) {
                    0 -> Color(0xFFE57373)
                    1 -> Color(0xFF64B5F6)
                    2 -> Color(0xFF81C784)
                    else -> Color(0xFFFFD54F)
                }
                
                selectedCells.forEachIndexed { index, cell ->
                    val centerX = spacingPx + cell.x * (cellSizePx + spacingPx) + cellSizePx / 2
                    val centerY = spacingPx + cell.y * (cellSizePx + spacingPx) + cellSizePx / 2
                    
                    if (index == 0) {
                        path.moveTo(centerX, centerY)
                    } else {
                        path.lineTo(centerX, centerY)
                    }
                }
                
                drawPath(
                    path = path,
                    color = pathColor,
                    style = Stroke(
                        width = 8.dp.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }
    }
}

@Composable
private fun ArrowButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    size: androidx.compose.ui.unit.Dp
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(size)
            .background(Color(0xFF546E7A), CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(size * 0.6f)
        )
    }
}

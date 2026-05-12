package com.memory.brain.training.games.presentation.screens.game.games.laser

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.GameProgression3
import com.memory.brain.training.games.presentation.screens.game.common.BaseGameScreen
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.random.Random

// Data Classes and Enums
enum class CellType {
    EMPTY,
    BUSY,
    START,
    END,
    MIRROR_SLASH,    // /
    MIRROR_BACKSLASH // \
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

data class Position(val x: Int, val y: Int)

data class MirrorItem(
    val type: CellType,
    val position: Position? = null
)

data class LaserUiState(
    val level: Int = 1,
    val gridSize: Int = 6,
    val grid: List<List<CellType>> = emptyList(),
    val startPos: Position = Position(0, 0),
    val endPos: Position = Position(0, 0),
    val laserPath: List<Position> = emptyList(),
    val availableMirrors: List<MirrorItem> = emptyList(),
    val draggedMirror: MirrorItem? = null,
    val timeRemaining: Long = 80000L,
    val isGameOver: Boolean = false,
    val isWon: Boolean = false,
    val isPaused: Boolean = false
)

// ViewModel
class LaserViewModel : ViewModel() {
    
    private val progression = GameProgression3()
    
    private val _uiState = MutableStateFlow(LaserUiState())
    val uiState: StateFlow<LaserUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var levelStartTime = 0L
    private val levelDuration = 80000L
    
    fun startGame() {
        progression.startGame()
        startLevel()
    }
    
    private fun startLevel() {
        val level = progression.getLevelNumber()
        val gridSize = getGridSize(level)
        val mirrorsCount = getMirrorsCount(level)
        val busyCount = getBusyCount(level)
        
        val (grid, start, end, solution) = generateLevel(gridSize, mirrorsCount, busyCount)
        
        _uiState.value = LaserUiState(
            level = level,
            gridSize = gridSize,
            grid = grid,
            startPos = start,
            endPos = end,
            availableMirrors = solution.map { MirrorItem(it) },
            timeRemaining = levelDuration
        )
        
        updateLaserPath()
        startTimer()
    }
    
    private fun getGridSize(level: Int): Int {
        return when {
            level <= 3 -> 6
            level <= 5 -> 7
            else -> 8
        }
    }
    
    private fun getMirrorsCount(level: Int): Int {
        return when {
            level <= 1 -> 1
            level <= 3 -> 2
            level <= 7 -> 3
            else -> 4
        }
    }
    
    private fun getBusyCount(level: Int): Int {
        return when {
            level == 1 -> 0
            level == 2 -> 1
            level == 3 -> 2
            level <= 5 -> level
            level <= 9 -> level + 2
            else -> 15
        }
    }
    
    private fun generateLevel(
        gridSize: Int,
        mirrorsCount: Int,
        busyCount: Int
    ): Quadruple<List<List<CellType>>, Position, Position, List<CellType>> {
        val grid = MutableList(gridSize) { MutableList(gridSize) { CellType.EMPTY } }
        
        // Generate start position (on edge)
        val startEdge = Random.nextInt(4)
        val start = when (startEdge) {
            0 -> Position(Random.nextInt(1, gridSize - 1), 0) // Top
            1 -> Position(gridSize - 1, Random.nextInt(1, gridSize - 1)) // Right
            2 -> Position(Random.nextInt(1, gridSize - 1), gridSize - 1) // Bottom
            else -> Position(0, Random.nextInt(1, gridSize - 1)) // Left
        }
        
        grid[start.x][start.y] = CellType.START
        
        // Generate end position (on opposite edge)
        val endEdge = (startEdge + 2) % 4
        val end = when (endEdge) {
            0 -> Position(Random.nextInt(1, gridSize - 1), 0)
            1 -> Position(gridSize - 1, Random.nextInt(1, gridSize - 1))
            2 -> Position(Random.nextInt(1, gridSize - 1), gridSize - 1)
            else -> Position(0, Random.nextInt(1, gridSize - 1))
        }
        
        grid[end.x][end.y] = CellType.END
        
        // Generate solution mirrors
        val solution = mutableListOf<CellType>()
        repeat(mirrorsCount) {
            solution.add(if (Random.nextBoolean()) CellType.MIRROR_SLASH else CellType.MIRROR_BACKSLASH)
        }
        
        // Add busy cells
        val busyPositions = mutableSetOf<Position>()
        while (busyPositions.size < busyCount) {
            val x = Random.nextInt(1, gridSize - 1)
            val y = Random.nextInt(1, gridSize - 1)
            val pos = Position(x, y)
            if (pos != start && pos != end && pos !in busyPositions) {
                busyPositions.add(pos)
                grid[x][y] = CellType.BUSY
            }
        }
        
        return Quadruple(grid, start, end, solution)
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
    
    fun onMirrorDragStart(mirror: MirrorItem) {
        _uiState.value = _uiState.value.copy(draggedMirror = mirror)
    }
    
    fun onMirrorDrop(x: Int, y: Int) {
        val state = _uiState.value
        val mirror = state.draggedMirror ?: return
        
        if (x < 1 || x >= state.gridSize - 1 || y < 1 || y >= state.gridSize - 1) {
            _uiState.value = state.copy(draggedMirror = null)
            return
        }
        
        if (state.grid[x][y] != CellType.EMPTY) {
            _uiState.value = state.copy(draggedMirror = null)
            return
        }
        
        val newGrid = state.grid.map { it.toMutableList() }.toMutableList()
        
        // Remove mirror from old position if it was placed
        mirror.position?.let { oldPos ->
            if (oldPos.x in newGrid.indices && oldPos.y in newGrid[0].indices) {
                newGrid[oldPos.x][oldPos.y] = CellType.EMPTY
            }
        }
        
        // Place mirror at new position
        newGrid[x][y] = mirror.type
        
        // Update available mirrors
        val newMirrors = state.availableMirrors.map {
            if (it == mirror) it.copy(position = Position(x, y)) else it
        }
        
        _uiState.value = state.copy(
            grid = newGrid,
            availableMirrors = newMirrors,
            draggedMirror = null
        )
        
        updateLaserPath()
    }
    
    private fun updateLaserPath() {
        val state = _uiState.value
        val path = mutableListOf<Position>()
        var currentPos = state.startPos
        var direction = getInitialDirection(state.startPos, state.gridSize)
        
        path.add(currentPos)
        
        var steps = 0
        val maxSteps = state.gridSize * state.gridSize * 2
        
        while (steps < maxSteps) {
            val nextPos = getNextPosition(currentPos, direction)
            
            if (!isValidPosition(nextPos, state.gridSize)) break
            
            path.add(nextPos)
            currentPos = nextPos
            
            if (currentPos == state.endPos) {
                _uiState.value = state.copy(
                    laserPath = path,
                    isWon = true,
                    isGameOver = true
                )
                timerJob?.cancel()
                return
            }
            
            val cellType = state.grid[currentPos.x][currentPos.y]
            
            when (cellType) {
                CellType.MIRROR_SLASH -> {
                    direction = reflectSlash(direction)
                }
                CellType.MIRROR_BACKSLASH -> {
                    direction = reflectBackslash(direction)
                }
                CellType.BUSY -> break
                else -> {}
            }
            
            steps++
        }
        
        _uiState.value = state.copy(laserPath = path, isWon = false)
    }
    
    private fun getInitialDirection(start: Position, gridSize: Int): Direction {
        return when {
            start.y == 0 -> Direction.DOWN
            start.y == gridSize - 1 -> Direction.UP
            start.x == 0 -> Direction.RIGHT
            else -> Direction.LEFT
        }
    }
    
    private fun getNextPosition(pos: Position, direction: Direction): Position {
        return when (direction) {
            Direction.UP -> Position(pos.x, pos.y - 1)
            Direction.DOWN -> Position(pos.x, pos.y + 1)
            Direction.LEFT -> Position(pos.x - 1, pos.y)
            Direction.RIGHT -> Position(pos.x + 1, pos.y)
        }
    }
    
    private fun isValidPosition(pos: Position, gridSize: Int): Boolean {
        return pos.x in 0 until gridSize && pos.y in 0 until gridSize
    }
    
    private fun reflectSlash(direction: Direction): Direction {
        return when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.DOWN
            Direction.RIGHT -> Direction.UP
        }
    }
    
    private fun reflectBackslash(direction: Direction): Direction {
        return when (direction) {
            Direction.UP -> Direction.LEFT
            Direction.DOWN -> Direction.RIGHT
            Direction.LEFT -> Direction.UP
            Direction.RIGHT -> Direction.DOWN
        }
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
    
    fun nextLevel() {
        progression.nextLevel()
        startLevel()
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}

data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

// Composable Screen
@Composable
fun LaserGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: LaserViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    
    LaunchedEffect(Unit) {
        viewModel.startGame()
    }
    
    LaunchedEffect(uiState.isWon) {
        if (uiState.isWon) {
            delay(1000)
            viewModel.nextLevel()
        }
    }
    
    LaunchedEffect(uiState.isGameOver) {
        if (uiState.isGameOver && !uiState.isWon) {
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
        lives = 0,
        showProgressBar = true,
        progressBarProgress = (uiState.timeRemaining / 80000f).coerceIn(0f, 1f),
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Available mirrors
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                uiState.availableMirrors.forEach { mirror ->
                    if (mirror.position == null) {
                        MirrorButton(
                            mirror = mirror,
                            onDragStart = { viewModel.onMirrorDragStart(mirror) }
                        )
                    }
                }
            }
            
            // Game grid
            LaserGrid(
                gridSize = uiState.gridSize,
                grid = uiState.grid,
                startPos = uiState.startPos,
                endPos = uiState.endPos,
                laserPath = uiState.laserPath,
                onMirrorDrop = { x, y -> viewModel.onMirrorDrop(x, y) }
            )
        }
    }
}

@Composable
private fun MirrorButton(
    mirror: MirrorItem,
    onDragStart: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(Color(0xFF03A9F4), RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { onDragStart() },
                    onDrag = { _, _ -> },
                    onDragEnd = { }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (mirror.type == CellType.MIRROR_SLASH) "/" else "\\",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun LaserGrid(
    gridSize: Int,
    grid: List<List<CellType>>,
    startPos: Position,
    endPos: Position,
    laserPath: List<Position>,
    onMirrorDrop: (Int, Int) -> Unit
) {
    val cellSize = 50.dp
    val spacing = 4.dp
    
    var dropPosition by remember { mutableStateOf<Offset?>(null) }
    
    Box(
        modifier = Modifier
            .size((cellSize + spacing) * gridSize + spacing)
            .background(Color(0xFF263238))
            .padding(spacing)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        dropPosition?.let { offset ->
                            val x = (offset.x / (cellSize.toPx() + spacing.toPx())).toInt()
                            val y = (offset.y / (cellSize.toPx() + spacing.toPx())).toInt()
                            onMirrorDrop(x, y)
                        }
                        dropPosition = null
                    },
                    onDrag = { change, _ ->
                        dropPosition = change.position
                    }
                )
            }
    ) {
        // Draw grid cells
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
                        val cellType = grid[x][y]
                        val pos = Position(x, y)
                        
                        Box(
                            modifier = Modifier
                                .size(cellSize)
                                .background(
                                    when (cellType) {
                                        CellType.START -> Color(0xFF4CAF50)
                                        CellType.END -> Color(0xFFF44336)
                                        CellType.BUSY -> Color(0xFF757575)
                                        CellType.MIRROR_SLASH, CellType.MIRROR_BACKSLASH -> Color(0xFF03A9F4)
                                        else -> Color(0xFF37474F)
                                    },
                                    RoundedCornerShape(4.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            when (cellType) {
                                CellType.MIRROR_SLASH -> Text("/", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
                                CellType.MIRROR_BACKSLASH -> Text("\\", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
                                CellType.START -> Text("S", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
                                CellType.END -> Text("E", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
                                else -> {}
                            }
                        }
                    }
                }
            }
        }
        
        // Draw laser path
        Canvas(modifier = Modifier.fillMaxSize()) {
            if (laserPath.size > 1) {
                val path = Path()
                val cellSizePx = cellSize.toPx()
                val spacingPx = spacing.toPx()
                
                laserPath.forEachIndexed { index, pos ->
                    val centerX = pos.x * (cellSizePx + spacingPx) + cellSizePx / 2
                    val centerY = pos.y * (cellSizePx + spacingPx) + cellSizePx / 2
                    
                    if (index == 0) {
                        path.moveTo(centerX, centerY)
                    } else {
                        path.lineTo(centerX, centerY)
                    }
                }
                
                drawPath(
                    path = path,
                    color = Color(0xFFFF5252),
                    style = Stroke(
                        width = 6.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
        }
    }
}

package com.memory.brain.training.games.presentation.screens.game.games.reactiontest

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.data.GameDataProvider
import com.memory.brain.training.games.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ReactionTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val gameId: Int = savedStateHandle.get<Int>("gameId") ?: 9
    
    private val _uiState = MutableStateFlow(ReactionTestUiState())
    val uiState: StateFlow<ReactionTestUiState> = _uiState.asStateFlow()
    
    private var gameJob: Job? = null
    
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
        _uiState.update { 
            it.copy(
                gameState = ReactionTestGameState.WAITING,
                score = 0,
                round = 1,
                totalRounds = 10,
                reactionTimes = emptyList()
            )
        }
        startRound()
    }
    
    private fun startRound() {
        gameJob?.cancel()
        
        _uiState.update { it.copy(gameState = ReactionTestGameState.WAITING) }
        
        gameJob = viewModelScope.launch {
            // 随机等待2-5秒
            val waitTime = Random.nextLong(2000, 5000)
            delay(waitTime)
            
            _uiState.update { 
                it.copy(
                    gameState = ReactionTestGameState.READY,
                    roundStartTime = System.currentTimeMillis()
                )
            }
        }
    }
    
    fun onReact() {
        when (_uiState.value.gameState) {
            ReactionTestGameState.WAITING -> {
                // 太早点击了
                _uiState.update { it.copy(gameState = ReactionTestGameState.TOO_EARLY) }
                gameJob?.cancel()
                viewModelScope.launch {
                    delay(1000)
                    if (_uiState.value.round < _uiState.value.totalRounds) {
                        _uiState.update { it.copy(round = it.round + 1) }
                        startRound()
                    } else {
                        endGame()
                    }
                }
            }
            ReactionTestGameState.READY -> {
                // 计算反应时间
                val reactionTime = System.currentTimeMillis() - _uiState.value.roundStartTime
                val newReactionTimes = _uiState.value.reactionTimes + reactionTime
                val avgReactionTime = newReactionTimes.average().toInt()
                val score = maxOf(0, 1000 - avgReactionTime)
                
                _uiState.update { 
                    it.copy(
                        reactionTimes = newReactionTimes,
                        score = score,
                        gameState = ReactionTestGameState.RESULT
                    )
                }
                
                viewModelScope.launch {
                    delay(1000)
                    if (_uiState.value.round < _uiState.value.totalRounds) {
                        _uiState.update { it.copy(round = it.round + 1) }
                        startRound()
                    } else {
                        endGame()
                    }
                }
            }
            else -> {}
        }
    }
    
    private fun endGame() {
        gameJob?.cancel()
        _uiState.update { it.copy(gameState = ReactionTestGameState.GAME_OVER) }
    }
    
    fun calculateStars(): Int {
        val avgTime = if (_uiState.value.reactionTimes.isNotEmpty()) {
            _uiState.value.reactionTimes.average().toInt()
        } else {
            1000
        }
        return when {
            avgTime < 300 -> 3
            avgTime < 500 -> 2
            avgTime < 700 -> 1
            else -> 0
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        gameJob?.cancel()
    }
}

data class ReactionTestUiState(
    val game: Game? = null,
    val gameState: ReactionTestGameState = ReactionTestGameState.LOADING,
    val score: Int = 0,
    val round: Int = 1,
    val totalRounds: Int = 10,
    val reactionTimes: List<Long> = emptyList(),
    val roundStartTime: Long = 0L
)

enum class ReactionTestGameState {
    LOADING,
    WAITING,
    READY,
    TOO_EARLY,
    RESULT,
    GAME_OVER
}

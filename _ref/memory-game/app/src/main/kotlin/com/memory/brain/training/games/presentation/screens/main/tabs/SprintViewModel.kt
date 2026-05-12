package com.memory.brain.training.games.presentation.screens.main.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.domain.model.GameInfo
import com.memory.brain.training.games.domain.usecase.game.GetAllGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SprintViewModel @Inject constructor(
    private val getAllGamesUseCase: GetAllGamesUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SprintUiState())
    val uiState: StateFlow<SprintUiState> = _uiState.asStateFlow()
    
    init {
        loadGames()
    }
    
    private fun loadGames() {
        viewModelScope.launch {
            getAllGamesUseCase()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { games ->
                    _uiState.update { it.copy(games = games, isLoading = false) }
                }
        }
    }
    
    fun onGameClick(game: GameInfo) {
        // Navigate to game screen
    }
}

data class SprintUiState(
    val games: List<GameInfo> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

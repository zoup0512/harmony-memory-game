package com.memory.brain.training.games.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memory.brain.training.games.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    
    init {
        observeUserData()
    }
    
    private fun observeUserData() {
        viewModelScope.launch {
            combine(
                userRepository.getCoinsBalance(),
                userRepository.getStarsBalance()
            ) { coins, stars ->
                _uiState.update { it.copy(coins = coins, stars = stars) }
            }.collect()
        }
    }
    
    fun addStarsAndCoins(stars: Int) {
        viewModelScope.launch {
            userRepository.addStars(stars)
            userRepository.addCoins(stars * 10, com.memory.brain.training.games.domain.repository.CoinTransactionType.WIN_GAME)
        }
    }
}

data class MainUiState(
    val coins: Int = 0,
    val stars: Int = 0,
    val isLoading: Boolean = false
)

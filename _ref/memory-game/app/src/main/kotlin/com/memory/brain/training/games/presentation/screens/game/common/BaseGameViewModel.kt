package com.memory.brain.training.games.presentation.screens.game.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 游戏ViewModel基类，提供通用的游戏逻辑
 */
abstract class BaseGameViewModel<T : BaseGameUiState> : ViewModel() {
    
    protected abstract val _uiState: MutableStateFlow<T>
    abstract val uiState: StateFlow<T>
    
    protected var countdownJob: Job? = null
    
    companion object {
        const val MAX_LEVELS = 10
        const val INITIAL_LIVES = 3
        const val INITIAL_TIME = 60
    }
    
    /**
     * 开始倒计时
     */
    protected fun startCountdown(
        onTimeUp: () -> Unit,
        isGameOver: () -> Boolean,
        updateTime: (Int) -> Unit,
        getCurrentTime: () -> Int
    ) {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (getCurrentTime() > 0 && !isGameOver()) {
                delay(1000)
                val newTime = getCurrentTime() - 1
                updateTime(newTime)
                
                if (newTime <= 0) {
                    onTimeUp()
                }
            }
        }
    }
    
    /**
     * 计算星星数量
     */
    fun calculateStars(score: Int): Int {
        return when {
            score >= 1000 -> 3
            score >= 500 -> 2
            score >= 200 -> 1
            else -> 0
        }
    }
    
    /**
     * 取消所有协程
     */
    override fun onCleared() {
        super.onCleared()
        countdownJob?.cancel()
    }
}

/**
 * 游戏UI状态基类
 */
interface BaseGameUiState {
    val score: Int
    val level: Int
    val lives: Int
    val timeRemaining: Int
}

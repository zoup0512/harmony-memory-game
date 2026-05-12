package com.memory.brain.training.games.domain.model

import kotlin.math.min
import kotlin.math.sqrt

/**
 * Base class for game progression logic
 * Manages level difficulty progression
 */
abstract class GameProgression {
    protected var currentLevel: Int = 1
    
    abstract fun startGame()
    abstract fun nextLevel()
    
    fun getLevelNumber(): Int = currentLevel
}

/**
 * GameProgression1 - For Memory Grid (Game1)
 * Grid size and win cells increase with level
 */
open class GameProgression1 : GameProgression() {
    private var gridSize: Int = 3
    private var winCells: Int = 3
    
    override fun startGame() {
        currentLevel = 1
        calculateGridParams()
    }
    
    override fun nextLevel() {
        currentLevel++
        calculateGridParams()
    }
    
    fun getCurrentGridSize(): Int = gridSize
    
    fun getCurrentWinCells(): Int = winCells
    
    private fun calculateGridParams() {
        // Grid size calculation based on level
        gridSize = when {
            currentLevel <= 2 -> 3
            currentLevel <= 5 -> 4
            currentLevel <= 10 -> 5
            currentLevel <= 15 -> 6
            currentLevel <= 20 -> 7
            else -> 8
        }
        
        // Win cells calculation
        winCells = when {
            currentLevel == 1 -> 3
            currentLevel == 2 -> 4
            currentLevel == 3 -> 5
            currentLevel == 4 -> 6
            currentLevel == 5 -> 7
            currentLevel == 6 -> 8
            currentLevel == 7 -> 9
            currentLevel == 8 -> 10
            currentLevel == 9 -> 11
            currentLevel == 10 -> 12
            currentLevel <= 15 -> 13 + (currentLevel - 10)
            currentLevel <= 20 -> 18 + (currentLevel - 15)
            else -> 23 + (currentLevel - 20)
        }
        
        // Ensure win cells don't exceed grid capacity
        val maxCells = gridSize * gridSize
        winCells = min(winCells, maxCells - 1)
    }
}

/**
 * GameProgression2 - For Rotating Grid (Game2)
 * Similar to GameProgression1
 */
class GameProgression2 : GameProgression1()

/**
 * GameProgression3 - For One and Only (Game10), Colors (Game20)
 * Element count increases with level
 */
class GameProgression3 : GameProgression() {
    override fun startGame() {
        currentLevel = 1
    }
    
    override fun nextLevel() {
        currentLevel++
    }
    
    /**
     * Get number of elements for current level
     * Used by One and Only game
     */
    fun getElementCount(): Int {
        return when {
            currentLevel <= 2 -> currentLevel + 2  // 3-4 elements
            currentLevel <= 5 -> currentLevel + 3  // 5-8 elements
            currentLevel <= 10 -> currentLevel + 4 // 9-14 elements
            currentLevel <= 20 -> currentLevel + 5 // 15-25 elements
            else -> min(currentLevel + 6, 50)     // 26-50 elements max
        }
    }
}

/**
 * GameProgression12 - For Correctly (Game11)
 * Equation complexity increases with level
 */
class GameProgression12 : GameProgression() {
    override fun startGame() {
        currentLevel = 1
    }
    
    override fun nextLevel() {
        currentLevel++
    }
    
    /**
     * Get equation type for current level
     * Returns equation complexity level (1-7)
     */
    fun getEquationType(): Int {
        return when {
            currentLevel <= 3 -> 1   // Simple addition/subtraction
            currentLevel <= 5 -> 2   // Larger numbers
            currentLevel <= 7 -> 3   // Multiplication
            currentLevel <= 9 -> 4   // Three operations
            currentLevel <= 11 -> 5  // Larger three operations
            currentLevel <= 15 -> 6  // Multiplication + addition
            currentLevel <= 20 -> 7  // Complex: mult + mult + add
            else -> 8               // Very complex
        }
    }
    
    /**
     * Get time limit for current level (milliseconds)
     */
    fun getTimeLimit(): Long {
        return if (currentLevel <= 10) 4000L else 8000L
    }
}

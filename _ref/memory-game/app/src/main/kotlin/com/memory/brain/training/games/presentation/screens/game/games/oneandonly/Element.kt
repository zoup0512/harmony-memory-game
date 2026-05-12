package com.memory.brain.training.games.presentation.screens.game.games.oneandonly

import androidx.compose.ui.graphics.Color

/**
 * Element for One and Only game
 * Each element has a color and a shape
 */
data class Element(
    val color: Int,
    val shape: Int,
    val isWin: Boolean = false
) {
    companion object {
        // Shape types (matching old project)
        const val SHAPE_CIRCLE = 0
        const val SHAPE_SQUARE = 1
        const val SHAPE_TRIANGLE = 2
        const val SHAPE_DIAMOND = 3
        
        // Color indices (will map to actual colors)
        const val COLOR_RED = 0
        const val COLOR_BLUE = 1
        const val COLOR_GREEN = 2
        const val COLOR_YELLOW = 3
        const val COLOR_PURPLE = 4
        const val COLOR_ORANGE = 5
    }
    
    /**
     * Get the actual Color for this element
     */
    fun getColor(): Color {
        return when (color) {
            COLOR_RED -> Color(0xFFE53935)      // Red
            COLOR_BLUE -> Color(0xFF1E88E5)     // Blue
            COLOR_GREEN -> Color(0xFF43A047)    // Green
            COLOR_YELLOW -> Color(0xFFFDD835)   // Yellow
            COLOR_PURPLE -> Color(0xFF8E24AA)   // Purple
            COLOR_ORANGE -> Color(0xFFFB8C00)   // Orange
            else -> Color.Gray
        }
    }
    
    /**
     * Get shape name for debugging
     */
    fun getShapeName(): String {
        return when (shape) {
            SHAPE_CIRCLE -> "Circle"
            SHAPE_SQUARE -> "Square"
            SHAPE_TRIANGLE -> "Triangle"
            SHAPE_DIAMOND -> "Diamond"
            else -> "Unknown"
        }
    }
    
    /**
     * Get color name for debugging
     */
    fun getColorName(): String {
        return when (color) {
            COLOR_RED -> "Red"
            COLOR_BLUE -> "Blue"
            COLOR_GREEN -> "Green"
            COLOR_YELLOW -> "Yellow"
            COLOR_PURPLE -> "Purple"
            COLOR_ORANGE -> "Orange"
            else -> "Unknown"
        }
    }
}

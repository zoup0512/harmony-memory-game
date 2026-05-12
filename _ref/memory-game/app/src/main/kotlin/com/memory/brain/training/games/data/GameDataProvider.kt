package com.memory.brain.training.games.data

import com.memory.brain.training.games.BuildConfig
import com.memory.brain.training.games.domain.model.Game
import com.memory.brain.training.games.domain.model.GameCategory

/**
 * 提供所有游戏的数据 - 完整的23个游戏
 * 
 * Debug模式下所有游戏自动解锁
 */
object GameDataProvider {
    
    fun getAllGames(): List<Game> {
        val games = listOf(
            // Memory Games (记忆类) - 6个游戏
            Game(
                id = 1,
                name = "记忆网格",
                description = "记住网格中的位置",
                category = GameCategory.MEMORY,
                iconResId = 0,
                isLocked = false,
                requiredStars = 0
            ),
        Game(
            id = 2,
            name = "六边形",
            description = "六边形网格记忆游戏",
            category = GameCategory.MEMORY,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 3,
            name = "谁是新的",
            description = "识别新出现的元素",
            category = GameCategory.MEMORY,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 5,
            name = "跟随路径",
            description = "记住并跟随路径",
            category = GameCategory.MEMORY,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 6,
            name = "图像漩涡",
            description = "图像记忆游戏",
            category = GameCategory.MEMORY,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 8,
            name = "找图片",
            description = "找到之前看过的图片",
            category = GameCategory.MEMORY,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        
        // Attention Games (注意力类) - 2个游戏
        Game(
            id = 4,
            name = "旋转网格",
            description = "记住网格位置，然后旋转",
            category = GameCategory.ATTENTION,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 7,
            name = "抓住它们",
            description = "快速抓住目标",
            category = GameCategory.ATTENTION,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        
        // Speed Games (速度类) - 5个游戏
        Game(
            id = 24,
            name = "唯一的",
            description = "找出唯一不同的元素",
            category = GameCategory.SPEED,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 12,
            name = "全部相同",
            description = "判断所有元素是否相同",
            category = GameCategory.SPEED,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 13,
            name = "排序数字",
            description = "快速排序数字",
            category = GameCategory.SPEED,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 18,
            name = "找到全部",
            description = "找到所有目标",
            category = GameCategory.SPEED,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 22,
            name = "舒尔特表",
            description = "按顺序点击数字",
            category = GameCategory.SPEED,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        
        // Problem Solving Games (问题解决类) - 5个游戏
        Game(
            id = 25,
            name = "正确吗",
            description = "判断数学等式是否正确",
            category = GameCategory.PROBLEM_SOLVING,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 11,
            name = "更多更少",
            description = "比较数字大小",
            category = GameCategory.PROBLEM_SOLVING,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 16,
            name = "248",
            description = "2048类游戏",
            category = GameCategory.PROBLEM_SOLVING,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 20,
            name = "对称",
            description = "判断对称性",
            category = GameCategory.PROBLEM_SOLVING,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 21,
            name = "激光",
            description = "激光反射游戏",
            category = GameCategory.PROBLEM_SOLVING,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        
        // Flexibility Games (灵活性类) - 3个游戏
        Game(
            id = 26,
            name = "颜色",
            description = "判断颜色词与文字颜色是否匹配",
            category = GameCategory.FLEXIBILITY,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 14,
            name = "纸飞机",
            description = "纸飞机方向游戏",
            category = GameCategory.FLEXIBILITY,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 15,
            name = "像之前的吗",
            description = "判断是否与之前相同",
            category = GameCategory.FLEXIBILITY,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        
        // Imagination Games (想象力类) - 2个游戏
        Game(
            id = 19,
            name = "数一数",
            description = "数数游戏",
            category = GameCategory.IMAGINATION,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        ),
        Game(
            id = 17,
            name = "金字塔",
            description = "金字塔游戏",
            category = GameCategory.IMAGINATION,
            iconResId = 0,
            isLocked = false,
            requiredStars = 0
        )
        )
        
        // Debug模式下解锁所有游戏
        return if (BuildConfig.DEBUG_UNLOCK_ALL_GAMES) {
            games.map { it.copy(isLocked = false) }
        } else {
            games
        }
    }
    
    fun getGamesByCategory(category: GameCategory): List<Game> {
        return getAllGames().filter { it.category == category }
    }
    
    fun getUnlockedGames(): List<Game> {
        return getAllGames().filter { !it.isLocked }
    }
}

package com.memory.brain.training.games.presentation.screens.game

import androidx.compose.runtime.Composable
import com.memory.brain.training.games.presentation.screens.game.games.colormemory.ColorMemoryGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.colormatch.ColorMatchGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.colors.ColorsGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.correctly.CorrectlyGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.finddifference.FindDifferenceGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.mathpuzzle.MathPuzzleGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.memorygrid.MemoryGridGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.multitask.MultiTaskGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.numbermemory.NumberMemoryGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.oneandonly.OneAndOnlyGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.quickclick.QuickClickGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.reactiontest.ReactionTestGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.rotatinggrid.RotatingGridGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.rotationvision.RotationVisionGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.spatialvision.SpatialVisionGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.taskswitch.TaskSwitchGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.logicreasoning.LogicReasoningGameScreen
import com.memory.brain.training.games.presentation.screens.game.games.placeholder.PlaceholderGameScreen

/**
 * 游戏工厂 - 根据游戏ID创建对应的游戏界面
 */
@Composable
fun GameFactory(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit
) {
    when (gameId) {
        1 -> MemoryGridGameScreen(gameId, onNavigateBack, onGameComplete)
        2 -> com.memory.brain.training.games.presentation.screens.game.games.hexagons.HexagonsGameScreen(gameId, onNavigateBack, onGameComplete)
        3 -> com.memory.brain.training.games.presentation.screens.game.games.whosnew.WhosNewGameScreen(gameId, onNavigateBack, onGameComplete)
        4 -> RotatingGridGameScreen(gameId, onNavigateBack, onGameComplete)
        5 -> com.memory.brain.training.games.presentation.screens.game.games.followthepath.FollowThePathGameScreen(gameId, onNavigateBack, onGameComplete)
        6 -> com.memory.brain.training.games.presentation.screens.game.games.imagevortex.ImageVortexGameScreen(gameId, onNavigateBack, onGameComplete)
        7 -> com.memory.brain.training.games.presentation.screens.game.games.catchthem.CatchThemGameScreen(gameId, onNavigateBack, onGameComplete)
        8 -> com.memory.brain.training.games.presentation.screens.game.games.findthepicture.FindThePictureGameScreen(gameId, onNavigateBack, onGameComplete)
        9 -> PlaceholderGameScreen(gameId, onNavigateBack, onGameComplete) // (unused)
        10 -> PlaceholderGameScreen(gameId, onNavigateBack, onGameComplete) // (unused)
        11 -> com.memory.brain.training.games.presentation.screens.game.games.moreless.MoreLessGameScreen(gameId, onNavigateBack, onGameComplete)
        12 -> com.memory.brain.training.games.presentation.screens.game.games.allthesame.AllTheSameGameScreen(gameId, onNavigateBack, onGameComplete)
        13 -> com.memory.brain.training.games.presentation.screens.game.games.sortthedigits.SortTheDigitsGameScreen(gameId, onNavigateBack, onGameComplete)
        14 -> com.memory.brain.training.games.presentation.screens.game.games.paperplanes.PaperPlanesGameScreen(gameId, onNavigateBack, onGameComplete)
        15 -> com.memory.brain.training.games.presentation.screens.game.games.likeprevious.LikePreviousGameScreen(gameId, onNavigateBack, onGameComplete)
        16 -> com.memory.brain.training.games.presentation.screens.game.games.game248.Game248GameScreen(gameId, onNavigateBack, onGameComplete)
        17 -> com.memory.brain.training.games.presentation.screens.game.games.pyramids.PyramidsGameScreen(gameId, onNavigateBack, onGameComplete)
        18 -> com.memory.brain.training.games.presentation.screens.game.games.findall.FindAllGameScreen(gameId, onNavigateBack, onGameComplete)
        19 -> com.memory.brain.training.games.presentation.screens.game.games.countemall.CountEmAllGameScreen(gameId, onNavigateBack, onGameComplete)
        20 -> com.memory.brain.training.games.presentation.screens.game.games.symmetry.SymmetryGameScreen(gameId, onNavigateBack, onGameComplete)
        21 -> com.memory.brain.training.games.presentation.screens.game.games.laser.LaserGameScreen(gameId, onNavigateBack, onGameComplete)
        22 -> com.memory.brain.training.games.presentation.screens.game.games.schultztables.SchultzTablesGameScreen(gameId, onNavigateBack, onGameComplete)
        23 -> PlaceholderGameScreen(gameId, onNavigateBack, onGameComplete) // (unused)
        24 -> OneAndOnlyGameScreen(gameId, onNavigateBack, onGameComplete)
        25 -> CorrectlyGameScreen(gameId, onNavigateBack, onGameComplete)
        26 -> ColorsGameScreen(gameId, onNavigateBack, onGameComplete)
        else -> PlaceholderGameScreen(gameId, onNavigateBack, onGameComplete)
    }
}

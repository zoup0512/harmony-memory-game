package com.memory.brain.training.games.domain.usecase.game

import com.memory.brain.training.games.domain.model.GameInfo
import com.memory.brain.training.games.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to get all available games
 */
class GetAllGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(): Flow<List<GameInfo>> {
        return gameRepository.getAllGames()
    }
}

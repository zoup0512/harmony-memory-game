package com.memory.brain.training.games.domain.usecase.game

import com.memory.brain.training.games.domain.model.GameSession
import com.memory.brain.training.games.domain.repository.GameRepository
import com.memory.brain.training.games.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Use case to save a game session and update user stats
 */
class SaveGameSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(session: GameSession) {
        // Save the game session
        gameRepository.saveGameSession(session)
        
        // Update user coins and stars
        if (session.isCompleted) {
            userRepository.addCoins(
                session.coinsEarned,
                com.memory.brain.training.games.domain.repository.CoinTransactionType.WIN_GAME
            )
            userRepository.addStars(session.stars)
        }
    }
}

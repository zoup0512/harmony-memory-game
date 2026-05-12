package com.memory.brain.training.games.domain.usecase.online

import com.memory.brain.training.games.domain.repository.OnlineRepository
import javax.inject.Inject

/**
 * Use case to start searching for an online match
 */
class StartOnlineMatchUseCase @Inject constructor(
    private val onlineRepository: OnlineRepository
) {
    suspend operator fun invoke(gameId: String): Result<String> {
        return onlineRepository.searchForMatch(gameId)
    }
}

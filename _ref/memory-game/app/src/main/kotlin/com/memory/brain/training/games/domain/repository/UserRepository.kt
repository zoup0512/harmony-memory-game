package com.memory.brain.training.games.domain.repository

import com.memory.brain.training.games.domain.model.User
import com.memory.brain.training.games.domain.model.UserStats
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for user-related operations
 */
interface UserRepository {
    
    // User Profile
    fun getCurrentUser(): Flow<User?>
    suspend fun updateUser(user: User)
    suspend fun saveUser(user: User)
    
    // Authentication
    suspend fun loginWithFacebook(token: String): Result<User>
    suspend fun loginAnonymously(): Result<User>
    suspend fun logout()
    fun isLoggedIn(): Flow<Boolean>
    
    // Coins
    suspend fun addCoins(amount: Int, transactionType: CoinTransactionType)
    suspend fun spendCoins(amount: Int, transactionType: CoinTransactionType): Boolean
    fun getCoinsBalance(): Flow<Int>
    
    // Stars
    suspend fun addStars(amount: Int)
    fun getStarsBalance(): Flow<Int>
    
    // User Stats
    fun getUserStats(): Flow<UserStats>
    suspend fun updateUserStats(stats: UserStats)
    
    // Purchases
    suspend fun setPro(isPro: Boolean)
    suspend fun setAdsRemoved(removed: Boolean)
    suspend fun setUnlimitedOnline(unlimited: Boolean)
    suspend fun setSecretGameUnlocked(unlocked: Boolean)
    suspend fun setAllGamesUnlocked(unlocked: Boolean)
    
    // Preferences
    suspend fun setNickname(nickname: String)
    suspend fun setPhotoUrl(url: String)
    suspend fun setSoundEnabled(enabled: Boolean)
    fun isSoundEnabled(): Flow<Boolean>
}

/**
 * Types of coin transactions
 */
enum class CoinTransactionType {
    WIN_GAME,
    WIN_ONLINE,
    DRAW_ONLINE,
    WATCH_AD,
    FACEBOOK_SHARE,
    PURCHASE,
    UNLOCK_GAME,
    REPLAY_GAME,
    DAILY_BONUS
}

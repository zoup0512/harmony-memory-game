package com.memory.brain.training.games.data.repository

import com.memory.brain.training.games.data.local.database.dao.CoinTransactionDao
import com.memory.brain.training.games.data.local.database.dao.UserDao
import com.memory.brain.training.games.data.local.database.entity.CoinTransactionEntity
import com.memory.brain.training.games.data.mapper.toEntity
import com.memory.brain.training.games.data.mapper.toModel
import com.memory.brain.training.games.domain.model.User
import com.memory.brain.training.games.domain.model.UserStats
import com.memory.brain.training.games.domain.repository.CoinTransactionType
import com.memory.brain.training.games.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val coinTransactionDao: CoinTransactionDao
) : UserRepository {
    
    override fun getCurrentUser(): Flow<User?> {
        return userDao.getCurrentUser().map { it?.toModel() }
    }
    
    override suspend fun updateUser(user: User) {
        userDao.updateUser(user.toEntity())
    }
    
    override suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }
    
    override suspend fun loginWithFacebook(token: String): Result<User> {
        // Implement Facebook login
        return Result.failure(NotImplementedError())
    }
    
    override suspend fun loginAnonymously(): Result<User> {
        // Implement anonymous login
        return Result.failure(NotImplementedError())
    }
    
    override suspend fun logout() {
        userDao.deleteAll()
    }
    
    override fun isLoggedIn(): Flow<Boolean> {
        return userDao.getCurrentUser().map { it != null }
    }
    
    override suspend fun addCoins(amount: Int, transactionType: CoinTransactionType) {
        val user = userDao.getCurrentUser()
        // Add coins and record transaction
        coinTransactionDao.insertTransaction(
            CoinTransactionEntity(
                userId = "", // Get from current user
                amount = amount,
                type = transactionType.name,
                timestamp = System.currentTimeMillis()
            )
        )
    }
    
    override suspend fun spendCoins(amount: Int, transactionType: CoinTransactionType): Boolean {
        // Check if user has enough coins, then deduct
        return false
    }
    
    override fun getCoinsBalance(): Flow<Int> {
        return userDao.getCurrentUser().map { it?.coins ?: 0 }
    }
    
    override suspend fun addStars(amount: Int) {
        // Implement star addition
    }
    
    override fun getStarsBalance(): Flow<Int> {
        return userDao.getCurrentUser().map { it?.stars ?: 0 }
    }
    
    override fun getUserStats(): Flow<UserStats> {
        return kotlinx.coroutines.flow.flowOf(UserStats())
    }
    
    override suspend fun updateUserStats(stats: UserStats) {
        // Implement stats update
    }
    
    override suspend fun setPro(isPro: Boolean) {
        // Implement pro status update
    }
    
    override suspend fun setAdsRemoved(removed: Boolean) {
        // Implement ads removed status
    }
    
    override suspend fun setUnlimitedOnline(unlimited: Boolean) {
        // Implement unlimited online status
    }
    
    override suspend fun setSecretGameUnlocked(unlocked: Boolean) {
        // Implement secret game unlock
    }
    
    override suspend fun setAllGamesUnlocked(unlocked: Boolean) {
        // Implement all games unlock
    }
    
    override suspend fun setNickname(nickname: String) {
        // Implement nickname update
    }
    
    override suspend fun setPhotoUrl(url: String) {
        // Implement photo URL update
    }
    
    override suspend fun setSoundEnabled(enabled: Boolean) {
        // Implement sound preference
    }
    
    override fun isSoundEnabled(): Flow<Boolean> {
        return userDao.getCurrentUser().map { it?.soundEnabled ?: true }
    }
}

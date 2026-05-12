package com.memory.brain.training.games.data.local.database.dao

import androidx.room.*
import com.memory.brain.training.games.data.local.database.entity.CoinTransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinTransactionDao {
    
    @Insert
    suspend fun insertTransaction(transaction: CoinTransactionEntity)
    
    @Query("SELECT * FROM coin_transactions WHERE userId = :userId ORDER BY timestamp DESC")
    fun getTransactions(userId: String): Flow<List<CoinTransactionEntity>>
    
    @Query("SELECT * FROM coin_transactions WHERE userId = :userId ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentTransactions(userId: String, limit: Int): Flow<List<CoinTransactionEntity>>
    
    @Query("SELECT SUM(amount) FROM coin_transactions WHERE userId = :userId AND type = :type")
    fun getTotalByType(userId: String, type: String): Flow<Int?>
}

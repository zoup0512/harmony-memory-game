package com.memory.brain.training.games.data.local.database.dao

import androidx.room.*
import com.memory.brain.training.games.data.local.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    
    @Query("SELECT * FROM users LIMIT 1")
    fun getCurrentUser(): Flow<UserEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Query("UPDATE users SET coins = coins + :amount WHERE id = :userId")
    suspend fun addCoins(userId: String, amount: Int)
    
    @Query("UPDATE users SET coins = coins - :amount WHERE id = :userId")
    suspend fun subtractCoins(userId: String, amount: Int)
    
    @Query("UPDATE users SET stars = stars + :amount WHERE id = :userId")
    suspend fun addStars(userId: String, amount: Int)
    
    @Query("SELECT coins FROM users WHERE id = :userId")
    fun getCoins(userId: String): Flow<Int>
    
    @Query("SELECT stars FROM users WHERE id = :userId")
    fun getStars(userId: String): Flow<Int>
    
    @Query("DELETE FROM users")
    suspend fun deleteAll()
}

package com.memory.brain.training.games.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.memory.brain.training.games.data.local.database.dao.*
import com.memory.brain.training.games.data.local.database.entity.*

@Database(
    entities = [
        UserEntity::class,
        GameSessionEntity::class,
        GameStatsEntity::class,
        ChallengeGameEntity::class,
        WorkoutGameEntity::class,
        CoinTransactionEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MemoryGamesDatabase : RoomDatabase() {
    
    abstract fun userDao(): UserDao
    abstract fun gameSessionDao(): GameSessionDao
    abstract fun gameStatsDao(): GameStatsDao
    abstract fun challengeGameDao(): ChallengeGameDao
    abstract fun workoutGameDao(): WorkoutGameDao
    abstract fun coinTransactionDao(): CoinTransactionDao
    
    companion object {
        const val DATABASE_NAME = "memory_games_db"
    }
}

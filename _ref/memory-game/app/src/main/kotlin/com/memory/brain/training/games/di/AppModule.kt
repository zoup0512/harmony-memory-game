package com.memory.brain.training.games.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkerFactory
import com.memory.brain.training.games.data.local.database.MemoryGamesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideMemoryGamesDatabase(
        @ApplicationContext context: Context
    ): MemoryGamesDatabase {
        return Room.databaseBuilder(
            context,
            MemoryGamesDatabase::class.java,
            MemoryGamesDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    
    @Provides
    @Singleton
    fun provideUserDao(database: MemoryGamesDatabase) = database.userDao()
    
    @Provides
    @Singleton
    fun provideGameSessionDao(database: MemoryGamesDatabase) = database.gameSessionDao()
    
    @Provides
    @Singleton
    fun provideGameStatsDao(database: MemoryGamesDatabase) = database.gameStatsDao()
    
    @Provides
    @Singleton
    fun provideChallengeGameDao(database: MemoryGamesDatabase) = database.challengeGameDao()
    
    @Provides
    @Singleton
    fun provideWorkoutGameDao(database: MemoryGamesDatabase) = database.workoutGameDao()
    
    @Provides
    @Singleton
    fun provideCoinTransactionDao(database: MemoryGamesDatabase) = database.coinTransactionDao()
    
    @Provides
    @Singleton
    fun provideWorkerFactory(): WorkerFactory {
        // Return default WorkerFactory for now
        // Can be customized later if needed
        return WorkerFactory.getDefaultWorkerFactory()
    }
}

package com.memory.brain.training.games.di

import com.memory.brain.training.games.data.repository.*
import com.memory.brain.training.games.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindGameRepository(
        gameRepositoryImpl: GameRepositoryImpl
    ): GameRepository
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
    
    @Binds
    @Singleton
    abstract fun bindOnlineRepository(
        onlineRepositoryImpl: OnlineRepositoryImpl
    ): OnlineRepository
}

package com.memory.brain.training.games.data.local.database.dao

import androidx.room.*
import com.memory.brain.training.games.data.local.database.entity.WorkoutGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutGameDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutGame(game: WorkoutGameEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutGames(games: List<WorkoutGameEntity>)
    
    @Update
    suspend fun updateWorkoutGame(game: WorkoutGameEntity)
    
    @Query("SELECT * FROM workout_games WHERE dayNumber = :dayNumber ORDER BY `order` ASC")
    fun getWorkoutGames(dayNumber: Int): Flow<List<WorkoutGameEntity>>
    
    @Query("SELECT MAX(dayNumber) FROM workout_games")
    fun getCurrentDay(): Flow<Int?>
    
    @Query("DELETE FROM workout_games WHERE dayNumber = :dayNumber")
    suspend fun deleteWorkoutDay(dayNumber: Int)
}

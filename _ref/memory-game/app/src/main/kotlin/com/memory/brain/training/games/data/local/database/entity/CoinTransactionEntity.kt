package com.memory.brain.training.games.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_transactions")
data class CoinTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val amount: Int,
    val type: String,
    val timestamp: Long
)

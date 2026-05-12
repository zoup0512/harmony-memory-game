package com.memory.brain.training.games.data.mapper

import com.memory.brain.training.games.data.local.database.entity.*
import com.memory.brain.training.games.domain.model.*
import com.memory.brain.training.games.domain.repository.GameStats

// User mappings
fun UserEntity.toModel() = User(
    id = id,
    nickname = nickname,
    email = email,
    photoUrl = photoUrl,
    coins = coins,
    stars = stars,
    onlineRank = onlineRank,
    isPro = isPro,
    adsRemoved = adsRemoved,
    unlimitedOnline = unlimitedOnline,
    secretGameUnlocked = secretGameUnlocked,
    allGamesUnlocked = allGamesUnlocked
)

fun User.toEntity() = UserEntity(
    id = id,
    nickname = nickname,
    email = email,
    photoUrl = photoUrl,
    coins = coins,
    stars = stars,
    onlineRank = onlineRank,
    isPro = isPro,
    adsRemoved = adsRemoved,
    unlimitedOnline = unlimitedOnline,
    secretGameUnlocked = secretGameUnlocked,
    allGamesUnlocked = allGamesUnlocked,
    soundEnabled = true,
    createdAt = System.currentTimeMillis(),
    updatedAt = System.currentTimeMillis()
)

// GameSession mappings
fun GameSessionEntity.toModel() = GameSession(
    id = id,
    gameId = gameId,
    userId = userId,
    level = level,
    score = score,
    stars = stars,
    coinsEarned = coinsEarned,
    duration = duration,
    isCompleted = isCompleted,
    timestamp = timestamp,
    mode = GameMode.valueOf(mode)
)

fun GameSession.toEntity() = GameSessionEntity(
    id = id,
    gameId = gameId,
    userId = userId,
    level = level,
    score = score,
    stars = stars,
    coinsEarned = coinsEarned,
    duration = duration,
    isCompleted = isCompleted,
    timestamp = timestamp,
    mode = mode.name
)

// GameStats mappings
fun GameStatsEntity.toModel() = GameStats(
    gameId = gameId,
    highestLevel = highestLevel,
    totalPlays = totalPlays,
    totalStars = totalStars,
    averageScore = averageScore,
    bestScore = bestScore,
    lastPlayed = lastPlayed
)

fun GameStats.toEntity() = GameStatsEntity(
    gameId = gameId,
    highestLevel = highestLevel,
    totalPlays = totalPlays,
    totalStars = totalStars,
    averageScore = averageScore,
    bestScore = bestScore,
    lastPlayed = lastPlayed
)

// ChallengeGame mappings
fun ChallengeGameEntity.toModel() = ChallengeGame(
    id = id,
    gameId = gameId,
    levelNumber = levelNumber,
    difficulty = difficulty,
    requiredScore = requiredScore,
    timeLimit = timeLimit,
    isCompleted = isCompleted,
    stars = stars
)

fun ChallengeGame.toEntity() = ChallengeGameEntity(
    id = id,
    gameId = gameId,
    levelNumber = levelNumber,
    difficulty = difficulty,
    requiredScore = requiredScore,
    timeLimit = timeLimit,
    isCompleted = isCompleted,
    stars = stars
)

// WorkoutGame mappings
fun WorkoutGameEntity.toModel() = WorkoutGame(
    id = id,
    gameId = gameId,
    dayNumber = dayNumber,
    order = order,
    targetLevel = targetLevel,
    isCompleted = isCompleted
)

fun WorkoutGame.toEntity() = WorkoutGameEntity(
    id = id,
    gameId = gameId,
    dayNumber = dayNumber,
    order = order,
    targetLevel = targetLevel,
    isCompleted = isCompleted
)

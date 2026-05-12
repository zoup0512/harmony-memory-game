# 暂停功能实现进度

## 已完成的游戏 (7/23)

### 1. ✅ Memory Grid (记忆网格)
- **文件**: `MemoryGridViewModel.kt`, `MemoryGridGameScreen.kt`
- **状态**: 完成
- **功能**: 暂停、继续、重新开始、退出

### 2. ✅ Catch Them
- **文件**: `CatchThemGame.kt` (单文件)
- **状态**: 完成
- **功能**: 暂停计时器、恢复游戏、重新开始

### 3. ✅ Game 248
- **文件**: `Game248.kt` (单文件)
- **状态**: 完成
- **功能**: 暂停计时器、恢复游戏、重新开始

### 4. ✅ Laser
- **文件**: `LaserGame.kt` (单文件)
- **状态**: 完成
- **功能**: 暂停计时器、恢复游戏、重新开始

### 5. ✅ More Less
- **文件**: `MoreLessViewModel.kt`, `MoreLessGameScreen.kt`
- **状态**: 完成
- **功能**: 暂停关卡计时器、恢复游戏、重新开始

### 6. ✅ Symmetry (对称)
- **文件**: `SymmetryViewModel.kt`, `SymmetryGameScreen.kt`
- **状态**: 完成
- **功能**: 暂停游戏计时器、恢复游戏、重新开始

### 7. ✅ All The Same
- **文件**: `AllTheSameViewModel.kt`, `AllTheSameGameScreen.kt`
- **状态**: 完成
- **功能**: 暂停游戏计时器、恢复游戏、重新开始

## 待实现的游戏 (16/23)

### 高优先级 (有计时器的游戏)
8. ⬜ Find All - `FindAllViewModel.kt`, `FindAllGameScreen.kt`
9. ⬜ Like Previous - `LikePreviousViewModel.kt`, `LikePreviousGameScreen.kt`
10. ⬜ Paper Planes - `PaperPlanesViewModel.kt`, `PaperPlanesGameScreen.kt`
11. ⬜ Schultz Tables - `SchultzTablesViewModel.kt`, `SchultzTablesGameScreen.kt`
12. ⬜ Sort The Digits - `SortTheDigitsViewModel.kt`, `SortTheDigitsGameScreen.kt`
13. ⬜ Colors - `ColorsViewModel.kt`, `ColorsGameScreen.kt`

### 中优先级 (其他游戏)
14. ⬜ Pyramids - `PyramidsViewModel.kt`, `PyramidsGameScreen.kt`
15. ⬜ Whos New - `WhosNewViewModel.kt`, `WhosNewGameScreen.kt`
16. ⬜ Hexagons - `HexagonsViewModel.kt`, `HexagonsGameScreen.kt`
17. ⬜ Follow The Path - `FollowThePathViewModel.kt`, `FollowThePathGameScreen.kt`
18. ⬜ Count Em All - `CountEmAllViewModel.kt`, `CountEmAllGameScreen.kt`
19. ⬜ Find The Picture - `FindThePictureViewModel.kt`, `FindThePictureGameScreen.kt`
20. ⬜ Image Vortex - `ImageVortexViewModel.kt`, `ImageVortexGameScreen.kt`
21. ⬜ Color Match
22. ⬜ Color Memory
23. ⬜ 其他游戏...

## 实现模式

### 对于 ViewModel:
```kotlin
// 1. 在 UiState 中添加
val isPaused: Boolean = false

// 2. 添加暂停方法
fun pauseGame() {
    timerJob?.cancel()
    _uiState.value = _uiState.value.copy(isPaused = true)
}

fun resumeGame() {
    _uiState.value = _uiState.value.copy(isPaused = false)
    if (_uiState.value.gameState == GameState.PLAYING) {
        startTimer() // 或其他恢复逻辑
    }
}

fun restartGame() {
    _uiState.value = _uiState.value.copy(isPaused = false)
    startGame()
}
```

### 对于 GameScreen:
```kotlin
// 1. 导入 PauseDialog
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

// 2. 添加暂停对话框
if (uiState.isPaused) {
    PauseDialog(
        gameName = game?.name ?: "",
        onDismiss = { viewModel.resumeGame() },
        onResume = { viewModel.resumeGame() },
        onRestart = { viewModel.restartGame() },
        onExit = onNavigateBack
    )
}

// 3. 修改 BaseGameScreen
onPauseClick = { viewModel.pauseGame() }
```

## 下一步
继续实现剩余16个游戏的暂停功能

# 批量实现暂停功能

## 策略

由于有23个游戏需要实现暂停功能，我将采用以下策略：

### 方法1：使用单文件游戏（已有暂停支持）

以下游戏使用单文件架构，已经包含 ViewModel 和 UI：
- CatchThemGame.kt
- Game248.kt  
- LaserGame.kt
- MoreLessGameScreen.kt
- SymmetryGameScreen.kt

这些游戏需要在单个文件中添加暂停功能。

### 方法2：使用分离文件的游戏

大多数游戏使用分离的 ViewModel 和 GameScreen 文件。

## 通用暂停代码模板

### ViewModel 添加

```kotlin
// 在 UiState 中添加
val isPaused: Boolean = false

// 在 ViewModel 中添加方法
fun pauseGame() {
    timerJob?.cancel()
    _uiState.value = _uiState.value.copy(isPaused = true)
}

fun resumeGame() {
    _uiState.value = _uiState.value.copy(isPaused = false)
    // 根据游戏类型恢复计时器
    startTimer() // 或其他恢复逻辑
}

fun restartGame() {
    _uiState.value = _uiState.value.copy(isPaused = false)
    startGame()
}
```

### GameScreen 添加

```kotlin
// 导入
import com.memory.brain.training.games.presentation.screens.game.common.PauseDialog

// 在 Composable 中添加
if (uiState.isPaused) {
    PauseDialog(
        gameName = game?.name ?: "",
        onDismiss = { viewModel.resumeGame() },
        onResume = { viewModel.resumeGame() },
        onRestart = { viewModel.restartGame() },
        onExit = onNavigateBack
    )
}

// 修改 BaseGameScreen
onPauseClick = { viewModel.pauseGame() }
```

## 实现优先级

### 高优先级（已实现的游戏）

1. ✅ Memory Grid - 已完成
2. ⬜ Catch Them
3. ⬜ 248
4. ⬜ Laser
5. ⬜ More Less
6. ⬜ Symmetry

### 中优先级（有计时器的游戏）

7. ⬜ All The Same
8. ⬜ Find All
9. ⬜ Like Previous
10. ⬜ Paper Planes
11. ⬜ Schultz Tables
12. ⬜ Sort The Digits
13. ⬜ Colors

### 低优先级（其他游戏）

14-23. 其他游戏

## 批量实现脚本

由于手动修改23个游戏工作量大，建议：

1. 先实现主要的5-10个游戏
2. 测试功能是否正常
3. 再批量实现其余游戏


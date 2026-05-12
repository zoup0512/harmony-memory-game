# 调试日志指南

## 已添加的日志

为了调试"答对后无法进入下一关卡"的问题，我在关键位置添加了详细的日志输出。

### 日志图标说明

- 🎬 **startGame** - 游戏开始
- 🎮 **generatePattern** - 生成图案
- 🖱️ **onCellClick** - 用户点击格子
- 📝 **submitAnswer** - 提交答案
- ✅ **CORRECT** - 答对
- ❌ **WRONG** - 答错
- ➡️ **Next Level** - 进入下一关
- 🎉 **Game Complete** - 完成所有关卡
- 💀 **Game Over** - 游戏结束（失败）
- 🏁 **endGame** - 游戏结束函数
- 🎨 **GameScreen** - UI层状态变化
- ⚠️ **Warning** - 警告或被拒绝的操作

## 日志位置

### 1. GameViewModel.kt

#### startGame()
```kotlin
🎬 [startGame] Starting new game
🎬 [startGame] Initial state set, calling generatePattern()
🎬 [startGame] Starting countdown
```

#### generatePattern()
```kotlin
🎮 [generatePattern] Level=1, PatternSize=4, Pattern=[2, 5, 9, 13]
🎮 [generatePattern] State updated to SHOWING_PATTERN
🎮 [generatePattern] Starting pattern display coroutine, delay=2200ms
🎮 [generatePattern] Pattern display finished, switching to PLAYER_TURN
```

#### onCellClick()
```kotlin
🖱️ [onCellClick] Selected position=2, selections=[2]
🖱️ [onCellClick] Selected position=5, selections=[2, 5]
🖱️ [onCellClick] Deselected position=5, selections=[2]
⚠️ [onCellClick] Ignored click on position=2, gameState=SHOWING_PATTERN
```

#### submitAnswer()
```kotlin
📝 [submitAnswer] Called, gameState=PLAYER_TURN
📝 [submitAnswer] Pattern: {2, 5, 9, 13}
📝 [submitAnswer] UserSelections: {2, 5, 9, 13}
📝 [submitAnswer] IsCorrect: true
✅ [submitAnswer] CORRECT! OldLevel=1, NewLevel=2, OldScore=0, NewScore=100
➡️ [submitAnswer] Continuing to level 2
➡️ [submitAnswer] State updated, scheduling generatePattern() in 500ms
➡️ [submitAnswer] Calling generatePattern() for level 2
```

或者答错时：
```kotlin
📝 [submitAnswer] IsCorrect: false
❌ [submitAnswer] WRONG! OldLives=3, NewLives=2
🔄 [submitAnswer] Showing same pattern again
```

或者完成所有关卡：
```kotlin
🎉 [submitAnswer] Game completed! All 10 levels finished
```

#### endGame()
```kotlin
🏁 [endGame] Game ending, cancelling jobs
🏁 [endGame] State set to GAME_OVER
```

### 2. GameScreen.kt

#### 状态变化监控
```kotlin
🎨 [GameScreen] State changed to: SHOWING_PATTERN
🎨 [GameScreen] Level changed to: 2
🎨 [GameScreen] Score changed to: 100
🎨 [GameScreen] GAME_OVER detected, navigating to result screen. Score=5500, Stars=3
```

## 如何查看日志

### 方法1：Android Studio Logcat
1. 打开 Android Studio
2. 点击底部的 "Logcat" 标签
3. 在搜索框中输入过滤条件：
   - `submitAnswer` - 查看提交答案相关日志
   - `generatePattern` - 查看图案生成日志
   - `GameScreen` - 查看UI状态变化
   - `🎮` 或 `📝` - 使用图标过滤

### 方法2：命令行 adb logcat
```bash
# 查看所有日志
adb logcat | grep "submitAnswer\|generatePattern\|GameScreen"

# 只看特定标签
adb logcat | grep "🎮\|📝\|🎨"

# 清除旧日志后查看
adb logcat -c && adb logcat
```

### 方法3：Android Studio Run 窗口
运行应用时，日志会直接显示在 Run 窗口中。

## 预期的正常流程日志

### 场景1：答对进入下一关

```
🎬 [startGame] Starting new game
🎬 [startGame] Initial state set, calling generatePattern()
🎮 [generatePattern] Level=1, PatternSize=4, Pattern=[2, 5, 9, 13]
🎮 [generatePattern] State updated to SHOWING_PATTERN
🎨 [GameScreen] State changed to: SHOWING_PATTERN
🎮 [generatePattern] Starting pattern display coroutine, delay=2200ms

... 等待2.2秒 ...

🎮 [generatePattern] Pattern display finished, switching to PLAYER_TURN
🎨 [GameScreen] State changed to: PLAYER_TURN

... 用户点击格子 ...

🖱️ [onCellClick] Selected position=2, selections=[2]
🖱️ [onCellClick] Selected position=5, selections=[2, 5]
🖱️ [onCellClick] Selected position=9, selections=[2, 5, 9]
🖱️ [onCellClick] Selected position=13, selections=[2, 5, 9, 13]

... 用户点击提交 ...

📝 [submitAnswer] Called, gameState=PLAYER_TURN
📝 [submitAnswer] Pattern: {2, 5, 9, 13}
📝 [submitAnswer] UserSelections: {2, 5, 9, 13}
📝 [submitAnswer] IsCorrect: true
✅ [submitAnswer] CORRECT! OldLevel=1, NewLevel=2, OldScore=0, NewScore=100
➡️ [submitAnswer] Continuing to level 2
➡️ [submitAnswer] State updated, scheduling generatePattern() in 500ms
🎨 [GameScreen] Level changed to: 2
🎨 [GameScreen] Score changed to: 100

... 等待500ms ...

➡️ [submitAnswer] Calling generatePattern() for level 2
🎮 [generatePattern] Level=2, PatternSize=5, Pattern=[1, 4, 7, 10, 14]
🎮 [generatePattern] State updated to SHOWING_PATTERN
🎨 [GameScreen] State changed to: SHOWING_PATTERN
🎮 [generatePattern] Starting pattern display coroutine, delay=2400ms

... 第2关开始 ...
```

### 场景2：答错重试

```
📝 [submitAnswer] Called, gameState=PLAYER_TURN
📝 [submitAnswer] Pattern: {2, 5, 9, 13}
📝 [submitAnswer] UserSelections: {2, 5, 9, 14}  ← 选错了
📝 [submitAnswer] IsCorrect: false
❌ [submitAnswer] WRONG! OldLives=3, NewLives=2
🔄 [submitAnswer] Showing same pattern again

... 重新显示图案 ...
```

### 场景3：完成所有关卡

```
📝 [submitAnswer] Called, gameState=PLAYER_TURN
📝 [submitAnswer] Pattern: {...}
📝 [submitAnswer] UserSelections: {...}
📝 [submitAnswer] IsCorrect: true
✅ [submitAnswer] CORRECT! OldLevel=10, NewLevel=11, OldScore=5400, NewScore=5500
🎉 [submitAnswer] Game completed! All 10 levels finished
🎨 [GameScreen] State changed to: GAME_OVER
🎨 [GameScreen] GAME_OVER detected, navigating to result screen. Score=5500, Stars=3
```

## 问题诊断指南

### 问题1：答对后没有进入下一关

**查找日志**：
```
✅ [submitAnswer] CORRECT!
➡️ [submitAnswer] Continuing to level X
```

**如果看到这些日志**：
- 说明 `submitAnswer()` 正确执行了
- 检查是否有 `➡️ [submitAnswer] Calling generatePattern()` 日志
- 检查是否有 `🎮 [generatePattern] Level=X` 日志

**如果没有看到 `generatePattern` 日志**：
- 协程可能被取消或延迟执行失败
- 检查是否有异常日志

**如果看到 `generatePattern` 但没有状态变化**：
- 检查 `🎨 [GameScreen] State changed to:` 日志
- UI可能没有正确响应状态变化

### 问题2：图案显示时间不对

**查找日志**：
```
🎮 [generatePattern] Starting pattern display coroutine, delay=XXXXms
🎮 [generatePattern] Pattern display finished, switching to PLAYER_TURN
```

**检查**：
- 两条日志之间的时间差是否等于 delay 时间
- 如果时间差很短，说明协程被提前取消

### 问题3：用户点击无响应

**查找日志**：
```
⚠️ [onCellClick] Ignored click on position=X, gameState=SHOWING_PATTERN
```

**说明**：
- 用户在错误的状态下点击（不是 PLAYER_TURN）
- 检查当前 gameState 是什么

### 问题4：判断逻辑错误

**查找日志**：
```
📝 [submitAnswer] Pattern: {2, 5, 9, 13}
📝 [submitAnswer] UserSelections: {2, 5, 9, 14}
📝 [submitAnswer] IsCorrect: false
```

**检查**：
- Pattern 和 UserSelections 是否应该相等
- 如果应该相等但判断为 false，说明判断逻辑有问题

## 调试步骤

1. **清除旧日志**
   ```bash
   adb logcat -c
   ```

2. **启动应用并开始游戏**

3. **玩第1关并答对**

4. **查看日志输出**
   ```bash
   adb logcat | grep "🎮\|📝\|➡️\|🎨"
   ```

5. **分析日志**
   - 是否看到 `✅ CORRECT!`？
   - 是否看到 `➡️ Continuing to level 2`？
   - 是否看到 `➡️ Calling generatePattern()`？
   - 是否看到 `🎮 [generatePattern] Level=2`？
   - 是否看到 `🎨 State changed to: SHOWING_PATTERN`？

6. **找出中断点**
   - 在哪一步日志停止了？
   - 那就是问题所在

## 日志示例：完整的一关流程

```
T0.0s  🎮 [generatePattern] Level=1, PatternSize=4, Pattern=[2, 5, 9, 13]
T0.0s  🎮 [generatePattern] State updated to SHOWING_PATTERN
T0.0s  🎨 [GameScreen] State changed to: SHOWING_PATTERN
T0.0s  🎮 [generatePattern] Starting pattern display coroutine, delay=2200ms
T2.2s  🎮 [generatePattern] Pattern display finished, switching to PLAYER_TURN
T2.2s  🎨 [GameScreen] State changed to: PLAYER_TURN
T3.0s  🖱️ [onCellClick] Selected position=2, selections=[2]
T3.5s  🖱️ [onCellClick] Selected position=5, selections=[2, 5]
T4.0s  🖱️ [onCellClick] Selected position=9, selections=[2, 5, 9]
T4.5s  🖱️ [onCellClick] Selected position=13, selections=[2, 5, 9, 13]
T5.0s  📝 [submitAnswer] Called, gameState=PLAYER_TURN
T5.0s  📝 [submitAnswer] Pattern: {2, 5, 9, 13}
T5.0s  📝 [submitAnswer] UserSelections: {2, 5, 9, 13}
T5.0s  📝 [submitAnswer] IsCorrect: true
T5.0s  ✅ [submitAnswer] CORRECT! OldLevel=1, NewLevel=2, OldScore=0, NewScore=100
T5.0s  ➡️ [submitAnswer] Continuing to level 2
T5.0s  ➡️ [submitAnswer] State updated, scheduling generatePattern() in 500ms
T5.0s  🎨 [GameScreen] Level changed to: 2
T5.0s  🎨 [GameScreen] Score changed to: 100
T5.5s  ➡️ [submitAnswer] Calling generatePattern() for level 2
T5.5s  🎮 [generatePattern] Level=2, PatternSize=5, Pattern=[1, 4, 7, 10, 14]
T5.5s  🎮 [generatePattern] State updated to SHOWING_PATTERN
T5.5s  🎨 [GameScreen] State changed to: SHOWING_PATTERN
T5.5s  🎮 [generatePattern] Starting pattern display coroutine, delay=2400ms
T7.9s  🎮 [generatePattern] Pattern display finished, switching to PLAYER_TURN
T7.9s  🎨 [GameScreen] State changed to: PLAYER_TURN
```

## 构建状态

✅ **BUILD SUCCESSFUL** in 49s
- 36 actionable tasks: 19 executed, 17 from cache
- 日志代码已编译通过

## 下一步

1. 运行应用
2. 玩游戏并答对第1关
3. 查看 Logcat 输出
4. 找出日志在哪里中断
5. 根据中断点定位问题

日志会清楚地显示整个流程的每一步，帮助我们找出问题所在。

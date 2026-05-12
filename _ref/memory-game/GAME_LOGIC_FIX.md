# 游戏逻辑修复说明

## 问题描述
用户报告："数字游戏"（记忆网格游戏）第一关卡完成后没有游戏结算以及跳到下一关卡。

## 问题分析

### 原始问题
1. **无限关卡循环**：答对后会无限进入下一关卡，level不断增加，永远不会结束
2. **缺少关卡上限**：没有定义最大关卡数，导致游戏无法正常结束
3. **状态管理问题**：在答对后更新状态时，没有清除用户的选择，可能导致UI显示混乱

### 根本原因
原始的 `submitAnswer()` 逻辑中：
```kotlin
if (isCorrect) {
    // 只是简单地增加关卡和分数
    val newLevel = _uiState.value.level + 1
    // 没有检查是否达到最大关卡
    // 继续生成下一个图案
}
```

## 修复方案

### 1. 添加最大关卡限制
```kotlin
companion object {
    private const val MAX_LEVELS = 10 // 最多10关
}
```

### 2. 修改答对逻辑
```kotlin
if (isCorrect) {
    val newScore = _uiState.value.score + (100 * _uiState.value.level)
    val newLevel = _uiState.value.level + 1
    
    // 检查是否达到最大关卡
    if (newLevel > MAX_LEVELS) {
        // 游戏成功完成，进入结算界面
        _uiState.update { 
            it.copy(
                score = newScore,
                gameState = GameState.GAME_OVER
            )
        }
        countdownJob?.cancel() // 停止倒计时
    } else {
        // 继续下一关
        _uiState.update { 
            it.copy(
                score = newScore,
                level = newLevel,
                userSelections = emptyList() // 清除用户选择
            )
        }
        
        // 延迟后生成新图案
        viewModelScope.launch {
            delay(500)
            generatePattern()
        }
    }
}
```

### 3. 清除用户选择
在答对和答错时都清除用户的选择，避免UI显示混乱：
```kotlin
userSelections = emptyList()
```

## 游戏流程

### 正常完成流程
1. 玩家开始游戏（第1关）
2. 记住图案并正确选择 → 进入第2关
3. 继续答对 → 第3关、第4关...
4. 完成第10关后 → `gameState` 变为 `GAME_OVER`
5. `GameScreen` 检测到 `GAME_OVER` → 自动导航到结算界面
6. 显示最终分数和获得的星星

### 失败流程
1. 答错 → 失去1条生命
2. 生命值为0 → `gameState` 变为 `GAME_OVER`
3. 导航到结算界面

### 超时流程
1. 60秒倒计时结束 → `gameState` 变为 `GAME_OVER`
2. 导航到结算界面

## 分数计算

- **每关基础分**：100 × 当前关卡数
  - 第1关：100分
  - 第2关：200分
  - 第3关：300分
  - ...
  - 第10关：1000分
  
- **完美通关总分**：100 + 200 + 300 + ... + 1000 = **5500分**

## 星星评级

根据最终分数计算星星：
- **3星**：≥ 1000分
- **2星**：≥ 500分
- **1星**：≥ 200分
- **0星**：< 200分

完美通关（5500分）可获得3星。

## 测试建议

### 测试场景1：正常通关
1. 启动游戏
2. 连续答对10关
3. 验证：
   - 每关分数正确累加
   - 第10关完成后自动跳转到结算界面
   - 显示正确的总分和星星数

### 测试场景2：中途失败
1. 启动游戏
2. 答对几关后故意答错3次
3. 验证：
   - 生命值正确减少
   - 生命值为0时跳转到结算界面
   - 显示当前累计分数

### 测试场景3：超时
1. 启动游戏
2. 等待60秒不操作
3. 验证：
   - 倒计时正确显示
   - 时间到后跳转到结算界面

### 测试场景4：关卡进度
1. 启动游戏
2. 观察每关的图案数量
3. 验证：
   - 第1关：4个格子
   - 第2关：5个格子
   - 第3关：6个格子
   - ...
   - 第9关及以后：12个格子（上限）

## 修改的文件

- `app/src/main/kotlin/com/memory/brain/training/games/presentation/screens/game/GameViewModel.kt`
  - 添加 `MAX_LEVELS` 常量
  - 修改 `submitAnswer()` 方法
  - 添加关卡完成检查
  - 清除用户选择状态

## 构建状态

✅ **BUILD SUCCESSFUL** in 1m 55s
- 36 actionable tasks: 19 executed, 17 from cache
- 只有一些未使用参数的警告，不影响功能

## 后续优化建议

1. **可配置难度**：允许玩家选择关卡数量（5关/10关/15关）
2. **难度曲线**：调整每关的图案数量增长速度
3. **奖励机制**：完美通关给予额外奖励
4. **关卡提示**：在UI上显示"第X关/共10关"
5. **进度保存**：允许玩家暂停并继续游戏

# 暂停功能说明

## 当前状态

**暂停按钮**: ✅ 已显示  
**暂停功能**: ❌ 未实现

所有游戏的 `onPauseClick` 都是空实现：
```kotlin
onPauseClick = { /* TODO: Implement pause */ }
```

---

## 旧项目中的暂停功能

### PauseDialog 功能

旧项目中的暂停对话框提供以下功能：

1. **继续游戏** (Resume)
   - 关闭对话框，继续游戏
   - 快捷键：返回键

2. **重新开始** (Replay)
   - 重新开始当前游戏
   - 从第1关开始

3. **查看教程** (Tutorial)
   - 打开游戏教程页面
   - 查看游戏玩法说明

4. **退出到主菜单** (Exit)
   - 保存游戏进度
   - 返回主菜单

5. **声音开关** (Sound Toggle)
   - 开启/关闭游戏音效
   - 状态持久化保存

### 对话框布局

```
┌─────────────────────────┐
│       游戏暂停          │
├─────────────────────────┤
│                         │
│   [继续游戏]  Resume    │
│                         │
│   [重新开始]  Replay    │
│                         │
│   [查看教程]  Tutorial  │
│                         │
│   [退出游戏]  Exit      │
│                         │
│   🔊 声音开关           │
│                         │
└─────────────────────────┘
```

---

## 实现计划

### 第一阶段：基础暂停功能 ⭐

**优先级**: 高  
**工作量**: 中等

#### 功能列表

1. ✅ **暂停按钮** - 已显示
2. ⬜ **暂停对话框** - 需要实现
3. ⬜ **继续游戏** - 需要实现
4. ⬜ **退出到主菜单** - 需要实现

#### 实现步骤

1. **创建 PauseDialog Composable**
   ```kotlin
   @Composable
   fun PauseDialog(
       onDismiss: () -> Unit,
       onResume: () -> Unit,
       onExit: () -> Unit
   )
   ```

2. **在 ViewModel 中添加暂停状态**
   ```kotlin
   data class GameUiState(
       // ... 其他状态
       val isPaused: Boolean = false
   )
   ```

3. **实现暂停逻辑**
   ```kotlin
   fun pauseGame() {
       // 暂停计时器
       timerJob?.cancel()
       // 更新状态
       _uiState.value = _uiState.value.copy(isPaused = true)
   }
   
   fun resumeGame() {
       // 恢复计时器
       startTimer()
       // 更新状态
       _uiState.value = _uiState.value.copy(isPaused = false)
   }
   ```

4. **在 GameScreen 中显示对话框**
   ```kotlin
   if (uiState.isPaused) {
       PauseDialog(
           onDismiss = { viewModel.resumeGame() },
           onResume = { viewModel.resumeGame() },
           onExit = { onNavigateBack() }
       )
   }
   ```

### 第二阶段：完整功能 ⭐⭐

**优先级**: 中等  
**工作量**: 较大

#### 额外功能

5. ⬜ **重新开始** - 重置游戏
6. ⬜ **查看教程** - 显示游戏说明
7. ⬜ **声音开关** - 音效控制

#### 实现步骤

1. **添加重新开始功能**
   ```kotlin
   fun restartGame() {
       progression.startGame()
       startGameFlow()
   }
   ```

2. **添加教程系统**
   - 创建教程页面
   - 为每个游戏编写说明
   - 添加导航逻辑

3. **添加声音系统**
   - 集成音效库
   - 添加音效文件
   - 实现音效控制

---

## 技术实现

### 1. PauseDialog Composable

```kotlin
@Composable
fun PauseDialog(
    gameName: String,
    onDismiss: () -> Unit,
    onResume: () -> Unit,
    onRestart: () -> Unit = {},
    onTutorial: () -> Unit = {},
    onExit: () -> Unit,
    showRestart: Boolean = true,
    showTutorial: Boolean = false
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 标题
                Text(
                    text = "游戏暂停",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Divider()
                
                // 继续游戏
                PauseButton(
                    text = "继续游戏",
                    icon = Icons.Default.PlayArrow,
                    onClick = onResume
                )
                
                // 重新开始
                if (showRestart) {
                    PauseButton(
                        text = "重新开始",
                        icon = Icons.Default.Refresh,
                        onClick = onRestart
                    )
                }
                
                // 查看教程
                if (showTutorial) {
                    PauseButton(
                        text = "查看教程",
                        icon = Icons.Default.Help,
                        onClick = onTutorial
                    )
                }
                
                // 退出游戏
                PauseButton(
                    text = "退出游戏",
                    icon = Icons.Default.ExitToApp,
                    onClick = onExit,
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
private fun PauseButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    color: Color = Color.Black
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = color.copy(alpha = 0.1f),
            contentColor = color
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp)
    }
}
```

### 2. ViewModel 修改

```kotlin
class MemoryGridViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(MemoryGridUiState())
    val uiState: StateFlow<MemoryGridUiState> = _uiState.asStateFlow()
    
    private var gameFlowJob: Job? = null
    private var pausedTime: Long = 0
    
    fun pauseGame() {
        // 暂停游戏流程
        gameFlowJob?.cancel()
        pausedTime = System.currentTimeMillis()
        
        _uiState.value = _uiState.value.copy(isPaused = true)
    }
    
    fun resumeGame() {
        // 恢复游戏流程
        _uiState.value = _uiState.value.copy(isPaused = false)
        
        // 根据当前状态恢复
        when (_uiState.value.gameState) {
            GameFlowState.USER_INPUT -> {
                // 用户输入阶段，直接恢复
            }
            else -> {
                // 其他阶段，重新开始流程
                startGameFlow()
            }
        }
    }
    
    fun restartGame() {
        startGame()
    }
}

data class MemoryGridUiState(
    // ... 其他状态
    val isPaused: Boolean = false
)
```

### 3. GameScreen 修改

```kotlin
@Composable
fun MemoryGridGameScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    onGameComplete: (score: Int, stars: Int) -> Unit,
    viewModel: MemoryGridViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = GameDataProvider.getAllGames().find { it.id == gameId }
    
    // 暂停对话框
    if (uiState.isPaused) {
        PauseDialog(
            gameName = game?.name ?: "",
            onDismiss = { viewModel.resumeGame() },
            onResume = { viewModel.resumeGame() },
            onRestart = { viewModel.restartGame() },
            onExit = { onNavigateBack() }
        )
    }
    
    BaseGameScreen(
        game = game,
        score = uiState.score,
        level = uiState.level,
        lives = uiState.lives,
        onNavigateBack = onNavigateBack,
        onPauseClick = { viewModel.pauseGame() }  // 🆕 实现暂停
    ) {
        // 游戏内容
    }
}
```

---

## 用户体验

### 暂停时的行为

1. **计时器暂停**
   - 所有计时器停止
   - 时间不再流逝

2. **游戏状态冻结**
   - 用户无法操作游戏
   - 游戏画面保持不变

3. **显示暂停对话框**
   - 半透明背景
   - 居中显示对话框
   - 可以点击按钮或返回键

### 继续游戏时的行为

1. **关闭对话框**
   - 对话框消失
   - 恢复游戏画面

2. **恢复计时器**
   - 计时器继续计时
   - 从暂停时的时间继续

3. **恢复游戏状态**
   - 用户可以继续操作
   - 游戏继续进行

---

## 实现优先级

### 必须实现（MVP）

1. ✅ 暂停按钮显示
2. ⬜ 暂停对话框
3. ⬜ 继续游戏功能
4. ⬜ 退出游戏功能
5. ⬜ 计时器暂停/恢复

### 建议实现

6. ⬜ 重新开始功能
7. ⬜ 返回键处理
8. ⬜ 暂停动画效果

### 可选实现

9. ⬜ 查看教程功能
10. ⬜ 声音开关
11. ⬜ 暂停统计（暂停次数、时长）

---

## 工作量估算

### 基础功能（第一阶段）

- **PauseDialog 组件**: 2-3小时
- **ViewModel 暂停逻辑**: 1-2小时
- **GameScreen 集成**: 1小时
- **测试和调试**: 1-2小时

**总计**: 5-8小时

### 完整功能（第二阶段）

- **重新开始功能**: 1小时
- **教程系统**: 4-6小时
- **声音系统**: 3-4小时
- **测试和调试**: 2-3小时

**总计**: 10-14小时

---

## 测试要点

### 功能测试

- [ ] 点击暂停按钮显示对话框
- [ ] 点击继续游戏恢复游戏
- [ ] 点击退出游戏返回主菜单
- [ ] 点击重新开始重置游戏
- [ ] 返回键关闭对话框
- [ ] 计时器正确暂停和恢复

### 状态测试

- [ ] 暂停时游戏状态冻结
- [ ] 恢复时游戏状态正确
- [ ] 暂停期间不计时
- [ ] 暂停不影响分数和生命

### UI测试

- [ ] 对话框居中显示
- [ ] 背景半透明
- [ ] 按钮可点击
- [ ] 文字清晰可读

---

## 相关文件

### 旧项目参考

- `src/main/java/com/cube/memorygames/ui/PauseDialog.java`
- `src/main/res/layout/dialog_pause.xml`

### 新项目需要创建

- `app/src/main/kotlin/com/memory/brain/training/games/presentation/screens/game/common/PauseDialog.kt`
- 修改所有游戏的 ViewModel 和 GameScreen

---

## 总结

**当前状态**: 暂停按钮已显示，但功能未实现

**建议**: 
1. 优先实现基础暂停功能（继续、退出）
2. 后续添加完整功能（重新开始、教程、声音）

**影响范围**: 所有23个游戏

**用户价值**: 
- 提升用户体验
- 允许用户中断游戏
- 提供更多控制选项

---

**文档日期**: 2026-05-04  
**功能状态**: ❌ 未实现  
**优先级**: 中等


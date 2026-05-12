# 🎮 23个游戏完整重构计划

## 📋 重构策略

### 阶段1: 核心框架 ✅
- [x] 分析旧项目结构
- [x] 理解UI布局
- [x] 创建基础架构

### 阶段2: 逐个游戏实现 (进行中)

## 🎯 游戏实现顺序

### 第一批：记忆类游戏 (6个)

#### 1. Memory Grid (记忆网格) - 优先级: 最高
- **状态**: ✅ 已有基础实现，需要调整以匹配旧UI
- **文件**: `Game1MemoryGridActivity.java`
- **UI特点**:
  - 矩形网格
  - 显示图案 → 玩家选择 → 验证
  - 3条生命
  - 关卡递增
- **实现**: 需要重构现有实现

#### 2. Hexagons (六边形) - 优先级: 高
- **文件**: `Game3HexagonsActivity.java`
- **UI特点**:
  - 六边形网格
  - 类似Memory Grid但使用六边形
- **实现**: 新建

#### 3. Who's New? (谁是新的) - 优先级: 高
- **文件**: `Game4WhoNewActivity.java`
- **UI特点**:
  - 显示多个图像
  - 识别新出现的图像
- **实现**: 新建

#### 4. Follow the Path (跟随路径) - 优先级: 中
- **文件**: `Game6FollowThePathActivity.java`
- **UI特点**:
  - 显示路径动画
  - 玩家重复路径
- **实现**: 新建

#### 5. Image Vortex (图像漩涡) - 优先级: 中
- **文件**: `Game7ImageVortexActivity.java`
- **UI特点**:
  - 旋转的图像网格
  - 记忆图像位置
- **实现**: 新建

#### 6. Find the Picture (找图片) - 优先级: 中
- **文件**: `Game9FindThePictureActivity.java`
- **UI特点**:
  - 显示图片
  - 从多个选项中找到之前的图片
- **实现**: 新建

### 第二批：注意力类游戏 (2个)

#### 7. Rotating Grid (旋转网格) - 优先级: 高
- **文件**: `Game2RotatingGridActivity.java`
- **UI特点**:
  - 旋转的网格
  - 跟踪特定单元格
- **实现**: 新建

#### 8. Catch Them (抓住它们) - 优先级: 高
- **文件**: `Game8CatchThemActivity.java`
- **UI特点**:
  - 移动的目标
  - 快速点击
- **实现**: 新建

### 第三批：速度类游戏 (5个)

#### 9. One and Only (唯一的) - 优先级: 高
- **文件**: `Game10OneAndOnlyActivity.java`
- **UI特点**:
  - 网格显示元素
  - 找出唯一不同的
- **实现**: 新建

#### 10. All the Same (全部相同) - 优先级: 高
- **文件**: `Game13AllTheSameActivity.java`
- **UI特点**:
  - 判断所有元素是否相同
  - 快速反应
- **实现**: 新建

#### 11. Sort the Digits (排序数字) - 优先级: 中
- **文件**: `Game14SortTheDigitsActivity.java`
- **UI特点**:
  - 显示数字
  - 判断是否按顺序排列
- **实现**: 新建

#### 12. Find All (找到全部) - 优先级: 中
- **文件**: `Game19FindAllActivity.java`
- **UI特点**:
  - 找到所有目标元素
  - 时间限制
- **实现**: 新建

#### 13. Schultz Tables (舒尔特表) - 优先级: 中
- **文件**: `Game23TableActivity.java`
- **UI特点**:
  - 5x5数字网格
  - 按顺序点击1-25
- **实现**: 新建

### 第四批：问题解决类游戏 (5个)

#### 14. Correctly? (正确吗) - 优先级: 高
- **文件**: `Game11CorrectlyActivity.java`
- **UI特点**:
  - 显示数学表达式
  - 判断结果是否正确
- **实现**: 新建

#### 15. More, Less (更多更少) - 优先级: 高
- **文件**: `Game12MoreLessActivity.java`
- **UI特点**:
  - 比较两个数字
  - 选择更大或更小
- **实现**: 新建

#### 16. 248 - 优先级: 低
- **文件**: `Game17_248Activity.java`
- **UI特点**:
  - 类似2048游戏
  - 合并相同数字
- **实现**: 新建

#### 17. Symmetry (对称) - 优先级: 中
- **文件**: `Game21SymmetryActivity.java`
- **UI特点**:
  - 判断图案是否对称
- **实现**: 新建

#### 18. Laser (激光) - 优先级: 低
- **文件**: `Game22MirrorsActivity.java`
- **UI特点**:
  - 激光反射游戏
  - 放置镜子引导激光
- **实现**: 新建

### 第五批：灵活性类游戏 (3个)

#### 19. Paper Planes (纸飞机) - 优先级: 中
- **文件**: `Game15PaperPlanesActivity.java`
- **UI特点**:
  - 纸飞机方向判断
  - 快速反应
- **实现**: 新建

#### 20. Like Previous? (像之前的吗) - 优先级: 中
- **文件**: `Game16LikePreviousActivity.java`
- **UI特点**:
  - 判断当前元素是否与之前相同
- **实现**: 新建

#### 21. Colors (颜色) - 优先级: 高
- **文件**: `Game20ColorsActivity.java`
- **UI特点**:
  - 颜色文字与颜色不匹配
  - Stroop效应测试
- **实现**: 新建

### 第六批：想象力类游戏 (2个)

#### 22. Count'em All (数一数) - 优先级: 中
- **文件**: `Game5CountAllActivity.java`
- **UI特点**:
  - 快速数数
  - 估算数量
- **实现**: 新建

#### 23. Pyramids (金字塔) - 优先级: 低
- **文件**: `Game18PyramidsActivity.java`
- **UI特点**:
  - 金字塔结构
  - 空间想象
- **实现**: 新建

## 🏗️ 通用组件

### 需要创建的共享组件

#### 1. BaseGameScreen (基础游戏屏幕)
```kotlin
@Composable
fun BaseGameScreen(
    gameInfo: Game,
    score: Int,
    level: Int,
    lives: Int,
    timeRemaining: Int?,
    onNavigateBack: () -> Unit,
    showTimer: Boolean = false,
    timerProgress: Float = 0f,
    content: @Composable () -> Unit
)
```

#### 2. GameGrid组件
- RectangularGrid (矩形网格)
- HexagonalGrid (六边形网格)
- CustomGrid (自定义网格)

#### 3. 游戏状态管理
```kotlin
sealed class GameState {
    object Loading : GameState()
    object ShowingPattern : GameState()
    object PlayerTurn : GameState()
    object GameOver : GameState()
}
```

#### 4. 动画组件
- CellAnimation (单元格动画)
- FadeAnimation (淡入淡出)
- ScaleAnimation (缩放动画)

## 📝 实现检查清单

每个游戏需要实现：

### ViewModel
- [ ] 游戏状态管理
- [ ] 关卡进度 (Progression)
- [ ] 分数计算
- [ ] 生命系统
- [ ] 时间倒计时
- [ ] 答案验证
- [ ] 星星计算

### Screen (UI)
- [ ] 顶部栏 (标题、暂停、生命)
- [ ] 关卡显示
- [ ] 游戏区域
- [ ] 操作按钮
- [ ] 动画效果
- [ ] 状态提示

### 游戏逻辑
- [ ] 图案/题目生成
- [ ] 难度递增
- [ ] 用户输入处理
- [ ] 正确答案判断
- [ ] 游戏结束条件

### 集成
- [ ] 注册到GameFactory
- [ ] 导航集成
- [ ] 结果屏幕集成
- [ ] 数据持久化

## 🎨 UI规范

### 布局结构
```
┌─────────────────────────────────┐
│ 顶部栏                           │
│ [暂停] 游戏名称 [生命❤❤]         │
├─────────────────────────────────┤
│ 关卡 X                           │
├─────────────────────────────────┤
│ [进度条] (在线模式)               │
├─────────────────────────────────┤
│                                 │
│      游戏内容区域                 │
│                                 │
│                                 │
├─────────────────────────────────┤
│ [操作按钮]                       │
└─────────────────────────────────┘
```

### 颜色主题
- Memory: `#2196F3` (蓝色)
- Attention: `#009688` (青色)
- Speed: `#F44336` (红色)
- Problem Solving: `#673AB7` (紫色)
- Flexibility: `#FFC107` (黄色)
- Imagination: `#00BCD4` (青蓝色)

### 字体
- 标题: Roboto Regular 18sp
- 关卡: Roboto Light 14sp
- 内容: Roboto Light 可变

## 📊 进度追踪

### 总体进度
- 游戏总数: 23
- 已完成: 1 (Memory Grid 基础版)
- 进行中: 0
- 待开始: 22

### 按类别进度
- 记忆类: 1/6 (17%)
- 注意力类: 0/2 (0%)
- 速度类: 0/5 (0%)
- 问题解决类: 0/5 (0%)
- 灵活性类: 0/3 (0%)
- 想象力类: 0/2 (0%)

## 🚀 下一步行动

1. ✅ 分析旧项目完成
2. ⏳ 重构Memory Grid以完全匹配旧UI
3. ⏳ 实现Hexagons游戏
4. ⏳ 实现Rotating Grid游戏
5. ⏳ 实现One and Only游戏
6. ⏳ 继续实现其他游戏...

---

**开始日期**: 2026年5月1日  
**预计完成**: 逐步实现  
**当前状态**: 开始实施

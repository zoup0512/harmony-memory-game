# 旧项目分析 - 23个小游戏

## 📋 项目概述

这是一个Java + XML的Android项目重构为Kotlin + Compose的任务。旧项目位于 `src/main/java/com/cube/memorygames/` 目录。

## 🎮 23个游戏列表

根据 `Games.java` 和游戏Activity文件，完整的游戏列表如下：

### 记忆类 (Memory) - 6个游戏
1. **Memory Grid** (记忆网格) - `Game1MemoryGridActivity`
   - ID: "0"
   - 描述: 记住网格中高亮的位置
   
2. **Hexagons** (六边形) - `Game3HexagonsActivity`
   - ID: "2"
   - 描述: 六边形网格记忆游戏

3. **Who's New?** (谁是新的) - `Game4WhoNewActivity`
   - ID: "3"
   - 描述: 识别新出现的元素

4. **Follow the Path** (跟随路径) - `Game6FollowThePathActivity`
   - ID: "5"
   - 描述: 记住并跟随路径

5. **Image Vortex** (图像漩涡) - `Game7ImageVortexActivity`
   - ID: "6"
   - 描述: 图像记忆游戏

6. **Find the Picture** (找图片) - `Game9FindThePictureActivity`
   - ID: "8"
   - 描述: 找到之前看过的图片

### 注意力类 (Attention) - 2个游戏
7. **Rotating Grid** (旋转网格) - `Game2RotatingGridActivity`
   - ID: "1"
   - 描述: 旋转网格中的注意力游戏

8. **Catch Them** (抓住它们) - `Game8CatchThemActivity`
   - ID: "7"
   - 描述: 快速抓住目标

### 速度类 (Speed) - 5个游戏
9. **One and Only** (唯一的) - `Game10OneAndOnlyActivity`
   - ID: "9"
   - 描述: 找出唯一不同的元素

10. **All the Same** (全部相同) - `Game13AllTheSameActivity`
    - ID: "12"
    - 描述: 判断所有元素是否相同

11. **Sort the Digits** (排序数字) - `Game14SortTheDigitsActivity`
    - ID: "13"
    - 描述: 快速排序数字

12. **Find All** (找到全部) - `Game19FindAllActivity`
    - ID: "18"
    - 描述: 找到所有目标

13. **Schultz Tables** (舒尔特表) - `Game23TableActivity`
    - ID: "22"
    - 描述: 按顺序点击数字

### 问题解决类 (Problem Solving) - 5个游戏
14. **Correctly?** (正确吗) - `Game11CorrectlyActivity`
    - ID: "10"
    - 描述: 判断计算是否正确

15. **More, Less** (更多更少) - `Game12MoreLessActivity`
    - ID: "11"
    - 描述: 比较数字大小

16. **248** - `Game17_248Activity`
    - ID: "16"
    - 描述: 2048类游戏

17. **Symmetry** (对称) - `Game21SymmetryActivity`
    - ID: "20"
    - 描述: 判断对称性

18. **Laser** (激光/镜子) - `Game22MirrorsActivity`
    - ID: "21"
    - 描述: 激光反射游戏

### 灵活性类 (Flexibility) - 3个游戏
19. **Paper Planes** (纸飞机) - `Game15PaperPlanesActivity`
    - ID: "14"
    - 描述: 纸飞机方向游戏

20. **Like Previous?** (像之前的吗) - `Game16LikePreviousActivity`
    - ID: "15"
    - 描述: 判断是否与之前相同

21. **Colors** (颜色) - `Game20ColorsActivity`
    - ID: "19"
    - 描述: 颜色识别游戏

### 想象力类 (Imagination) - 2个游戏
22. **Count'em All** (数一数) - `Game5CountAllActivity`
    - ID: "4"
    - 描述: 数数游戏

23. **Pyramids** (金字塔) - `Game18PyramidsActivity`
    - ID: "17"
    - 描述: 金字塔游戏

## 🏗️ 旧项目架构

### Activity结构
每个游戏都有独立的Activity：
- 继承自 `AppCompatActivity`
- 使用 `ButterKnife` 进行View绑定
- 包含游戏逻辑、UI更新、广告、内购等

### 核心组件

#### 1. GameInfo (游戏信息)
```java
public class GameInfo {
    private int gameNameRes;      // 游戏名称资源ID
    private String analyticsName; // 分析名称
    private int gameImageRes;     // 游戏图标资源ID
    private int requiredStars;    // 需要的星星数
    private String id;            // 游戏ID
}
```

#### 2. Progression (游戏进度)
- `GameProgression1`, `GameProgression2`, etc.
- 管理关卡难度递增
- 控制网格大小、元素数量等

#### 3. GameGrid (游戏网格)
- `RectangularGrid` - 矩形网格
- `HexView` - 六边形网格
- 自定义View实现游戏界面

#### 4. GameFlowState (游戏流程状态)
```java
- LOADING - 加载中
- SHOWING_PATTERN - 显示图案
- PLAYER_TURN - 玩家回合
- GAME_OVER - 游戏结束
```

### UI组件
- XML布局文件
- 自定义View (GameGrid, TimerView, etc.)
- Dialog (PauseDialog, TimeoutDialog, etc.)

## 📊 游戏通用特性

### 1. 游戏模式
- **Sprint** - 快速游戏模式
- **Workout** - 训练模式
- **Challenge** - 挑战模式
- **Online** - 在线对战模式

### 2. 游戏流程
```
开始游戏 → 显示图案/题目 → 玩家输入 → 验证答案 → 下一关/游戏结束
```

### 3. 评分系统
- 关卡数决定分数
- 星星奖励（根据关卡数）
- 金币奖励

### 4. 生命系统
- 3条生命
- 答错扣除生命
- 生命为0游戏结束

### 5. 时间系统
- 倒计时
- 在线模式有时间限制
- 暂停功能（最多5次）

## 🎨 UI设计特点

### 颜色主题
每个类别有独特的颜色：
- Memory (记忆): 蓝色系
- Attention (注意力): 青色系
- Speed (速度): 红色系
- Problem Solving (问题解决): 紫色系
- Flexibility (灵活性): 黄色系
- Imagination (想象力): 青蓝色系

### 布局结构
```
┌─────────────────────────────┐
│  顶部栏 (标题、分数)          │
├─────────────────────────────┤
│  统计栏 (关卡、生命、时间)    │
├─────────────────────────────┤
│                             │
│      游戏区域 (网格/内容)     │
│                             │
├─────────────────────────────┤
│  操作按钮 (提交、暂停等)      │
└─────────────────────────────┘
```

### 动画效果
- 网格单元格动画
- 淡入淡出效果
- 完成关卡动画

## 📝 重构策略

### 需要保留的功能
1. ✅ 游戏逻辑和规则
2. ✅ UI布局和设计
3. ✅ 动画效果
4. ✅ 评分系统
5. ✅ 关卡进度系统

### 需要现代化的部分
1. Java → Kotlin
2. XML布局 → Jetpack Compose
3. ButterKnife → Compose状态管理
4. AsyncTask → Coroutines
5. 自定义View → Compose组件

### 实现优先级
1. **高优先级** - 核心游戏逻辑
   - Memory Grid (已完成)
   - Hexagons
   - Rotating Grid
   - One and Only
   - Correctly

2. **中优先级** - 其他常见游戏
   - Who's New
   - Catch Them
   - All the Same
   - More Less
   - Colors

3. **低优先级** - 复杂游戏
   - 248
   - Pyramids
   - Laser
   - Schultz Tables

## 🔍 下一步行动

1. 查看每个游戏的Activity代码
2. 理解游戏逻辑和UI
3. 用Compose重新实现
4. 保持原有的游戏体验

---

**状态**: 分析完成，准备开始重构  
**日期**: 2026年5月1日

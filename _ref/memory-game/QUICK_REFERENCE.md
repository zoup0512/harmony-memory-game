# 🚀 快速参考

## 项目状态
✅ **构建成功** | Kotlin + Compose + MVVM | Clean Architecture

---

## 📂 关键文件位置

```
项目根目录/
├── 📄 BUILD_STATUS.md          ← 构建状态报告（从这里开始！）
├── 📄 REFACTORING_GUIDE.md     ← 详细重构指南
├── 📄 PROJECT_SUMMARY.md       ← 项目架构总结
├── 📄 BUILD_INSTRUCTIONS.md    ← 构建说明
├── 📄 FIXES_APPLIED.md         ← 已修复的问题
└── 📄 quick-build.bat          ← 快速构建脚本
```

---

## ⚡ 常用命令

```bash
# 清理 + 构建
./gradlew clean build

# 安装到设备
./gradlew installDebug

# 运行测试
./gradlew test

# 查看任务
./gradlew tasks
```

---

## 🏗️ 项目结构速查

```
app/src/main/kotlin/com/memory/brain/training/games/
│
├── 📦 domain/              # 业务逻辑层
│   ├── model/             # 领域模型
│   ├── repository/        # Repository 接口
│   └── usecase/           # 用例
│
├── 📦 data/               # 数据层
│   ├── local/database/    # Room 数据库
│   ├── repository/        # Repository 实现
│   └── mapper/            # 数据映射
│
├── 📦 presentation/       # UI 层
│   ├── screens/           # 各个屏幕
│   ├── components/        # 可复用组件
│   ├── navigation/        # 导航
│   └── theme/             # 主题
│
└── 📦 di/                 # 依赖注入
```

---

## 🎮 游戏列表

### 记忆类 (6个)
1. Memory Grid
2. Hexagons
3. Who's New?
4. Follow the Path
5. Image Vortex
6. Find the Picture

### 注意力类 (2个)
7. Rotating Grid
8. Catch Them

### 速度类 (5个)
9. One and Only
10. All the Same
11. Sort the Digits
12. Find All
13. Schultz Tables

### 问题解决类 (5个)
14. Correctly?
15. More, Less
16. 248
17. Symmetry
18. Laser

### 想象力类 (2个)
19. Count'em All
20. Pyramids

### 灵活性类 (3个)
21. Paper Planes
22. Like Previous?
23. Colors

---

## 🔧 技术栈

| 层级 | 技术 |
|------|------|
| 语言 | Kotlin |
| UI | Jetpack Compose |
| 架构 | MVVM + Clean Architecture |
| DI | Hilt |
| 数据库 | Room |
| 网络 | Retrofit + OkHttp |
| 异步 | Coroutines + Flow |
| 导航 | Navigation Compose |

---

## 📋 待办事项

### 高优先级
- [ ] 从旧项目复制资源文件（drawable, mipmap, assets）
- [ ] 配置 Firebase（如需要）
- [ ] 更新 Facebook App ID
- [ ] 实现游戏数据提供者

### 中优先级
- [ ] 实现第一个游戏（Memory Grid）
- [ ] 实现用户认证
- [ ] 实现金币系统
- [ ] 实现星星系统

### 低优先级
- [ ] 迁移其他 22 个游戏
- [ ] 实现在线对战
- [ ] 实现成就系统
- [ ] 集成广告

---

## 🆘 遇到问题？

1. **构建失败** → 查看 BUILD_INSTRUCTIONS.md
2. **架构疑问** → 查看 REFACTORING_GUIDE.md
3. **项目概览** → 查看 PROJECT_SUMMARY.md
4. **已知问题** → 查看 FIXES_APPLIED.md

---

## 💡 开发提示

### Compose 最佳实践
```kotlin
// ✅ 好的做法
@Composable
fun GameCard(game: GameInfo, onClick: () -> Unit) {
    // 无状态组件
}

// ❌ 避免
@Composable
fun GameCard(game: GameInfo) {
    var clicked by remember { mutableStateOf(false) }
    // 组件内部管理状态
}
```

### ViewModel 模式
```kotlin
@HiltViewModel
class GameViewModel @Inject constructor(
    private val useCase: GetGameUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    // 业务逻辑
}
```

### Repository 模式
```kotlin
class GameRepositoryImpl @Inject constructor(
    private val dao: GameDao,
    private val api: GameApi
) : GameRepository {
    
    override fun getGames(): Flow<List<Game>> {
        return dao.getGames().map { it.map { entity -> entity.toModel() } }
    }
}
```

---

## 🎯 下一步

1. **打开 Android Studio**
2. **同步 Gradle** (File → Sync Project)
3. **复制资源文件**
4. **开始开发！**

---

**快速链接**:
- [构建状态](BUILD_STATUS.md)
- [重构指南](REFACTORING_GUIDE.md)
- [项目总结](PROJECT_SUMMARY.md)

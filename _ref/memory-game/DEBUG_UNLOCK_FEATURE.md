# Debug 模式游戏解锁功能

## 功能说明

在 **Debug 模式**下，所有游戏将自动解锁，无需满足任何条件即可游玩所有23个游戏。

这个功能方便开发和测试时快速访问所有游戏，无需逐个解锁。

---

## 实现方式

### 1. BuildConfig 配置

在 `app/build.gradle.kts` 中添加了 `DEBUG_UNLOCK_ALL_GAMES` 标志：

```kotlin
buildTypes {
    debug {
        isDebuggable = true
        buildConfigField("boolean", "DEBUG_UNLOCK_ALL_GAMES", "true")
    }
    release {
        isMinifyEnabled = false
        proguardFiles(...)
        buildConfigField("boolean", "DEBUG_UNLOCK_ALL_GAMES", "false")
    }
}

buildFeatures {
    compose = true
    buildConfig = true  // 启用 BuildConfig
}
```

### 2. GameDataProvider 修改

在 `GameDataProvider.kt` 中，`getAllGames()` 函数会根据 BuildConfig 标志决定是否解锁所有游戏：

```kotlin
fun getAllGames(): List<Game> {
    val games = listOf(
        // ... 所有游戏定义
    )
    
    // Debug模式下解锁所有游戏
    return if (BuildConfig.DEBUG_UNLOCK_ALL_GAMES) {
        games.map { it.copy(isLocked = false) }
    } else {
        games
    }
}
```

---

## 使用方法

### Debug 模式（开发测试）

1. 在 Android Studio 中选择 **debug** 构建变体
2. 运行应用
3. **所有游戏自动解锁** ✅

### Release 模式（正式发布）

1. 在 Android Studio 中选择 **release** 构建变体
2. 构建 APK/AAB
3. **游戏按正常规则锁定/解锁** 🔒

---

## 切换构建变体

在 Android Studio 中：

1. 打开 **Build Variants** 面板
   - 菜单: `View` → `Tool Windows` → `Build Variants`
   - 或使用快捷键: `Alt + 7` (Windows/Linux) / `⌘ + 7` (Mac)

2. 选择构建变体：
   - **debug** - 所有游戏解锁
   - **release** - 正常游戏规则

---

## 技术细节

### BuildConfig 字段

- **类型**: `boolean`
- **名称**: `DEBUG_UNLOCK_ALL_GAMES`
- **Debug 值**: `true`
- **Release 值**: `false`

### 代码位置

1. **配置文件**: `app/build.gradle.kts`
   - 定义 BuildConfig 字段
   - 启用 buildConfig 功能

2. **数据提供者**: `app/src/main/kotlin/com/memory/brain/training/games/data/GameDataProvider.kt`
   - 导入 `BuildConfig`
   - 在 `getAllGames()` 中检查标志
   - 使用 `map { it.copy(isLocked = false) }` 解锁所有游戏

---

## 优势

### 1. 开发效率
- 无需手动解锁每个游戏
- 快速测试所有游戏功能
- 节省开发时间

### 2. 测试便利
- 可以立即测试任何游戏
- 无需完成前置条件
- 方便 QA 测试

### 3. 安全性
- Release 版本自动恢复正常规则
- 不会影响正式发布版本
- 用户体验不受影响

### 4. 灵活性
- 通过构建变体轻松切换
- 无需修改代码
- 配置简单明了

---

## 示例场景

### 场景 1: 开发新游戏
```
开发者想测试 Game 21 (Laser)
→ 使用 debug 构建
→ 直接点击 Laser 游戏
→ 立即开始测试 ✅
```

### 场景 2: 测试游戏平衡
```
QA 想测试所有游戏的难度
→ 使用 debug 构建
→ 所有游戏可访问
→ 逐个测试所有23个游戏 ✅
```

### 场景 3: 演示应用
```
向客户展示所有游戏
→ 使用 debug 构建
→ 展示所有23个游戏
→ 无需解锁流程 ✅
```

### 场景 4: 正式发布
```
准备发布到 Google Play
→ 使用 release 构建
→ 游戏按规则锁定
→ 用户需要逐步解锁 🔒
```

---

## 验证方法

### 验证 Debug 模式

1. 切换到 debug 构建变体
2. 运行应用
3. 进入游戏列表
4. 检查所有游戏是否显示为解锁状态
5. 尝试点击任意游戏，应该可以直接进入

### 验证 Release 模式

1. 切换到 release 构建变体
2. 构建应用
3. 安装到设备
4. 检查游戏是否按规则锁定
5. 验证解锁机制是否正常工作

---

## 注意事项

### 1. 构建变体
- 确保在开发时使用 **debug** 变体
- 发布前切换到 **release** 变体
- 不要将 debug APK 发布到商店

### 2. 代码审查
- 确认 BuildConfig 字段配置正确
- 检查 debug 和 release 的值是否正确
- 验证 GameDataProvider 逻辑正确

### 3. 测试
- 测试两种构建变体
- 确保 debug 模式所有游戏解锁
- 确保 release 模式游戏正常锁定

---

## 扩展功能

如果需要更多 debug 功能，可以添加更多 BuildConfig 字段：

```kotlin
buildTypes {
    debug {
        buildConfigField("boolean", "DEBUG_UNLOCK_ALL_GAMES", "true")
        buildConfigField("boolean", "DEBUG_UNLIMITED_LIVES", "true")
        buildConfigField("boolean", "DEBUG_SKIP_ADS", "true")
        buildConfigField("int", "DEBUG_STARTING_COINS", "10000")
    }
    release {
        buildConfigField("boolean", "DEBUG_UNLOCK_ALL_GAMES", "false")
        buildConfigField("boolean", "DEBUG_UNLIMITED_LIVES", "false")
        buildConfigField("boolean", "DEBUG_SKIP_ADS", "false")
        buildConfigField("int", "DEBUG_STARTING_COINS", "0")
    }
}
```

---

## 总结

✅ **实现完成**
- Debug 模式下所有游戏自动解锁
- Release 模式下游戏正常锁定
- 通过 BuildConfig 控制
- 无需修改游戏逻辑

✅ **使用简单**
- 切换构建变体即可
- 无需额外配置
- 开发测试更高效

✅ **安全可靠**
- Release 版本不受影响
- 用户体验保持一致
- 代码清晰易维护

---

**实现日期**: 2026-05-04  
**功能状态**: ✅ 完成  
**测试状态**: 待验证


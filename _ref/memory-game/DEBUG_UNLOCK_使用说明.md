# Debug 模式游戏解锁 - 快速使用指南

## 🎮 功能说明

在 **Debug 模式**下运行应用时，所有23个游戏将自动解锁，无需任何条件即可游玩。

---

## 🚀 如何使用

### 方法 1: Android Studio 中切换构建变体

1. 打开 **Build Variants** 面板
   - 菜单: `View` → `Tool Windows` → `Build Variants`
   - 或快捷键: `Alt + 7` (Windows/Linux) / `⌘ + 7` (Mac)

2. 在 **Active Build Variant** 列中选择：
   - **debug** ← 选择这个，所有游戏解锁 ✅
   - release ← 正常游戏规则

3. 点击 **Run** 按钮运行应用

### 方法 2: 命令行构建

```bash
# Debug 版本（所有游戏解锁）
./gradlew assembleDebug

# Release 版本（正常规则）
./gradlew assembleRelease
```

---

## ✅ 验证是否生效

运行 debug 版本后：

1. 打开应用
2. 进入游戏列表（Sprint 模式）
3. 检查所有游戏是否都可以点击
4. 尝试点击任意游戏，应该可以直接进入

**预期结果**: 所有23个游戏都显示为可玩状态，没有锁定图标 🔓

---

## 📋 两种模式对比

| 特性 | Debug 模式 | Release 模式 |
|------|-----------|-------------|
| 游戏解锁 | ✅ 全部解锁 | 🔒 按规则锁定 |
| 用途 | 开发测试 | 正式发布 |
| 构建变体 | debug | release |
| 适用场景 | 开发、测试、演示 | 发布到商店 |

---

## 🔧 技术实现

### 修改的文件

1. **app/build.gradle.kts**
   - 添加 `DEBUG_UNLOCK_ALL_GAMES` BuildConfig 字段
   - 启用 `buildConfig` 功能

2. **GameDataProvider.kt**
   - 导入 `BuildConfig`
   - 在 `getAllGames()` 中检查 debug 标志
   - Debug 模式下将所有游戏的 `isLocked` 设为 `false`

### 核心代码

```kotlin
// GameDataProvider.kt
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

## ⚠️ 注意事项

1. **不要发布 debug 版本到商店**
   - Debug 版本仅用于开发测试
   - 发布时必须使用 release 版本

2. **切换构建变体后需要重新构建**
   - 切换后点击 **Run** 或 **Build**
   - 确保使用正确的版本

3. **Release 版本不受影响**
   - Release 版本游戏仍按正常规则锁定
   - 用户体验保持一致

---

## 🎯 使用场景

### ✅ 适合使用 Debug 模式的场景

- 开发新游戏功能
- 测试所有游戏
- 向客户演示完整功能
- QA 测试
- 调试特定游戏

### ❌ 不适合使用 Debug 模式的场景

- 发布到 Google Play
- 分发给最终用户
- 性能测试（应使用 release）
- 正式版本构建

---

## 📝 快速检查清单

构建前检查：

- [ ] 确认当前构建变体
- [ ] Debug 模式：用于开发测试
- [ ] Release 模式：用于正式发布
- [ ] 运行应用验证游戏解锁状态

发布前检查：

- [ ] 切换到 **release** 构建变体
- [ ] 验证游戏按规则锁定
- [ ] 测试解锁机制
- [ ] 构建签名版本

---

## 🆘 常见问题

### Q: 为什么游戏还是锁定的？
A: 检查是否使用了 **debug** 构建变体，切换后需要重新运行应用。

### Q: Release 版本会解锁所有游戏吗？
A: 不会，release 版本游戏按正常规则锁定。

### Q: 如何确认当前是哪个构建变体？
A: 查看 Android Studio 底部状态栏或 Build Variants 面板。

### Q: 可以在 release 版本中也解锁所有游戏吗？
A: 不建议，这会影响用户体验。如需测试，使用 debug 版本。

---

## 📚 相关文档

- `DEBUG_UNLOCK_FEATURE.md` - 详细技术文档
- `app/build.gradle.kts` - 构建配置
- `GameDataProvider.kt` - 游戏数据提供者

---

**实现日期**: 2026-05-04  
**功能状态**: ✅ 完成  
**使用难度**: ⭐ 简单（只需切换构建变体）


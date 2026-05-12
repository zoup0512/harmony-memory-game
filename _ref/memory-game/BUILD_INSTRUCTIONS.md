# 构建说明

## 问题已解决 ✅

之前的构建错误是因为项目中同时存在旧的 `build.gradle` 和新的 `build.gradle.kts` 文件。

### 已完成的修复：

1. ✅ 将旧的 `build.gradle` 重命名为 `build.gradle.old`（作为备份）
2. ✅ 更新 `settings.gradle.kts`，将 `FAIL_ON_PROJECT_REPOS` 改为 `PREFER_SETTINGS`
3. ✅ 添加了所有必要的仓库到 settings.gradle.kts
4. ✅ 创建了基础的 AndroidManifest.xml
5. ✅ 创建了必要的资源文件（strings.xml, colors.xml, themes.xml）
6. ✅ 更新了 gradle.properties 以支持 Kotlin

## 构建步骤

### 1. 同步项目

在 Android Studio 中：
- 点击 **File → Sync Project with Gradle Files**
- 或点击工具栏的 "Sync Now" 按钮

### 2. 清理构建

```bash
./gradlew clean
```

### 3. 构建项目

```bash
./gradlew build
```

### 4. 运行应用

```bash
./gradlew installDebug
```

或在 Android Studio 中点击运行按钮。

## 需要的额外配置

### 1. 复制资源文件

从旧项目复制以下资源到新项目：

```
src/main/res/drawable/     → app/src/main/res/drawable/
src/main/res/mipmap/       → app/src/main/res/mipmap/
src/main/assets/           → app/src/main/assets/
```

### 2. 配置 Firebase

如果使用 Firebase，需要：
1. 将 `google-services.json` 放到 `app/` 目录
2. 在 Firebase Console 中配置应用

### 3. 配置 Facebook SDK

在 `app/src/main/res/values/strings.xml` 中：
```xml
<string name="facebook_app_id">YOUR_ACTUAL_FACEBOOK_APP_ID</string>
```

### 4. 配置签名

创建 `keystore.properties` 文件（不要提交到 Git）：
```properties
storePassword=YOUR_STORE_PASSWORD
keyPassword=YOUR_KEY_PASSWORD
keyAlias=YOUR_KEY_ALIAS
storeFile=YOUR_KEYSTORE_FILE_PATH
```

## 常见问题

### Q: 编译错误 "Cannot find symbol"
**A:** 确保所有 Kotlin 文件的包名正确，并且已经同步 Gradle。

### Q: 资源文件找不到
**A:** 从旧项目复制所有 drawable 和 mipmap 资源文件。

### Q: Hilt 注入失败
**A:** 确保：
1. Application 类添加了 `@HiltAndroidApp` 注解
2. Activity 添加了 `@AndroidEntryPoint` 注解
3. ViewModel 添加了 `@HiltViewModel` 注解

### Q: Compose 预览不显示
**A:** 确保：
1. 使用 `@Preview` 注解
2. Composable 函数没有参数或提供了默认值
3. Android Studio 已更新到最新版本

## 项目结构

```
memory-game/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── kotlin/com/memory/brain/training/games/
│   │       │   ├── data/           # 数据层
│   │       │   ├── domain/         # 领域层
│   │       │   ├── presentation/   # UI层
│   │       │   ├── di/             # 依赖注入
│   │       │   └── MemoryGamesApplication.kt
│   │       ├── res/                # 资源文件
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## 下一步

1. **复制资源文件** - 从旧项目复制所有图片和资源
2. **实现游戏数据** - 创建 23 个游戏的配置
3. **测试构建** - 确保项目可以成功编译
4. **开始迁移** - 按照 REFACTORING_GUIDE.md 开始迁移功能

## 有用的 Gradle 命令

```bash
# 清理构建
./gradlew clean

# 构建 Debug APK
./gradlew assembleDebug

# 构建 Release APK
./gradlew assembleRelease

# 运行测试
./gradlew test

# 查看依赖树
./gradlew app:dependencies

# 检查依赖更新
./gradlew dependencyUpdates
```

## 性能优化

在 `gradle.properties` 中已启用：
- ✅ 并行构建
- ✅ 构建缓存
- ✅ Kotlin 增量编译

## 支持

如果遇到问题：
1. 查看 `REFACTORING_GUIDE.md` 了解架构详情
2. 查看 `PROJECT_SUMMARY.md` 了解项目概览
3. 检查 Android Studio 的 Build 输出
4. 运行 `./gradlew build --stacktrace` 查看详细错误

---

**状态**: 项目配置已完成，可以开始构建 ✅

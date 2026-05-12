# 应用崩溃修复

## 🐛 问题列表

### 问题 1: AdMob SDK 初始化失败 ✅ 已修复
**错误信息**:
```
The Google Mobile Ads SDK was initialized incorrectly.
AdMob publishers should follow the instructions here:
https://googlemobileadssdk.page.link/admob-android-update-manifest
```

**解决方案**: 在 AndroidManifest.xml 中添加了 AdMob App ID

### 问题 2: Facebook SDK 缺少 Client Token ✅ 已修复
**错误信息**:
```
A valid Facebook app client token must be set in the AndroidManifest.xml 
or set by calling FacebookSdk.setClientToken before initializing the sdk.
```

**解决方案**: 
1. 在 AndroidManifest.xml 中添加了 Facebook Client Token
2. 移除了手动初始化 Facebook SDK 的代码（SDK 会自动初始化）

### 问题 3: Google Play Services 版本不匹配 ✅ 已修复
**错误信息**:
```
The meta-data tag in your app's AndroidManifest.xml does not have the right value.
Expected 12451000 but found 9452000.
```

**解决方案**: 更新 integers.xml 中的版本号从 9452000 到 12451000

---

## ✅ 已应用的修复

### 1. AndroidManifest.xml

添加了 Facebook Client Token：
```xml
<!-- Facebook Client Token - Required by Facebook SDK -->
<meta-data
    android:name="com.facebook.sdk.ClientToken"
    android:value="fb_test_client_token_placeholder" />
```

### 2. integers.xml

更新了 Google Play Services 版本：
```xml
<!-- 修改前 -->
<integer name="google_play_services_version">9452000</integer>

<!-- 修改后 -->
<integer name="google_play_services_version">12451000</integer>
```

### 3. MemoryGamesApplication.kt

移除了手动初始化 Facebook SDK：
```kotlin
// 修改前
FacebookSdk.sdkInitialize(applicationContext)

// 修改后
// Facebook SDK initializes automatically via ContentProvider
// No need to call FacebookSdk.sdkInitialize() manually
```

---

## 📝 修改的文件

1. `app/src/main/AndroidManifest.xml` - 添加 Facebook Client Token
2. `app/src/main/res/values/integers.xml` - 更新 Google Play Services 版本
3. `app/src/main/kotlin/com/memory/brain/training/games/MemoryGamesApplication.kt` - 移除手动初始化

---

## 🔄 如何应用修复

所有修复已经应用到代码中。重新构建应用即可：

```bash
./gradlew clean assembleDebug
./gradlew installDebug
```

或者在 Android Studio 中：
1. Build → Clean Project
2. Build → Rebuild Project
3. Run → Run 'app'

---

## ⚠️ 重要提示

### 测试凭据 vs 真实凭据

当前使用的都是**测试/占位符凭据**，用于开发和测试：

#### AdMob App ID
- **当前**: `ca-app-pub-3940256099942544~3347511713` (Google 测试 ID)
- **获取真实 ID**: [AdMob Console](https://apps.admob.com/)

#### Facebook Client Token
- **当前**: `fb_test_client_token_placeholder` (占位符)
- **获取真实 Token**: [Facebook Developers](https://developers.facebook.com/)
  1. 登录 Facebook Developers
  2. 选择您的应用
  3. 设置 → 高级 → 客户端令牌

#### Facebook App ID
- **当前**: 在 `strings.xml` 中定义
- **需要更新**: 替换为您的真实 Facebook App ID

---

## 📋 发布前检查清单

在发布应用到 Google Play 之前，必须完成：

- [ ] 替换 AdMob App ID 为真实 ID
- [ ] 替换 Facebook Client Token 为真实 Token
- [ ] 替换 Facebook App ID 为真实 ID
- [ ] 测试所有广告功能
- [ ] 测试 Facebook 登录功能
- [ ] 移除所有测试/调试代码

---

## 🧪 测试步骤

修复后，测试应用：

1. **构建并安装**
   ```bash
   ./gradlew clean assembleDebug
   ./gradlew installDebug
   ```

2. **启动应用**
   - 应用应该能够正常启动
   - 不应该有崩溃

3. **检查日志**
   ```bash
   adb logcat | grep -E "(MemoryGames|AndroidRuntime|FATAL)"
   ```
   - 不应该有 FATAL EXCEPTION
   - 不应该有 Facebook/AdMob 错误

4. **功能测试**
   - 测试应用基本功能
   - 测试广告显示（如果实现）
   - 测试 Facebook 登录（如果实现）

---

## 🔧 故障排除

### 如果应用仍然崩溃

1. **清理构建**
   ```bash
   ./gradlew clean
   rm -rf app/build
   ./gradlew assembleDebug
   ```

2. **检查依赖版本**
   - 确保所有 Google Play Services 依赖版本一致
   - 确保 Facebook SDK 版本正确

3. **查看完整日志**
   ```bash
   adb logcat > logcat.txt
   ```
   然后查找错误信息

### 如果 Facebook 功能不工作

1. 确保在 Facebook Developers 中正确配置了应用
2. 添加了正确的包名和签名哈希
3. 启用了所需的权限

### 如果广告不显示

1. 确保使用测试设备 ID（开发阶段）
2. 检查网络连接
3. 查看 AdMob 日志

---

## 📚 相关文档

- [AdMob Android 快速入门](https://developers.google.com/admob/android/quick-start)
- [Facebook Android SDK 文档](https://developers.facebook.com/docs/android)
- [Google Play Services 设置](https://developers.google.com/android/guides/setup)

---

## 📊 修复状态

| 问题 | 状态 | 文件 |
|------|------|------|
| AdMob App ID | ✅ 已修复 | AndroidManifest.xml |
| Facebook Client Token | ✅ 已修复 | AndroidManifest.xml |
| Google Play Services 版本 | ✅ 已修复 | integers.xml |
| Facebook SDK 初始化 | ✅ 已修复 | MemoryGamesApplication.kt |

---

**最后更新**: 2026-05-01  
**状态**: ✅ 所有已知崩溃问题已修复  
**测试**: ⏳ 需要重新构建和测试  
**发布前**: ⚠️ 必须替换所有测试凭据为真实凭据

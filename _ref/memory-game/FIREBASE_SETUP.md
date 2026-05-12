# Firebase 配置指南

## 🔥 当前状态

Firebase 功能已**暂时禁用**，以便项目可以正常构建。

## 为什么禁用？

项目需要 `google-services.json` 文件才能使用 Firebase，但该文件：
- 包含敏感信息（API密钥）
- 需要从 Firebase Console 下载
- 不应该提交到 Git

## 🚀 如何启用 Firebase

### 步骤 1: 创建 Firebase 项目

1. 访问 [Firebase Console](https://console.firebase.google.com/)
2. 点击 "添加项目"
3. 输入项目名称：`Memory Brain Training Games`
4. 按照向导完成创建

### 步骤 2: 添加 Android 应用

1. 在 Firebase 项目中，点击 "添加应用" → Android 图标
2. 输入包名：`com.zhoup.android.game.gridmemory`
3. （可选）输入应用昵称和调试签名证书 SHA-1
4. 点击 "注册应用"

### 步骤 3: 下载配置文件

1. 下载 `google-services.json` 文件
2. 将文件放到项目的 `app/` 目录：
   ```
   memory-game/
   └── app/
       └── google-services.json  ← 放在这里
   ```

### 步骤 4: 启用 Firebase 插件

在 `build.gradle.kts` 中取消注释：

```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")  // ← 取消注释
    id("com.google.firebase.crashlytics") // ← 取消注释
    kotlin("kapt")
}
```

在根目录的 `build.gradle.kts` 中也取消注释：

```kotlin
plugins {
    // ...
    id("com.google.gms.google-services") version "4.4.0" apply false  // ← 取消注释
    id("com.google.firebase.crashlytics") version "2.9.9" apply false // ← 取消注释
}
```

### 步骤 5: 启用 Firebase 依赖

在 `app/build.gradle.kts` 中取消注释：

```kotlin
// Firebase
implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
implementation("com.google.firebase:firebase-analytics-ktx")
implementation("com.google.firebase:firebase-crashlytics-ktx")
implementation("com.google.firebase:firebase-messaging-ktx")
implementation("com.google.firebase:firebase-auth-ktx")
```

### 步骤 6: 同步项目

```bash
./gradlew clean build
```

或在 Android Studio 中：
- File → Sync Project with Gradle Files

## 📋 Firebase 功能清单

启用 Firebase 后，你将获得：

- ✅ **Analytics** - 用户行为分析
- ✅ **Crashlytics** - 崩溃报告
- ✅ **Cloud Messaging** - 推送通知
- ✅ **Authentication** - 用户认证（可选）
- ✅ **Firestore** - 云数据库（可选）
- ✅ **Remote Config** - 远程配置（可选）

## 🔒 安全注意事项

### 不要提交 google-services.json

确保 `.gitignore` 包含：

```gitignore
# Google Services
google-services.json
```

### 使用环境变量（生产环境）

对于 CI/CD，使用环境变量或密钥管理：

```bash
# 在 CI 中
echo $GOOGLE_SERVICES_JSON | base64 -d > app/google-services.json
```

## 🧪 测试 Firebase 配置

启用 Firebase 后，测试是否正常工作：

```kotlin
// 在 Application 类中
override fun onCreate() {
    super.onCreate()
    
    // 测试 Firebase
    FirebaseApp.initializeApp(this)
    Firebase.analytics.logEvent("app_open", null)
    
    Timber.d("Firebase initialized successfully")
}
```

## 🆘 常见问题

### Q: 找不到 google-services.json
**A:** 确保文件在 `app/` 目录，不是 `app/src/` 目录。

### Q: 包名不匹配
**A:** 确保 Firebase Console 中的包名与 `build.gradle.kts` 中的 `applicationId` 一致。

### Q: SHA-1 证书错误
**A:** 获取调试证书的 SHA-1：
```bash
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
```

### Q: 构建失败
**A:** 确保：
1. `google-services.json` 在正确位置
2. 所有 Firebase 插件都已启用
3. 网络连接正常（需要下载依赖）

## 📱 推送通知配置

如果需要推送通知，还需要：

1. 在 Firebase Console 中配置 Cloud Messaging
2. 获取服务器密钥
3. 实现 `FirebaseMessagingService`

示例代码已在 `AndroidManifest.xml` 中配置：

```xml
<service
    android:name=".data.service.FirebaseMessagingService"
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```

## 🎯 不使用 Firebase 的替代方案

如果不想使用 Firebase，可以：

1. **Analytics**: 使用 Google Analytics for Firebase 的替代品
   - Mixpanel
   - Amplitude
   - 自建分析系统

2. **Crashlytics**: 使用其他崩溃报告工具
   - Sentry
   - Bugsnag
   - ACRA

3. **Push Notifications**: 使用其他推送服务
   - OneSignal
   - Pusher
   - 自建推送服务

## 📚 相关文档

- [Firebase Android 文档](https://firebase.google.com/docs/android/setup)
- [Firebase Console](https://console.firebase.google.com/)
- [Firebase SDK 版本](https://firebase.google.com/support/release-notes/android)

---

**当前状态**: Firebase 已禁用，项目可以正常构建  
**需要 Firebase?**: 按照上述步骤启用

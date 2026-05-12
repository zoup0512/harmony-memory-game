@echo off
echo ========================================
echo 快速构建和安装脚本
echo ========================================
echo.

echo [1/3] 清理旧的构建文件...
call gradlew clean
if %ERRORLEVEL% NEQ 0 (
    echo 错误: 清理失败
    pause
    exit /b 1
)

echo.
echo [2/3] 构建 Debug APK...
call gradlew assembleDebug
if %ERRORLEVEL% NEQ 0 (
    echo 错误: 构建失败
    pause
    exit /b 1
)

echo.
echo [3/3] 安装到设备...
call gradlew installDebug
if %ERRORLEVEL% NEQ 0 (
    echo 错误: 安装失败
    echo 请确保:
    echo   1. 设备已连接
    echo   2. USB 调试已启用
    echo   3. 已授权此计算机
    pause
    exit /b 1
)

echo.
echo ========================================
echo 构建和安装成功！
echo ========================================
echo.
echo APK 位置: app\build\outputs\apk\debug\app-debug.apk
echo.
echo 现在可以在设备上启动应用了。
echo.
pause

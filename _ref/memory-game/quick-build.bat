@echo off
echo ========================================
echo Memory Brain Training Games - Quick Build
echo ========================================
echo.

echo [1/4] Cleaning project...
call gradlew clean
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Clean failed!
    pause
    exit /b 1
)

echo.
echo [2/4] Syncing dependencies...
call gradlew --refresh-dependencies
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Dependency sync failed!
    pause
    exit /b 1
)

echo.
echo [3/4] Building project...
call gradlew build
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Build failed!
    pause
    exit /b 1
)

echo.
echo [4/4] Build successful!
echo ========================================
echo.
echo You can now:
echo   - Open the project in Android Studio
echo   - Run: gradlew installDebug
echo   - Or click Run in Android Studio
echo.
pause

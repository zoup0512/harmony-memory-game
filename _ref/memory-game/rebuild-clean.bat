@echo off
echo Stopping all Gradle daemons...
call gradlew --stop

echo Waiting for daemons to stop...
timeout /t 3 /nobreak > nul

echo Deleting Gradle caches...
rd /s /q "%USERPROFILE%\.gradle\caches" 2>nul

echo Deleting project build directories...
rd /s /q build 2>nul
rd /s /q app\build 2>nul
rd /s /q .gradle 2>nul

echo Waiting...
timeout /t 2 /nobreak > nul

echo Starting clean build...
call gradlew clean assembleDebug --no-daemon --refresh-dependencies

echo Done!
pause

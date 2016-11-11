@echo off
echo "Starting giiwa ..."

set startup=%~dp0

:checking
wmic process where caption="java.exe" get commandline /value | findstr "%startup%" > NUL  
if ERRORLEVEL 1 goto starting
if ERRORLEVEL 0 goto sleep

goto sleep

:starting
call %~dp0\bin\startup.bat
ping 0.0.0.0 -n 3 >NUL

:sleep
ping 0.0.0.0 -n 1 >NUL

goto checking
@echo off

echo Running Java class file...

java MineSweeper

if %errorlevel% == 0 (
  echo Execution was successful.
) else (
  echo Execution failed with error code %errorlevel%.
)

pause

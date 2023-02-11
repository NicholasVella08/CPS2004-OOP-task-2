@echo off

echo Compiling Java source file...

javac MineSweeper.java

if %errorlevel% == 0 (
  echo Compilation was successful.
) else (
  echo Compilation failed with error code %errorlevel%.
)

pause

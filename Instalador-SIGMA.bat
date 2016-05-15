@echo off
color 0F
echo ------------------------------
echo       Instalando o SIGMA
echo ------------------------------
md C:\Users\%username%\Documents\SIGMA
md C:\Users\%username%\Documents\SIGMA\Relatorios
md C:\Users\%username%\Documents\SIGMA\App
xcopy Java-code\*.* C:\Users\%username%\Documents\SIGMA\App /s
xcopy sigma-icon.ico C:\Users\%username%\Documents\SIGMA
xcopy Manual.pdf C:\Users\%username%\Documents\SIGMA
xcopy NR.exe C:\Users\%username%\Documents\SIGMA
xcopy ShortcutCreator.bat C:\Users\%username%\Documents\SIGMA
C:
cd C:\Users\%username%\Documents\SIGMA
ShortcutCreator.bat
del /q C:\Users\%username%\Documents\SIGMA\ShortcutCreator.bat

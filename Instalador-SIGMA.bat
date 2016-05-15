@echo off
color 0F
echo ------------------------------
echo       Instalando o SIGMA
echo ------------------------------
md C:\Users\%username%\Documents\SIGMA
md C:\Users\%username%\Documents\SIGMA\Relatorios
md C:\Users\%username%\Documents\SIGMA\App
copy Java-code\*.* C:\Users\%username%\Documents\SIGMA\App
copy sigma-icon.ico C:\Users\%username%\Documents\SIGMA
copy Manual.pdf C:\Users\%username%\Documents\SIGMA
copy NR.exe C:\Users\%username%\Documents\SIGMA
copy ShortcutCreator.bat C:\Users\%username%\Documents\SIGMA
C:
cd C:\Users\%username%\Documents\SIGMA
ShortcutCreator.bat
del C:\Users\%username%\Documents\SIGMA\ShortcutCreator.bat

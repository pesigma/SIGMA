@echo off

set SCRIPT="%TEMP%\%RANDOM%-%RANDOM%-%RANDOM%-%RANDOM%.vbs"

echo Set WshShell = WScript.CreateObject("WScript.Shell") > %SCRIPT%
echo Set lnk = WshShell.CreateShortcut("C:\Users\%USERNAME%\Desktop\MyShortcut.LNK") >> %SCRIPT%
echo lnk.TargetPath = "%windir%\notepad.exe" >> %SCRIPT%
echo lnk.Description = "MyProgram" >> %SCRIPT%
echo lnk.IconLocation = "C:\Users\%USERNAME%\Documents\SIGMA\sigma-icon.ico" >> %SCRIPT%
echo lnk.WindowStyle = "1" >> %SCRIPT%
echo lnk.Save >> %SCRIPT%

cscript %SCRIPT%
del %SCRIPT%
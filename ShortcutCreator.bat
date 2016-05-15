@echo off

set SCRIPT="%TEMP%\%RANDOM%-%RANDOM%-%RANDOM%-%RANDOM%.vbs"

echo Set WshShell = WScript.CreateObject("WScript.Shell") > %SCRIPT%
echo Set lnk = WshShell.CreateShortcut("C:\Users\%USERNAME%\Desktop\SIGMA.lnk") >> %SCRIPT%
echo lnk.TargetPath = "C:\Users\%USERNAME%\Documents\SIGMA\" >> %SCRIPT%
echo lnk.Description = "Inicia o SIGMA" >> %SCRIPT%
echo lnk.IconLocation = "C:\Users\%USERNAME%\Documents\SIGMA\sigma-icon.ico" >> %SCRIPT%
echo lnk.WindowStyle = "1" >> %SCRIPT%
echo lnk.Save >> %SCRIPT%

cscript %SCRIPT%
del %SCRIPT%
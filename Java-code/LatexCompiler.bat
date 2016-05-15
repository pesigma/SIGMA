@echo off
color 0F
move *.tex C:\Users\%username%\Documents\SIGMA\Relatorios
C:
cd C:\Users\%username%\Documents\SIGMA
echo %date%>N.txt
NR.exe
set /p R=<K.txt
DEL K.txt
DEL N.txt
cd C:\Users\%username%\Documents\SIGMA\Relatorios
pdflatex %R%
bibtex %R%
pdflatex %R%
pdflatex %R%
DEL *.log
DEL *.aux
DEL *.out
DEL *.blg
DEL *.bbl
DEL *.tex
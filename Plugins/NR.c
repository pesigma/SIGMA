#include <conio.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

FILE *On;
FILE *Off;
char REL[50], CON[50], DATA[15], ND[12];

int DATE ()
{
    ND[0]=DATA[0];
    ND[1]=DATA[1];
    strcat (ND,"-");
    ND[3]=DATA[3];
    ND[4]=DATA[4];
    strcat (ND,"-");
    ND[6]=DATA[6];
    ND[7]=DATA[7];
    ND[8]=DATA[8];
    ND[9]=DATA[9];
}

int NOME ()
{
    On=fopen("N.txt","r");
    fscanf (On, "%s", &DATA);
    strcpy (REL,"Relatorio(");
    DATE();
    strcat (REL,ND);
    strcat (REL,").tex");
    fclose (On);
    return 0;
}


int main ()
{
    NOME();
    Off=fopen("K.txt","w");
    fprintf (Off,"%s\n", REL);
    return (0);
}

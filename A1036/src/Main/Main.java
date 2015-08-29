package Main;

import java.util.Formatter;
import static java.lang.Double.NaN;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        double A,B,C; //Acumula o valor de entrada do Usuário
        Scanner input=new Scanner(System.in);
        A=input.nextDouble();
        B=input.nextDouble();
        C=input.nextDouble();
        if (A!=0 && (B*B)>=(4*A*C)){
            double R1=(((-B)+Math.sqrt((B*B)-(4*A*C)))/(2*A));
            double R2=(((-B)-Math.sqrt((B*B)-(4*A*C)))/(2*A));
            System.out.printf("R1 = %.5f\n", R1);
            System.out.printf("R1 = %.5f\n", R2);
	}
        else{
            System.out.printf("Impossivel calcular\n");
        }
        return;
    }
}

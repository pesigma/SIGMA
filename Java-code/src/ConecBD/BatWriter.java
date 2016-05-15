/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConecBD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juliano
 */
public class BatWriter {     
    public BatWriter(){
        writeBat ();
    }
    
    public boolean writeBat (){
        final File file = new File("Exemplo.bat");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            return false;
        } //Finalização de criação de arquivo
        
        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        } catch (IOException ex) {
            Logger.getLogger(BatWriter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }//Finalização de inicialização de escritor

        writer.println("@echo off");
        writer.println("cd \"C:\\Program Files\\Whatever\"");
        writer.println("This is just a crazy example!");
        writer.close(); //Close já dá writer.flush(); mas em alguns casos pode ser recomendado fazer separado.
        //Finalização de criação de comandos
        
        /*try {
            Process runBat = Runtime.getRuntime().exec("cmd /c " + file);
            runBat.waitFor();
            file.delete();
        } catch (IOException | InterruptedException ex) {
            return false;
        } //Finalização de execução de arquivo*/
        
        return true;
    }
}

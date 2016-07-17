/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils.console;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class PaneHandler extends StreamHandler {

    /*private void init(){
        setFormatter(new SimpleFormatter());
        try{
            setEncoding("UTF-8");
        } catch (IOException ex){
            try{
                setEncoding(null);
            } catch (IOException exe){
                exe.printStackTrace();
            }
        }
    }*/
    
    /**
     * Constrói um Handler usando a stream direcionada
     * à JTextPanes, com um {@link java.util.logging.SimpleFormatter}.
     * @param outStream Stream já instanciada.
     */
    public PaneHandler(BufferedPaneOutputStream outStream) {
        this(outStream, new SimpleFormatter());
    }

    /**
     * Constrói um Handler usando a stream direcionada
     * à JTextPanes.
     * @param outStream Stream já instanciada.
     * @param formatter Formatador a ser usado nos "LogRecords".
     */
    public PaneHandler(BufferedPaneOutputStream outStream, Formatter formatter) {
        super(outStream, formatter);
    }
    
    @Override
    public synchronized void close() throws SecurityException {
        flush();
    }

    @Override
    public synchronized void publish(LogRecord record) {
        super.publish(record); 
        flush();
    }
    
}

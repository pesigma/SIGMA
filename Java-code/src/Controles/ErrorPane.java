/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import javax.swing.JOptionPane;

/**
 * Concatena strings de erro, códigos, e etc.
 * Exibindo como warnings ou erros.
 * @author Juliano Felipe
 */
public class ErrorPane {
    
    /**
    * Exibe Option Pane de erro, conforme parâmetros.
    * @author Juliano Felipe
    * 
    * @param title - Título do pane
    * @param message - Mensagem legível para o usuário
    * @param code - Código de erro
    * @param trace - Tipo e "StackTrace" do erro.
    */
    public void Error (String title, String message, String code, String trace){
        JOptionPane.showMessageDialog(null, message + "\nErro código: " + code + "\n" + trace, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
    * Exibe Option Pane de warning, conforme parâmetros.
    * @author Juliano Felipe
    * 
    * @param title - Título do pane
    * @param message - Mensagem legível para o usuário
    * @param code - Código de warning
    */
    public void Warning (String title, String message, String code){
        JOptionPane.showMessageDialog(null, message + "\nErro código: " + code, title, JOptionPane.ERROR_MESSAGE);
    }
    
}

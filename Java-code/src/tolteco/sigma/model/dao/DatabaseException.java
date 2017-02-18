/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

/**
 * Exception para normalizar o tratamento
 * dentro do Sistema (Facilitando a propagação
 * até a view).
 * 
 * @author Juliano Felipe da Silva
 */
public class DatabaseException extends Exception{

    public DatabaseException() {
        super("Erro no Banco de dados");
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    } 
    
    
}

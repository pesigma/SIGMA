/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

import tolteco.sigma.model.dao.jdbc.JDBCDAOFactory;

/**
 *
 * @author Juliano Felipe da Silva
 */
public abstract class DAOFactory {
    public static final int SQLITE = 1;
    public static final int JDBC = 2;
    public static final int ARQUIVO = 3;
    
    public static DAOFactory getDAOFactory(int opcao) {
        switch (opcao ) {
            case SQLITE : throw new UnsupportedOperationException("Não implementado");
            case JDBC : return new JDBCDAOFactory();
            case ARQUIVO: throw new UnsupportedOperationException("Não implementado");
            default: return null;
        }
    }
    
    public abstract ClienteDAO getClienteDAO();
    
    public abstract FinancaDAO getFinancaDAO();
    
    public abstract ServicoDAO getServicoDAO();
    
    public abstract UsuarioDAO getUsuarioDAO();
}

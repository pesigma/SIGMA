/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.ClienteDAO;
import tolteco.sigma.model.dao.FinancaDAO;
import tolteco.sigma.model.dao.ServicoDAO;


/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCDAOFactory extends DAOFactory {
    private static ClienteDAO clienteDAO;
    
    @Override
    public ClienteDAO getClienteDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*if (clienteDAO == null) {
            clienteDAO = new JDBCClienteDAO();
        }
        return clienteDAO;*/
    }

    @Override
    public FinancaDAO getFinancaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServicoDAO getServicoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

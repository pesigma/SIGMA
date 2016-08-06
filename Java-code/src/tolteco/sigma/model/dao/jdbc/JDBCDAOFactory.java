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
import tolteco.sigma.model.dao.UsuarioDAO;
import tolteco.sigma.model.dao.VersionDAO;


/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCDAOFactory extends DAOFactory {
    private static ClienteDAO clienteDAO;
    private static FinancaDAO financaDAO;
    private static ServicoDAO servicoDAO;
    private static UsuarioDAO usuarioDAO; 
    private static VersionDAO versionDAO;
    
    @Override
    public ClienteDAO getClienteDAO() {
        if (clienteDAO == null) {
            clienteDAO = new JDBCClienteDAO();
        }
        return clienteDAO;
    }

    @Override
    public FinancaDAO getFinancaDAO() {
        if (financaDAO == null) {
            financaDAO = new JDBCFinancaDAO();
        }
        return financaDAO;
    }

    @Override
    public ServicoDAO getServicoDAO() {
        if (servicoDAO == null) {
            servicoDAO = new JDBCServicoDAO();
        }
        return servicoDAO;
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        if (usuarioDAO == null) {
            usuarioDAO = new JDBCUsuarioDAO();
        }
        return usuarioDAO;
    }

    @Override
    public VersionDAO getVersionDAO() {
        if (versionDAO == null) {
            versionDAO = new JDBCVersionDAO();
        }
        return versionDAO;
    }
    
}

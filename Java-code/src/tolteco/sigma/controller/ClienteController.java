/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import tolteco.sigma.model.dao.ClienteDAO;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.entidades.Cliente;

/**
 *
 * @author Juliano Felipe
 */
public class ClienteController extends GenericController<Cliente>{
    
    private final ClienteDAO clienteDAO;
    
    public ClienteController(DAOFactory dao) {
        super(dao);
        clienteDAO = dao.getClienteDAO();
    }

    @Override
    public boolean insert(Cliente t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Cliente t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Cliente t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> selectAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente search(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> select(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

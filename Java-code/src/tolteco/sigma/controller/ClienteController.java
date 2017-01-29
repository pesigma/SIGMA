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
        return clienteDAO.insert(t);
    }

    @Override
    public boolean remove(Cliente t) throws DatabaseException {
        return clienteDAO.remove(t);
    }

    @Override
    public boolean update(Cliente t) throws DatabaseException {
        return clienteDAO.update(t);
    }

    @Override
    public List<Cliente> selectAll() throws DatabaseException {
        return clienteDAO.selectAll();
    }

    @Override
    public Cliente search(int primaryKey) throws DatabaseException {
        return clienteDAO.search(primaryKey);
    }

    @Override
    public List<Cliente> select(String nome) throws DatabaseException {
        return clienteDAO.select(nome);
    }

}

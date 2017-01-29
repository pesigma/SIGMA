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
import tolteco.sigma.model.tables.ClienteTable;
import tolteco.sigma.model.tables.SigmaAbstractTableModel;

/**
 *
 * @author Juliano Felipe
 */
public class ClienteController extends GenericController<Cliente, ClienteTable>{
    
    private final ClienteDAO clienteDAO;

    public ClienteController(DAOFactory dao, SigmaAbstractTableModel model) {
        super(dao, model);
        clienteDAO = dao.getClienteDAO();
    }
    
    @Override
    public boolean insert(Cliente t) throws DatabaseException {
        boolean ins = clienteDAO.insert(t);
        
        if (ins){
            List<Cliente> clientes = clienteDAO.select(t.getNome());
            int key = -1;
            
            for (Cliente client : clientes){
                if (client.equals(t)){
                    key = client.getClienteId();
                    t = client;
                }
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na inserção de cliente. Obtenção de código de inserção"
                + " falhou.");
            } else{
                model.addRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na inserção de cliente. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
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

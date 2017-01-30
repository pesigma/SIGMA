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
                /*
                Essa atribuição maluca é para poder obter o
                id do cliente.
                Não fará muito sentido sem adaptar o "equals"
                para funcionar com ou sem id
                
                Esse parada vai 
                */
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
        boolean rem = clienteDAO.remove(t);
        
        if (rem){
            Cliente cliente = clienteDAO.search(t.getClienteId());
            int key = -1;
            if (cliente.equals(t)){
                key = cliente.getClienteId();
                t = cliente;
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na remoção de cliente. Obtenção de ID falhou.");
            } else{
                model.setRow(t); //Opção de updade de linha
            }
            
        } else {
            throw new DatabaseException(
                "Falha na remoção de cliente. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public boolean update(Cliente t) throws DatabaseException {
        boolean upd = clienteDAO.update(t);
        
        if (upd){
            Cliente cliente = clienteDAO.search(t.getClienteId());
            int key = -1;
            if (cliente.equals(t)){
                key = cliente.getClienteId();
                t = cliente;
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na atualização de cliente. Obtenção de ID de cliente"
                + " falhou.");
            } else{
                model.removeRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na atualização de cliente. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import java.util.logging.Level;
import tolteco.sigma.model.dao.ClienteDAO;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.entidades.Cliente;
import tolteco.sigma.model.tables.ClienteTable;
import tolteco.sigma.view.MainFrame;

/**
 *
 * @author Juliano Felipe
 */
public class ClienteController extends GenericController<Cliente, ClienteTable>{
    private final ClienteDAO clienteDAO;
    private final ClienteTable model;
    
    @Override
    protected final void initTable() throws DatabaseException {
        model.addAll(clienteDAO.selectAll());
    }
    
    public ClienteController(DAOFactory dao, ClienteTable model){
        super(dao);
        clienteDAO = dao.getClienteDAO();
        this.model = model;
    }
    
    @Override
    public int insert(Cliente t) throws DatabaseException {
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int ins = clienteDAO.insert(t);
        
        if (ins != -1){       
            model.addRow(t);      
        } else {
            throw new DatabaseException(
                "Falha na inserção de cliente. Persistência no banco de dados"
                + " falhou.");
        }
        
        return ins;
    }

    @Override
    public boolean remove(Cliente t) throws DatabaseException {
        if (!isTableInitialized) initTable(); isTableInitialized=true;
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
        if (!isTableInitialized) initTable(); isTableInitialized=true;
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

    public Cliente searchByCPF(String cpf) throws DatabaseException{
        return clienteDAO.searchByCPF(cpf);
    }

    @Override
    public ClienteTable getModel() {
        if (!isTableInitialized){
            try {
                initTable();
            } catch (DatabaseException ex) {
                MainFrame.LOG.log(Level.SEVERE, "Falha ao inicializar tabela." + ex.getMessage());
            } 
            isTableInitialized=true;
        }
        return model;
    }

    
    
}

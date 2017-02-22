/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import java.util.logging.Level;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.ServicoDAO;
import tolteco.sigma.model.entidades.Servico;
import tolteco.sigma.model.tables.ServicoTable;
import tolteco.sigma.view.MainFrame;

/**
 *
 * @author Juliano Felipe
 */
public class ServicoController extends GenericController<Servico, ServicoTable>{
    private final ServicoDAO servicoDAO;
    private final ServicoTable model;

    @Override
    protected final void initTable() throws DatabaseException {
        model.addAll(servicoDAO.selectAll());
    }
    
    public ServicoController(DAOFactory dao, ServicoTable model){
        super(dao);
        servicoDAO = dao.getServicoDAO();
        this.model = model;
    }
    
    @Override
    public int insert(Servico t) throws DatabaseException {
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int ins = servicoDAO.insert(t);
        
        if (ins != -1){      
            model.addRow(t);      
        } else {
            throw new DatabaseException(
                "Falha na inserção de serviço. Persistência no banco de dados"
                + " falhou.");
        }
        
        return ins;
    }

    @Override
    public boolean remove(Servico t) throws DatabaseException {
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int key = t.getRowid();
        boolean rem = servicoDAO.remove(t);
        
        if (rem){
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na remoção de serviço. Obtenção de ID falhou.");
            } else{
                model.removeRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na remoção de serviço. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public boolean update(Servico t) throws DatabaseException {
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        boolean upd = servicoDAO.update(t);
        
        if (upd){
            Servico servico = servicoDAO.search(t.getRowid());
            int key = -1;
            if (servico.equals(t)){
                key = servico.getRowid();
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na atualização de serviço. Obtenção de ID falhou.");
            } else{
                model.setRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na atualização de serviço. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public List<Servico> selectAll() throws DatabaseException {
        return servicoDAO.selectAll();
    }

    @Override
    public Servico search(int primaryKey) throws DatabaseException {
        return servicoDAO.search(primaryKey);
    }

    @Override
    public List<Servico> select(String nome) throws DatabaseException {
        return servicoDAO.select(nome);
    }

    @Override
    public ServicoTable getModel() {
        if (!isTableInitialized){
            try {
                initTable();
            } catch (DatabaseException ex) {
                MainFrame.LOG.log(Level.SEVERE, "Falha ao inicializar tabela.");
            } 
            isTableInitialized=true;
        }
        return model;
    }
    
    public List<Servico> searchByPlaca(String placa) throws DatabaseException{
        return servicoDAO.searchByPlaca(placa);
    }
}

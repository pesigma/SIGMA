/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.ServicoDAO;
import tolteco.sigma.model.entidades.Financa;
import tolteco.sigma.model.entidades.Servico;
import tolteco.sigma.model.tables.ServicoTable;
import tolteco.sigma.model.tables.SigmaAbstractTableModel;

/**
 *
 * @author Juliano Felipe
 */
public class ServicoController extends GenericController<Servico, ServicoTable>{

    private final ServicoDAO servicoDAO;

    public ServicoController(DAOFactory dao, SigmaAbstractTableModel model) {
        super(dao, model);
        servicoDAO = dao.getServicoDAO();
    }
    
    @Override
    public boolean insert(Servico t) throws DatabaseException {
        boolean ins = servicoDAO.insert(t);
        
        if (ins){
            List<Servico> servicos = servicoDAO.select(t.getPlaca());
            int key = -1;
            
            for (Servico servico : servicos){
                if (servico.equals(t)){
                    key = servico.getRowid();
                    t = servico;
                }
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na inserção de serviço. Obtenção de código de inserção"
                + " falhou.");
            } else{
                model.addRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na inserção de serviço. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public boolean remove(Servico t) throws DatabaseException {
        boolean rem = servicoDAO.remove(t);
        
        if (rem){
            Servico servico = servicoDAO.search(t.getRowid());
            int key = -1;
            if (servico.equals(t)){
                key = servico.getRowid();
            }
            
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
    
}

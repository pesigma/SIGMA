/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.FinancaDAO;
import tolteco.sigma.model.entidades.Financa;
import tolteco.sigma.model.tables.FinancaTable;

/**
 *
 * @author Juliano Felipe
 */
public class FinancaController extends GenericController<Financa, FinancaTable>{
    private final FinancaDAO financaDAO;
    private final FinancaTable model;

    public FinancaController(DAOFactory dao, FinancaTable model) {
        super(dao);
        financaDAO = dao.getFinancaDAO();
        this.model = model;
    }

    @Override
    public int insert(Financa t) throws DatabaseException {
        int ins = financaDAO.insert(t);
        
        if (ins != -1){      
            model.addRow(t);      
        } else {
            throw new DatabaseException(
                "Falha na inserção de finança. Persistência no banco de dados"
                + " falhou.");
        }
        
        return ins;
    }

    @Override
    public boolean remove(Financa t) throws DatabaseException {
        boolean rem = financaDAO.remove(t);
        
        if (rem){
            Financa financa = financaDAO.search(t.getRowid());
            int key = -1;
            if (financa.equals(t)){
                key = financa.getRowid();
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na remoção de finança. Obtenção de ID falhou.");
            } else{
                model.setRow(t); //Opção de updade de linha
            }
            
        } else {
            throw new DatabaseException(
                "Falha na remoção de finança. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public boolean update(Financa t) throws DatabaseException {
        boolean upd = financaDAO.update(t);
        
        if (upd){
            Financa financa = financaDAO.search(t.getRowid());
            int key = -1;
            if (financa.equals(t)){
                key = financa.getRowid();
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na atualização de finança. Obtenção de ID falhou.");
            } else{
                model.removeRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na atualização de finança. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public List<Financa> selectAll() throws DatabaseException {
        return financaDAO.selectAll();
    }

    @Override
    public Financa search(int primaryKey) throws DatabaseException {
        return financaDAO.search(primaryKey);
    }

    @Override
    public List<Financa> select(String nome) throws DatabaseException {
        return financaDAO.select(nome);
    }
    
    @Override
    public FinancaTable getModel() {
        return model;
    }
    
}

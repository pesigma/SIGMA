/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.Date;
import java.util.List;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.VersionDAO;
import tolteco.sigma.model.entidades.Version;
import tolteco.sigma.model.tables.VersionTable;

/**
 *
 * @author Juliano Felipe
 */ 
public class VersionController extends GenericController<Version, VersionTable>{

    private final VersionDAO versionDAO;
    private final VersionTable model;
    
    public VersionController(DAOFactory dao, VersionTable model) {
        super(dao);
        versionDAO = dao.getVersionDAO();
        this.model = model;
    }
    
    @Override
    public int insert(Version t) throws DatabaseException {
        int ins = versionDAO.insert(t);
        
        if (ins != -1){      
            model.addRow(t);      
        } else {
            throw new DatabaseException(
                "Falha na inserção de versão. Persistência no banco de dados"
                + " falhou.");
        }
        
        return ins;
    }

    @Override
    public boolean remove(Version t) throws DatabaseException {
        boolean rem = versionDAO.remove(t);
        
        if (rem){
            Version version = versionDAO.search(t.getMajorVersion());
            int key = -1;
            if (version.equals(t)){
                key = version.getMajorVersion();
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na remoção de versão. Obtenção de ID falhou.");
            } else{
                model.removeRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na remoção de versão. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public boolean update(Version t) throws DatabaseException {
        boolean rem = versionDAO.update(t);
        
        if (rem){
            Version version = versionDAO.search(t.getMajorVersion());
            int key = -1;
            if (version.equals(t)){
                key = version.getMajorVersion();
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na atualização de versão. Obtenção de ID falhou.");
            } else{
                model.setRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na atualização de versão. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public List<Version> selectAll() throws DatabaseException {
        return versionDAO.selectAll();
    }

    @Override
    public Version search(int primaryKey) throws DatabaseException {
        return versionDAO.search(primaryKey);
    }

    @Override
    public List<Version> select(String nome) throws DatabaseException {
        return versionDAO.select(nome);
    }
    
    public void createMajorRelease(String name, Date date, String notes) throws DatabaseException{
        versionDAO.createMajorRelease(name, date, notes);
    }
    
    public void createMinorRelease(int majorKey, Date date, String notes) throws DatabaseException{
        versionDAO.createMinorRelease(majorKey, date, notes);
    }
    
    public Version fetchLatestVersion() throws DatabaseException{
        return versionDAO.fetchLatestVersion();
    }

    @Override
    public VersionTable getModel() {
        return model;
    }
}

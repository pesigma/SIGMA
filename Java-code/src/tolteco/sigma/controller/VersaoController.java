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
import tolteco.sigma.model.tables.SigmaAbstractTableModel;
import tolteco.sigma.model.tables.VersionTable;

/**
 *
 * @author Juliano Felipe
 */ 
public class VersaoController extends GenericController<Version, VersionTable>{

    private final VersionDAO versionDAO;

    public VersaoController(DAOFactory dao, SigmaAbstractTableModel model) {
        super(dao, model);
        versionDAO = dao.getVersionDAO();
    }
    
    @Override
    public boolean insert(Version t) throws DatabaseException {
        boolean ins = versionDAO.insert(t);
        
        if (ins){
            List<Version> versoes = versionDAO.select(t.getMajorName());
            int key = -1;
            
            for (Version versao: versoes){
                if (versao.equals(t)){
                    key = versao.getRowid();
                    t = versao;
                }
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na inserção de versão. Obtenção de código de inserção"
                + " falhou.");
            } else{
                model.addRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na inserção de versão. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public boolean remove(Version t) throws DatabaseException {
        boolean rem = versionDAO.remove(t);
        
        if (rem){
            Version version = versionDAO.search(t.getRowid());
            int key = -1;
            if (version.equals(t)){
                key = version.getRowid();
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
            Version version = versionDAO.search(t.getRowid());
            int key = -1;
            if (version.equals(t)){
                key = version.getRowid();
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
    
    public void createMajorRelease(String name, String notes){
        versionDAO.createMajorRelease(name, notes);
    }
    
    public void createMinorRelease(Date date, String notes){
        versionDAO.createMinorRelease(date, notes);
    }
    
    public Version fetchLatestVersion(){
        return versionDAO.fetchLatestVersion();
    }
}

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

/**
 *
 * @author Juliano Felipe
 */
public class VersaoController extends GenericController<Version>{

    private final VersionDAO versionDAO;
    
    public VersaoController(DAOFactory dao) {
        super(dao);
        
        versionDAO = dao.getVersionDAO();
    }

    @Override
    public boolean insert(Version t) throws DatabaseException {
        return versionDAO.insert(t);
    }

    @Override
    public boolean remove(Version t) throws DatabaseException {
        return versionDAO.insert(t);
    }

    @Override
    public boolean update(Version t) throws DatabaseException {
        return versionDAO.insert(t);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.VersionDAO;
import tolteco.sigma.model.entidades.Major;
import tolteco.sigma.model.entidades.Minor;
import tolteco.sigma.model.entidades.Version;
import tolteco.sigma.model.tables.VersionTable;
import tolteco.sigma.view.MainFrame;

/**
 *
 * @author Juliano Felipe
 */ 
public class VersionController extends GenericController<Version, VersionTable>{

    private final VersionDAO versionDAO;
    private final VersionTable model;
    
    @Override
    protected final void initTable() throws DatabaseException {
        model.addAll(versionDAO.selectAll());
    }
    
    public VersionController(DAOFactory dao, VersionTable model){
        super(dao);
        versionDAO = dao.getVersionDAO();
        this.model = model;
    }
    
    @Override
    public int insert(Version t) throws DatabaseException {
        if (!isTableInitialized) initTable(); isTableInitialized=true;
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
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int key = t.getMajorVersion();
        boolean rem = versionDAO.remove(t);
        
        if (rem){
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
        if (!isTableInitialized) initTable(); isTableInitialized=true;
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
    
    public void createRelease(Version version) throws DatabaseException{
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int id = versionDAO.createMajorRelease(version.getMajorName(), version.getMajorDate(), version.getMajorNotes());
        createMinorRelease(id, version.getMinorDate(), version.getMinorNotes());
        model.addRow(version);
    }
    
    public void createMinorRelease(int majorKey, Date date, String notes) throws DatabaseException{
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int minorId = versionDAO.createMinorRelease(majorKey, date, notes);
        Major major = versionDAO.getMajor(majorKey);
        Version ver = Version.versionBuilder(major, new Minor(majorKey, minorId, date, notes));
        int row = -1;
        
        for (int i=0; i<model.getRowCount(); i++){
            if (model.getRow(i).getMajorVersion() == major.getMajorVer()){
                row = i;
                break;
            }
        }
        
        model.setValueAt(date, row, VersionTable.MINOR_DATE);
        model.setValueAt(notes, row, VersionTable.MINOR_NOTES);
    }
    
    public void createMinorRelease(Minor minor) throws DatabaseException{
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        this.createMinorRelease(minor.getMajorVer(), minor.getMinorDate(), minor.getMinorNotes());
    }
    
    public Version fetchLatestVersion() throws DatabaseException{
        return versionDAO.fetchLatestVersion();
    }

    public Major getLatestMajor() throws DatabaseException{
        return versionDAO.getLatestMajor();
    }
    
    public Minor getLatestMinor() throws DatabaseException{
        return versionDAO.getLatestMinor();
    }
    
    public Major getMajor(int key) throws DatabaseException{
        return versionDAO.getMajor(key);
    }
    
    public Minor getLatestMinor(int key) throws DatabaseException{
        return versionDAO.getLatestMinor(key);
    }
    
    public boolean createAutoMinorRelease(Date date, String notes) throws DatabaseException{
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int minorId = versionDAO.createAutoMinorRelease(date, notes);
        Major major = versionDAO.getLatestMajor();
        Version ver = Version.versionBuilder(major, new Minor(major.getMajorVer(), minorId, date, notes));
        int row = model.search(ver);
        
        model.setValueAt(date, row, VersionTable.MINOR_DATE);
        model.setValueAt(notes, row, VersionTable.MINOR_NOTES);
        return true;
    }
    
    @Override
    public VersionTable getModel() {
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
}

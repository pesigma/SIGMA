/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.VersionDAO;
import tolteco.sigma.model.entidades.Version;
import tolteco.sigma.utils.SDate;

/**
 * DAO para criar releases, retornar descrições, e nomes
 * das últimas versões, etc.
 * @author Juliano Felipe
 */
public class JDBCVersionDAO extends JDBCAbstractDAO<Version> implements VersionDAO{

    @Override
    public int createMajorRelease(String name, Date date, String notes) throws DatabaseException{
        String query = "INSERT INTO MajorVersion " + 
                "(MajorName,MajorDate,MajorNotes) " + 
                "VALUES (?,?,?)";
        PreparedStatement pst = null;
        
        int id = -1;
        
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, SDate.sigmaDateFormat(date));
            pst.setString(3, notes);
            pst.execute();

            id = getNextMajorId();
        } catch (SQLException e) {
            //String error = e.getClass().getName() + ": " + e.getMessage();
            throw new DatabaseException(e);
        }  finally {
            if (pst != null) 
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new DatabaseException(ex);
                }
        }
        
        return id-1;
    }

    @Override
    public int createMinorRelease(int majorVer, Date date, String notes) throws DatabaseException {
        String query = "INSERT INTO MinorVersion " + 
                "(MajorVer,MinorDate,MinorNotes) " + 
                "VALUES (?,?,?)";
        PreparedStatement pst = null;
        
        int id = -1;
        
        try {
            pst = connection.prepareStatement(query);
            pst.setInt   (1, majorVer);
            pst.setString(2, SDate.sigmaDateFormat(date));
            pst.setString(3, notes);
            pst.execute();

            id = getNextMinorId();
        } catch (SQLException e) {
            //String error = e.getClass().getName() + ": " + e.getMessage();
            throw new DatabaseException(e);
        }  finally {
            if (pst != null) 
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new DatabaseException(ex);
                }
        }
        
        return id-1;
    }

    @Override
    public Version fetchLatestVersion() throws DatabaseException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Version t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Version t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Version t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Version> selectAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Version search(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Version> select(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Version getInstance(ResultSet rs) throws DatabaseException {
        try { //SQL DATE O MESMO QUE JAVA DATE?
            return new Version(
                       rs.getInt   ("MajorVer"),
                       rs.getString("MajorName"),
 SDate.sigmaDateFormat(rs.getString("MajorDate")),
                       rs.getString("MajorNotes"),
                       rs.getInt   ("MinorVer"),
 SDate.sigmaDateFormat(rs.getString("MinorDate")),
                       rs.getString("MinorNotes"));
        } catch (SQLException | ParseException ex) {
            //Logger.getLogger(JDBCClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex);
        }
    }
    
    /**
     * Pega o último ID inserido e adiciona mais um.
     * Garante que não haverá conflito, mas não garante
     * nada sobre IDs deletados (GAPs surgirão).
     * @return inteiro do próximo id disponível para versões
     *         "Major".
     * @throws SQLException em obter ids.
     */
    protected final int getNextMajorId() throws SQLException {
        String query = "SELECT MajorVer FROM MajorVersion";
        int lastId;
        try (PreparedStatement pst = connection.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            lastId = -1;
            while (rs.next()){
                lastId = rs.getInt("MajorVer");
            }          
        }
        
        return lastId+1;
    }
    
    /**
     * Pega o último ID inserido e adiciona mais um.
     * Garante que não haverá conflito, mas não garante
     * nada sobre IDs deletados (GAPs surgirão).
     * @return inteiro do próximo id disponível para versões
     *         "Minor".
     * @throws SQLException em obter ids.
     */
    protected final int getNextMinorId() throws SQLException {
        String query = "SELECT MinorVer FROM MinorVersion";
        int lastId;
        try (PreparedStatement pst = connection.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            lastId = -1;
            while (rs.next()){
                lastId = rs.getInt("MinorVer");
            }          
        }
        
        return lastId+1;
    }

    @Override
    public Major getLatestMajor() throws DatabaseException {
        String query = "SELECT MajorVer, * FROM MajorVersion";
        
        int majorVer = -1;
        String majorName = null, majorDate = null, majorNotes = null;
        Major major = null;
        try (PreparedStatement pst = connection.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

            while (rs.next()){
                majorVer   = rs.getInt   ("MajorVer");
                majorName  = rs.getString("MajorName");
                majorDate  = rs.getString("MajorDate");
                majorNotes = rs.getString("MajorNotes");
            } rs.last();
            
            /*
            COLOCAR RS.LAST() NO LUGAR DESSE LOOP BIZARRO
            */
            
            
            asdfsadfasdf
                    sdaf
                    sdfa
                            asf
                            d
                                    sfad
            
            
            
            major = new Major(majorVer, majorName, SDate.sigmaDateFormat(majorDate), majorNotes);
        } catch (SQLException | ParseException ex) {
            //Logger.getLogger(JDBCVersionDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex);
        }
        
        return major;
    }

    @Override
    public Minor getLatestMinor() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

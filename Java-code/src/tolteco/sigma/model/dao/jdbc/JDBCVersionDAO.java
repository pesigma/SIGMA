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
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.VersionDAO;
import tolteco.sigma.model.entidades.Version;
import tolteco.sigma.utils.SDate;
import tolteco.sigma.view.Sistema;

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
             
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, SDate.sigmaDateFormat(date));
            pst.setString(3, notes);
            pst.execute();

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
        
        return true;
    }

    @Override
    public int createMinorRelease(int majorVer, Date date, String notes) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}

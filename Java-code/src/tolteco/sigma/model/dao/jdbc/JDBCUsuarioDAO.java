
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.UsuarioDAO;
import tolteco.sigma.model.entidades.Access;
import tolteco.sigma.model.entidades.Usuario;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCUsuarioDAO extends JDBCAbstractDAO implements UsuarioDAO{

    @Override
    public int insert(Usuario t) throws DatabaseException {
        String query = "INSERT INTO Users " + 
                "(accessLevel,userName,userPass) " + 
                "VALUES (?,?,?)";
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, t.getAccessLevel().getValue());
            pst.setString(2, t.getUserName());
            pst.setString(3, Arrays.toString(t.getPass()));
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
        
        return getNextId()-1;
    }

    @Override
    public boolean remove(Usuario t) throws DatabaseException {
        String query = "DELETE FROM Users WHERE userId=" + Integer.toString(t.getUserId());
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);            
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
    public boolean update(Usuario t) throws DatabaseException {
        String query = "UPDATE Users " + 
                "SET userId=?, accessLevel=?, " +
                "userName=?, userPass=? " +  
                "WHERE userId=" + Integer.toString(t.getUserId());
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);
            pst.setInt   (1, t.getUserId());
            pst.setInt   (2, t.getAccessLevel().getValue());
            pst.setString(3, t.getUserName());
            pst.setString(3, Arrays.toString(t.getPass()));
            pst.execute();
        } catch (Exception e) {
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
    public List<Usuario> selectAll() throws DatabaseException {
        List<Usuario> lista = new ArrayList<>();
        
        String query = "SELECT userId, * FROM Users";
        PreparedStatement pst = null;
        ResultSet rs=null;
        
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()){
                lista.add(getInstance(rs));
            }
            rs.close();
            
        } catch (Exception e) {
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
        
        return lista;
    }

    @Override
    public Usuario search(int primaryKey) throws DatabaseException {
        Usuario usuario;
        
        String query = "SELECT userId, * FROM Users WHERE userId=?";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, primaryKey);
            rs = pst.executeQuery();
            
            usuario = getInstance(rs);
            
            rs.close();
            
        } catch (Exception e) {
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
        
        return usuario;
    }

    @Override
    public List<Usuario> select(String nome) throws DatabaseException {
        List<Usuario> lista = new ArrayList<>();
        
        String query = "SELECT userId, * FROM Users WHERE userName=?";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try { //Há um while mas deverá executar somente uma vez
              //já que o userName deve ser único.
            pst = connection.prepareStatement(query);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            
            while (rs.next()){   
                lista.add(getInstance(rs));
            }
            rs.close();
            
        } catch (Exception e) {
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
        
        return lista;
    }
    
    @Override
    protected Usuario getInstance(ResultSet rs) throws DatabaseException{
        try {
            return new Usuario(
                    rs.getInt("userId"),
                    rs.getString("userName"),
                    Access.porCodigo(rs.getInt("accessLevel")),
                    rs.getString("userPass").toCharArray());
        } catch (SQLException ex) {
            //Logger.getLogger(JDBCClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex);
        }
    }   

    @Override
    public int getNextId() throws DatabaseException {
        String query = "SELECT userId FROM Users ORDER BY userId";
        int lastId = -2;
        try (PreparedStatement pst = connection.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            lastId = -1;
            while (rs.next()){
                lastId = rs.getInt("userId");
            }          
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
        
        return lastId+1;
    }
}

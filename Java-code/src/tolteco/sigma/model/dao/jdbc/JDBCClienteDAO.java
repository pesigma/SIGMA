/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.sql.PreparedStatement;
import java.util.List;
import tolteco.sigma.model.dao.ClienteDAO;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.entidades.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCClienteDAO extends JDBCAbstractDAO implements ClienteDAO{

    public JDBCClienteDAO() {
        super();
    }
    
    @Override
    public int insert(Cliente t) throws DatabaseException {
        String query = "INSERT INTO Cliente " + 
                "(userId,Primeiro_Nome,Ultimo_Nome,CPF,Telefone,Endereco,Observacoes) " + 
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, t.getUserId());
            pst.setString(2, t.getNome());
            pst.setString(3, t.getSobrenome());
            pst.setString(4, t.getCpf());
            pst.setString(5, t.getTel());
            pst.setString(6, t.getEnd());
            pst.setString(7, t.getObs());
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
    public boolean remove(Cliente t) throws DatabaseException {
        String query = "DELETE FROM Cliente WHERE clienteId="+t.getClienteId();
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
    public boolean update(Cliente t) throws DatabaseException {
        String query = "UPDATE Cliente " + 
                "SET userId=?, Primeiro_Nome=?, Ultimo_Nome=?, CPF=?, " + 
                "Telefone=?, Endereco=?, Observacoes=? " + 
                "WHERE clienteId=" + t.getClienteId();
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, t.getUserId());
            pst.setString(2, t.getNome());
            pst.setString(3, t.getSobrenome());
            pst.setString(4, t.getCpf());
            pst.setString(5, t.getTel());
            pst.setString(6, t.getEnd());
            pst.setString(7, t.getObs());
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
    public List<Cliente> selectAll() throws DatabaseException {       
        List<Cliente> lista = new ArrayList<>();
        
        String query = "SELECT clienteId, * FROM Cliente";
        PreparedStatement pst = null;
        ResultSet rs=null;
        
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()){
                lista.add(getInstance(rs));
            }
            rs.close();
            
        } catch (SQLException | DatabaseException e) {
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
    public Cliente search(int primaryKey) throws DatabaseException {
        Cliente cliente;
        
        String query = "SELECT clienteId, * FROM Cliente WHERE clienteId=?";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, primaryKey);
            rs = pst.executeQuery();
            
            cliente = getInstance(rs);
            
            rs.close();
            
        } catch (SQLException | DatabaseException e) {
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
        
        return cliente;
    }

    @Override
    public List<Cliente> select(String nome) throws DatabaseException {
        List<Cliente> lista = new ArrayList<>();
        
        String query = "SELECT clienteId, * FROM Cliente WHERE"
                + " (nome LIKE '%" + nome + "%')";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()){   
                lista.add(getInstance(rs));
            }
            rs.close();
            
        } catch (SQLException | DatabaseException e) {
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
    public Cliente searchByCPF(String cpf) throws DatabaseException{
        Cliente cliente = null;
        String query = "SELECT clienteId, * FROM Cliente WHERE CPF=?";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            cliente = getInstance(rs);
            rs.close();
            
        } catch (SQLException | DatabaseException e) {
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
        
        return cliente;
    }
    
    /**
     *
     * @param rs
     * @return
     * @throws tolteco.sigma.model.dao.DatabaseException
     */
    @Override
    protected Cliente getInstance(ResultSet rs) throws DatabaseException{
        try {
            return new Cliente(
                    rs.getInt("clienteId"),
                    rs.getString("Primeiro_Nome"),
                    rs.getString("Ultimo_Nome"),
                    rs.getString("Observacoes"),
                    rs.getString("Endereco"),
                    rs.getString("Telefone"),
                    rs.getString("CPF"),
                    rs.getInt("userId"));
        } catch (SQLException ex) {
            //Logger.getLogger(JDBCClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex);
        }
    }

    @Override
    public int getNextId() throws DatabaseException {
        String query = "SELECT clienteId FROM Cliente ORDER BY clienteId";
        int lastId = -2;
        try (PreparedStatement pst = connection.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            lastId = -1;
            while (rs.next()){
                lastId = rs.getInt("clienteId");
            }          
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
        
        return lastId+1;
    }
    
}
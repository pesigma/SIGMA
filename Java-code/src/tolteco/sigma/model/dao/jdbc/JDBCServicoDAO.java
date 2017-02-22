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
import java.util.List;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.ServicoDAO;
import tolteco.sigma.model.entidades.Servico;
import tolteco.sigma.model.entidades.Situacao;
import tolteco.sigma.view.Sistema;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCServicoDAO extends JDBCAbstractDAO<Servico> implements ServicoDAO{

    @Override
    public int insert(Servico t) throws DatabaseException {
        String query = "INSERT INTO Servico " + 
                "(userId,clienteId,Placa,Quilometragem,Modelo,Situacao,Observacoes) " + 
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);
            pst.setInt   (1, Sistema.getUserID());
            pst.setInt   (2, t.getIdcliente());
            pst.setString(3, t.getPlaca());
            pst.setDouble(4, t.getKm());
            pst.setString(5, t.getModelo());
            pst.setInt   (6, t.getSituacao().getCodigo());
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
    public boolean remove(Servico t) throws DatabaseException {
        String query = "DELETE FROM Servico WHERE servicoId=" + t.getRowid();
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
    public boolean update(Servico t) throws DatabaseException {
        String query = "UPDATE Servico " + 
                "SET userId=?, clienteId=?, PlacaPlaca=?, " + 
                "Quilometragem=?, Modelo=?, Situacao=?, Observacoes=? " + 
                "WHERE servicoId=" + t.getRowid();
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);
            pst.setInt   (1, Sistema.getUserID());
            pst.setInt   (2, t.getIdcliente());
            pst.setString(3, t.getPlaca());
            pst.setDouble(4, t.getKm());
            pst.setString(5, t.getModelo());
            pst.setInt   (6, t.getSituacao().getCodigo());
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
    public List<Servico> selectAll() throws DatabaseException {
        List<Servico> lista = new ArrayList<>();
        
        String query = "SELECT servicoId, * FROM Servico";
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
    public Servico search(int primaryKey) throws DatabaseException {
        Servico cliente;
        
        String query = "SELECT servicoId, * FROM Servico WHERE servicoId=?";
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
    public List<Servico> select(String modelo) throws DatabaseException {
        List<Servico> lista = new ArrayList<>();
        
        String query = "SELECT servicoId, * FROM Servico WHERE"
               + " (Modelo LIKE '%" + modelo + "%')";
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
    public List<Servico> searchByPlaca(String placa) throws DatabaseException {
        List<Servico> lista = new ArrayList();
        
        String query = "SELECT servicoId, * FROM Servico WHERE placa=?";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, placa);
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
    protected Servico getInstance(ResultSet rs) throws DatabaseException{
        try {
            return new Servico(
                    rs.getInt("servicoId"),
                    rs.getInt("clienteId"),
                    rs.getString("Placa"),
                    rs.getString("Modelo"),
                    rs.getDouble("Quilometragem"),
                    Situacao.porCodigo(rs.getInt("Situacao")),
                    rs.getString("Observacoes"),
                    rs.getInt("userId"));
        } catch (SQLException ex) {
            //Logger.getLogger(JDBCClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex);
        }
    }

    @Override
    public int getNextId() throws DatabaseException {
        String query = "SELECT servicoId FROM Servico ORDER BY servicoId";
        int lastId = -2;
        try (PreparedStatement pst = connection.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            lastId = -1;
            while (rs.next()){
                lastId = rs.getInt("servicoId");
            }          
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
        
        return lastId+1;
    }
    
}

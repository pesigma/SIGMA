/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import ConecBD.ConexaoBanco;
import tolteco.sigma.model.entidades.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juliano
 */
public class DAOServico {
    public static void insert (Servico sr) throws SQLException{
        Connection servico = ConexaoBanco.concliente();
        
        PreparedStatement pst = null;
        try {
            String sql1 = "INSERT INTO servico (Placa,Quilometragem,Modelo,Situacao,Idcliente,Obs) "
                        + "VALUES (?,?,?,?,?,?)";
            pst = servico.prepareStatement(sql1);
            pst.setString (1, sr.getPlaca());
            pst.setInt    (2, sr.getKm());
            pst.setString (3, sr.getModelo());
            pst.setBoolean(4, sr.getSit());
            pst.setInt    (5, sr.getIdcliente());
            pst.setString (6, sr.getObs());
            pst.executeUpdate();
        } catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na inserção do serviço.", "04-04-06.", error);
        } finally {
            if (pst != null) 
                pst.close();
            if (servico != null) 
                servico.close();
        }
    }
    
    private static ArrayList<Servico> selectAll (String placa){
        ArrayList<Servico> data = new ArrayList ();
        
        return data;
    }
    
    public static Servico select (String placa){
        
        return new Servico(1);
    }
    
    public static void delete (Servico sr) throws SQLException{
       Connection servico = ConexaoBanco.Multiple();
        PreparedStatement pst = null;
        try {
            String sql1 = "DELETE FROM financa "
                        + "WHERE rowid=" + Integer.toString(sr.getRowid());
            pst = servico.prepareStatement(sql1);            
            pst.execute();

        } catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na exclusão do cliente", "04-02-08.", error);
        } finally {
            if (pst != null) 
                pst.close();
            if (servico != null) 
                servico.close();
        } 
    }
    
    public static void update (Servico sr) throws SQLException{
        if (sr.getRowid()==-1){
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na obtenção do id do serviço.", 
                      "04-04-07.", "Id menor ou igual a -1.");
            return;
        }
        
        Connection servico = ConexaoBanco.concliente();
        PreparedStatement pst = null;
        try {
            String sql1 = "UPDATE servico SET Placa=?, Quilometragem=?, Modelo=?, Situacao=?, Idcliente=?, Obs=? "
                        + "WHERE rowid="+sr.getRowid();
            pst = servico.prepareStatement(sql1);
            pst.setString (1, sr.getPlaca());
            pst.setInt    (2, sr.getKm());
            pst.setString (3, sr.getModelo());
            pst.setBoolean(4, sr.getSit());
            pst.setInt    (5, sr.getIdcliente());
            pst.setString (6, sr.getObs());
            pst.executeUpdate();
        } catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na atualização de dados do serviço.", "04-04-08.", error);
        } finally {
            if (pst != null) 
                pst.close();
            if (servico != null) 
                servico.close();
        }
    }
}

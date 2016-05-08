/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import ConecBD.ConexaoBanco;
import Entidades.Financa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juliano
 */
public class DAOFinanca {
    public static void insert (Financa fin) throws SQLException{
        Connection confinanca = ConexaoBanco.confinanca();
        
        PreparedStatement pst = null;
        //try {
            String sql1 = "INSERT into financa (tipo,data,valor,sit,obs) "
                        + "VALUES (?,?,?,?,?)";
            pst = confinanca.prepareStatement(sql1);
            pst.setBoolean(1, fin.getTipo());
            pst.setString (2, fin.TranslateString(fin.getData().toString()));
            pst.setDouble (3, fin.getValor());
            pst.setBoolean(4, fin.isSit());
            pst.setString (5, fin.getObs());
            pst.executeUpdate();
        /*} catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na inserção de dados da finança.", "04-03-66.", error);
            e.printStackTrace();
        }  finally {
            if (pst != null) 
                pst.close();
            if (confinanca != null) 
                confinanca.close();
        }*/
    }
    
    private static ArrayList<Financa> selectAll (boolean sit){
        ArrayList<Financa> data = new ArrayList ();
        
        return data;
    }
    
    public static Financa select (boolean sit){
        
        return new Financa(1);
    }
    
    public static void delete (Financa fin) throws SQLException{       
        Connection confinanca = ConexaoBanco.confinanca();
        PreparedStatement pst = null;
        try {
            String sql1 = "DELETE FROM financa "
                        + "WHERE rowid=" + Integer.toString(fin.getRowid());
            pst = confinanca.prepareStatement(sql1);            
            pst.execute();

        } catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na exclusão do cliente", "04-02-08.", error);
        } finally {
            if (pst != null) 
                pst.close();
            if (confinanca != null) 
                confinanca.close();
        }
    }
    
    public static void update (Financa fin) throws SQLException{
        if (fin.getRowid()==-1){
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na atualização de dados da finança.", 
                    "04-03-04.", "ID obtido é inválido.");
            return;
        }
        
        Connection confinanca = ConexaoBanco.confinanca();
        PreparedStatement pst = null;
        try {
            String sql1 = "UPDATE financa SET tipo=?, data=?, valor=?, sit=?, obs=? "
                         +"WHERE rowid="+Integer.toString(fin.getRowid());
            pst = confinanca.prepareStatement(sql1);
            pst.setBoolean(1, fin.getTipo());
            pst.setString (2, fin.getData().toString());
            pst.setDouble (3, fin.getValor());
            pst.setBoolean(4, fin.isSit());
            pst.setString (5, fin.getObs());
            pst.executeUpdate();
        } catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na atualização de dados da finança.", "04-03-05.", error);
        } finally {
            if (pst != null) 
                pst.close();
            if (confinanca != null) 
                confinanca.close();
        }
    }
}

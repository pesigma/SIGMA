/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import ConecBD.ConexaoBanco;
import tolteco.sigma.model.entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juliano
 */
public class DAOCliente {
    
    private Connection getConnection (){
        return ConexaoBanco.concliente();
    }
    
    
    public static void insert (Cliente cl) throws SQLException{
        Connection concliente = ConexaoBanco.concliente();
        
        PreparedStatement pst = null;
        try {
            String sql1 = "INSERT INTO cliente (nome,cpf,tel,end,obs,lname) "
                        + "VALUES (?,?,?,?,?,?)";
            pst = concliente.prepareStatement(sql1);
            pst.setString(1, cl.getNome());
            pst.setString(6, cl.getSobrenome());
            pst.setString(2, cl.getCpf());
            pst.setString(3, cl.getTel());
            pst.setString(4, cl.getEnd());
            pst.setString(5, cl.getEnd());
            pst.execute();

        } catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na inserção de clientes.", "04-02-01.", error);
        }  finally {
            if (pst != null) 
                pst.close();
            if (concliente != null) 
                concliente.close();
        }
    }
    
    private static ArrayList<Cliente> selectAll (String name){
        ArrayList<Cliente> data = new ArrayList ();
        
        return data;
    }
    
    public static Cliente select (String name){
        
        return new Cliente(1);
    }
    
    public static void delete (Cliente cl) throws SQLException{
        Connection concliente = ConexaoBanco.concliente();
        PreparedStatement pst = null;
        try {
            String sql1 = "DELETE FROM cliente "
                        + "WHERE rowid=" + Integer.toString(cl.getRowid());
            pst = concliente.prepareStatement(sql1);            
            pst.execute();

        } catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na exclusão do cliente", "04-02-08.", error);
        } finally {
            if (pst != null) 
                pst.close();
            if (concliente != null) 
                concliente.close();
        }
    }
    
    public static void update (Cliente cl) throws SQLException{
        if (cl.getRowid()==-1){
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na atualização dos dados do cliente.", "04-02-06.",
                    "Erro no id do cliente.");
            return;
        }
        
        Connection concliente = ConexaoBanco.concliente();
        PreparedStatement pst=null;
        try {
            String sql1 = "UPDATE cliente SET nome=?, cpf=?, tel=?, end=?, obs=?, lname=?" 
                       + " WHERE rowid="+ Integer.toString(cl.getRowid());
            pst = concliente.prepareStatement(sql1);            
            pst.setString(1, cl.getNome());
            pst.setString(6, cl.getSobrenome());
            pst.setString(2, cl.getEnd());
            pst.setString(3, cl.getCpf());
            pst.setString(4, cl.getEnd());
            pst.setString(5, cl.getObs());
            pst.execute();

        } catch (SQLException e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Título", "Erro na atualização dos dados do cliente", "04-02-05.", error);
        } finally {
            if (pst != null) 
                pst.close();
            if (concliente != null) 
                concliente.close();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConecBD;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * 08/01 - Maycon Conexão com o Banco de dados
 */
/**
 *
 * @author Maycon
 */
public class ConexaoBanco {

    public static Connection concliente() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:BDSigma.sqlite");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM cliente");
            //cliente e o nome da tabela
            //JOptionPane.showMessageDialog(null, "Conectado"); //Comentar no futuro
            
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 0"); //Colocar no manual
        }
        return null;

    }

    /**
     * Juliano
     * Conecta ao banco na abertura da tela de login
    */
    public static Connection login() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:BDSigma.sqlite");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Login");
            //Banco de senhas
            //JOptionPane.showMessageDialog(null, "Conectado"); Se tudo estiver bem, nem mostra essa janela
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 0 - Login"); //Colocar no manual
        }
        return null;

    }
    
    /**
     * 06/01/16 - Juliano
     * Conecta ao banco de versão
    */
    public static Connection Version() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:BDSigma.sqlite");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Version");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 0 - Version"); //Colocar no manual
        }
        return null;

    } 
    
}

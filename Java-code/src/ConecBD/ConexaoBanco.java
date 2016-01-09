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
            JOptionPane.showMessageDialog(null, "Conectado"); //Comentar no futuro
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 0"); //Colocar no manual
        }
        return null;

    }
}

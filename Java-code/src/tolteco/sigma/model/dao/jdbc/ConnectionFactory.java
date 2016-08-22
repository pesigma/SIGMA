/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class ConnectionFactory {
    private static Connection conn;
    private static final String URL = "jdbc:sqlite:BDSigma.sqlite";
    
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(URL);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.exit(1);                        
            }
        }
        return conn;
    }
}

//Como era feito antes...
/*Connection conn = null;
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
            //JOptionPane.showMessageDialog(null, "Erro. Código: 00-01-01.", "Conexão banco de dados", JOptionPane.ERROR_MESSAGE);
            //System.err.println("01-01-01: " + e.getClass().getName() + ": " + e.getMessage());
            String erro = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error("Conexão banco de dados", "Erro na conexão com o banco de dados.", "00-01-01.", erro);
        }
        return null;*/
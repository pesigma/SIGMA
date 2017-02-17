/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view;

import tolteco.sigma.controller.ClienteController;
import tolteco.sigma.controller.FinancaController;
import tolteco.sigma.controller.ServicoController;
import tolteco.sigma.controller.UsuarioController;
import tolteco.sigma.controller.VersionController;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.jdbc.JDBCDAOFactory;
import tolteco.sigma.model.entidades.Access;
import tolteco.sigma.model.entidades.Usuario;
import tolteco.sigma.model.tables.ClienteTable;
import tolteco.sigma.model.tables.FinancaTable;
import tolteco.sigma.model.tables.ServicoTable;
import tolteco.sigma.model.tables.UsuarioTable;
import tolteco.sigma.model.tables.VersionTable;
import tolteco.sigma.utils.Main;

/**
 *
 * @author JFPS
 */
public class Sistema {
    private static Sistema system;
    private static Usuario user = new Usuario(0, "Teste", Access.ROOT, new char[]{123}); //Para testes
    private static MainFrame MAIN;
    private static final DAOFactory DAO = new JDBCDAOFactory();

    //<editor-fold defaultstate="collapsed" desc="MainFrame Initializer">
    public static MainFrame assembleMain() {
        return new MainFrame(initSubCliente(), initSubFinanca(), initSubServico(), initSubUsuario(), initSubVersion());
    }
    
    private static ServicoController initSubServico() {
        ServicoTable model = new ServicoTable();
        return new ServicoController(Sistema.DAO, model);
    }

    private static UsuarioController initSubUsuario() {
        UsuarioTable model = new UsuarioTable();
        return new UsuarioController(Sistema.DAO, model);
    }

    private static FinancaController initSubFinanca() {
        FinancaTable model = new FinancaTable();
        return new FinancaController(Sistema.DAO, model);
    }

    private static ClienteController initSubCliente() {
        ClienteTable model = new ClienteTable();
        return new ClienteController(Sistema.DAO, model);
    }

    private static VersionController initSubVersion() {
        VersionTable model = new VersionTable();
        return new VersionController(Sistema.DAO, model);
    }
    
    //</editor-fold>
    
    private Sistema(Usuario user) {
        Sistema.user = user;
    }
    
    public static int getUserID(){
        return user.getUserId();
    }

    public static Usuario getUser() {
        return user;
    }
    
    /*
    SYSTEM OPERATIONS
    */
    
    public static void login(Usuario user){
        if (user != null) system = new Sistema(user);
    }
    
    private static void startUp(){
        
    }
    
    public static void logout(){
        user = null;
        Sistema.shutdown();
    }
    
    private static void shutdown(){
        throw new UnsupportedOperationException("Não implementado");
        /*
        Realizar flush de logs.
        */
    }
}

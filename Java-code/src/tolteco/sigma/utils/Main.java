package tolteco.sigma.utils;
import Telas.*;
import tolteco.sigma.controller.ClienteController;
import tolteco.sigma.controller.FinancaController;
import tolteco.sigma.controller.ServicoController;
import tolteco.sigma.controller.UsuarioController;
import tolteco.sigma.controller.VersionController;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.jdbc.JDBCDAOFactory;
import tolteco.sigma.model.tables.ClienteTable;
import tolteco.sigma.model.tables.FinancaTable;
import tolteco.sigma.model.tables.ServicoTable;
import tolteco.sigma.model.tables.UsuarioTable;
import tolteco.sigma.model.tables.VersionTable;
import tolteco.sigma.view.MainFrame;
/**
 * Programa de chamada principal
 * @author Anderson Bottega
 * @author Juliano Felipe
 * @author Maycon Queiroz
 */
public class Main {
    private static final DAOFactory DAO = new JDBCDAOFactory();
    
    private static MainFrame MAIN;
    
    //<editor-fold defaultstate="collapsed" desc="MainFrame Initializer">
    private static ClienteController initSubCliente() {
        ClienteTable model = new ClienteTable();
        return new ClienteController(DAO, model);
    }
    
    private static FinancaController initSubFinanca() {
        FinancaTable model = new FinancaTable();
        return new FinancaController(DAO, model);
    }
    
    private static ServicoController initSubServico() {
        ServicoTable model = new ServicoTable();
        return new ServicoController(DAO, model);
    }
    
    private static UsuarioController initSubUsuario() {
        UsuarioTable model = new UsuarioTable();
        return new UsuarioController(DAO, model);
    }
    
    private static VersionController initSubVersion() {
        VersionTable model = new VersionTable();
        return new VersionController(DAO, model);
    }
    
    public static MainFrame assembleMain(){
        return new MainFrame(
                initSubCliente(),
                initSubFinanca(),
                initSubServico(),
                initSubUsuario(),
                initSubVersion()
        );
    }
//</editor-fold>
    
    public static void main(String[] args) {
        //MAIN = assembleMain();
        
        
        new TelaPrincipal().setVisible(true);
        //new Login().setVisible(true);
    }
}

package tolteco.sigma.utils;
import java.text.ParseException;
import tolteco.sigma.controller.UsuarioController;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.jdbc.JDBCDAOFactory;
import tolteco.sigma.model.entidades.Version;
import tolteco.sigma.model.tables.UsuarioTable;
import tolteco.sigma.view.Login;
/**
 * Programa de chamada principal
 * @author Anderson Bottega
 * @author Juliano Felipe
 * @author Maycon Queiroz
 */
public class Main {   
    public static void main(String[] args) throws ParseException, DatabaseException {
        DAOFactory dao = new JDBCDAOFactory();
        Version version = dao.getVersionDAO().fetchLatestVersion();
        UsuarioTable model =  new UsuarioTable();
        UsuarioController controller = new UsuarioController(dao, model);
        new Login(controller, version).setVisible(true);
    }
}

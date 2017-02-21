package tolteco.sigma.utils;
import java.text.ParseException;
import tolteco.sigma.controller.UsuarioController;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.jdbc.JDBCDAOFactory;
import tolteco.sigma.model.entidades.Access;
import tolteco.sigma.model.entidades.Usuario;
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
    
    private static Usuario user = new Usuario(0, "Teste", Access.ROOT, new char[]{123}); //Para testes
    
    public static void main(String[] args) throws ParseException, DatabaseException {
        //MAIN = assembleMain();
        
        //System.out.println(SDate.sigmaDateFormat("2017-02-12 13:46:20"));
        
        //new TelaPrincipal().setVisible(true);
        DAOFactory dao = new JDBCDAOFactory();
        System.out.println(dao.getUsuarioDAO().selectAll());
        Version version = dao.getVersionDAO().fetchLatestVersion();
        System.out.println(version.shortString());
        UsuarioTable model =  new UsuarioTable();
        UsuarioController controller = new UsuarioController(dao, model);
        new Login(controller, version).setVisible(true);
    }
}

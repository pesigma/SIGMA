package tolteco.sigma.utils;
import java.text.ParseException;
import tolteco.sigma.model.entidades.Access;
import tolteco.sigma.model.entidades.Usuario;
/**
 * Programa de chamada principal
 * @author Anderson Bottega
 * @author Juliano Felipe
 * @author Maycon Queiroz
 */
public class Main {
    
    private static Usuario user = new Usuario(0, "Teste", Access.ROOT, new char[]{123}); //Para testes
    
    public static void main(String[] args) throws ParseException {
        //MAIN = assembleMain();
        
        System.out.println(SDate.sigmaDateFormat("2017-02-12T13:46:20"));
        
        //new TelaPrincipal().setVisible(true);
        //new Login().setVisible(true);
    }
}

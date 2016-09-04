package tolteco.sigma.model.dao.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.FinancaDAO;
import tolteco.sigma.model.entidades.Financa;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCFinancaDAO extends JDBCAbstractDAO implements FinancaDAO {

    @Override
    public boolean insert(Financa t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Financa t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Financa t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Financa> selectAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Financa search(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Financa> select(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 04/09 - Maycon Funcao que retorna um Array de registros para serem
     * insridos no Latex
     *
     * @param tipo
     * @return
     * @throws DatabaseException
     */
    public List<Financa> toReport(int tipo) throws DatabaseException {
        String data = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        if (tipo == 0) { //Mensal
            int mes = Integer.parseInt(data.substring(5, 7)) - 1;
            if (mes == 0) {
                mes = 12;
            }
            String data2 = data.substring(0, 4) + "-" +  mes + "-" + data.substring(8, 10);
            String sql = "SELECT * FROM Financa WHERE date BETWEEN \"" + data + "\" AND \"" + data2 + "\""; //Erro de agrupamento de data
            //Falta o resto... esperando codigo
        } else if (tipo == 1) { //Anual
            int ano = Integer.parseInt(data.substring(0, 4)) - 1;
            String data2 = ano + "-" + data.substring(5, 7) + "-" + data.substring(8, 10);
            String sql = "SELECT * FROM Financa WHERE date BETWEEN \"" + data + "\" AND \"" + data2 + "\""; //Erro de agrupamento de data
            //Falta o resto... esperando codigo
        }
        return null;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCClienteDAO {
    
}

/*
package tolteco.sigma.model.dao.jdbc;

import br.unioeste.sgev.model.Evento;
import br.unioeste.sgev.model.dao.DatabaseException;
import br.unioeste.sgev.model.dao.EventoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class JDBCEventoDAO extends JDBCAbstractDAO 
        implements EventoDAO {

    public JDBCEventoDAO() {
        super();
    }
    
    private int getNextCodigo() throws SQLException {
        String sql = "select nextval('tb_eventos_codigo_seq')";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        rs.next();
        return rs.getInt("nextval");
    }
    
    private Evento getInstance(ResultSet rs) throws SQLException {
        Evento evento = new Evento();
        evento.setCodigo(rs.getInt("codigo"));
        evento.setNome(rs.getString("nome"));
        return evento;        
    }

    @Override
    public List<Evento> listar(Date inicio, Date fim) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserir(Evento t) throws DatabaseException {
        String sql = "insert into tb_eventos(nome)"
                + " values (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, t.getNome());
            
            int r = ps.executeUpdate();
            ps.close();
            return r == 1;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean remover(Evento t) throws DatabaseException {
        String sql = "DELETE FROM tb_eventos " +
                     " WHERE codigo=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, t.getCodigo());
            int r = ps.executeUpdate();
            ps.close();
            return r == 1;
         } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean atualizar(Evento t) throws DatabaseException {
        String sql = "UPDATE tb_eventos " +
                " SET nome=?" +
                " WHERE codigo=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, t.getNome());
            ps.setInt(2, t.getCodigo());
            int r = ps.executeUpdate();
            ps.close();
            return r == 1;
         } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Evento> listarTodos() throws DatabaseException {
        String sql = "Select * from tb_eventos order by nome";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Evento> eventos = new ArrayList<>();
            while (rs.next()) {
                eventos.add( getInstance(rs));
            }
            rs.close();
            ps.close();
            return eventos;            
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Evento buscar(int codigo) throws DatabaseException {
        String sql = "SELECT * FROM tb_eventos WHERE codigo=?";
        PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
            // prepared statement para busca
            stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setInt(1,codigo);

            // executa um select
            rs = stmt.executeQuery();
	    Evento evento = null;
	    if (rs.next()) {
		evento = getInstance(rs);
            }			
            rs.close();
            stmt.close();
            return evento;
	} catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
	}
    }

    @Override
    public List<Evento> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
*/

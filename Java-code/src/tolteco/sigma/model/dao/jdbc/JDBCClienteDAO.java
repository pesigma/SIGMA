/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.sql.PreparedStatement;
import java.util.List;
import tolteco.sigma.model.dao.ClienteDAO;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.entidades.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCClienteDAO extends JDBCAbstractDAO implements ClienteDAO{

    @Override
    public boolean insert(Cliente t) throws DatabaseException {
        Connection conn = ConnectionFactory.getConnection();
        String query = "INSERT INTO cliente " + 
                "(userId,Primeiro_Nome,Ultimo_Nome,CPF,Telefone,Endereco,Observacoes) " + 
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
             
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, t.getUserId());
            pst.setString(2, t.getNome());
            pst.setString(3, t.getSobrenome());
            pst.setString(4, t.getCpf());
            pst.setString(5, t.getTel());
            pst.setString(6, t.getEnd());
            pst.setString(7, t.getObs());
            pst.execute();

        } catch (Exception e) {
            //String error = e.getClass().getName() + ": " + e.getMessage();
            throw new DatabaseException(e);
        }  finally {
            if (pst != null) 
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new DatabaseException(ex);
                }
        }
        
        return true;
    }

    @Override
    public boolean remove(Cliente t) throws DatabaseException {
        Connection conn = ConnectionFactory.getConnection();
        String query = "DELETE FROM Cliente WHERE clienteId="+t.getClienteId();
        PreparedStatement pst = null;
             
        try {
            pst = conn.prepareStatement(query);            
            pst.execute();

        } catch (Exception e) {
            //String error = e.getClass().getName() + ": " + e.getMessage();
            throw new DatabaseException(e);
        }  finally {
            if (pst != null) 
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new DatabaseException(ex);
                }
        }
        
        return true;
    }

    @Override
    public boolean update(Cliente t) throws DatabaseException {
        Connection conn = ConnectionFactory.getConnection();
        String query = "UPDATE Cliente " + 
                "SET userId=?, Primeiro_Nome=?, Ultimo_Nome=?, CPF=?, " + 
                "Telefone=?, Endereco=?, Observacoes=? " + 
                "WHERE clienteId=" + t.getClienteId();
        PreparedStatement pst = null;
             
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, t.getUserId());
            pst.setString(2, t.getNome());
            pst.setString(3, t.getSobrenome());
            pst.setString(4, t.getCpf());
            pst.setString(5, t.getTel());
            pst.setString(6, t.getEnd());
            pst.setString(7, t.getObs());
            pst.execute();
        } catch (Exception e) {
            //String error = e.getClass().getName() + ": " + e.getMessage();
            throw new DatabaseException(e);
        }  finally {
            if (pst != null) 
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new DatabaseException(ex);
                }
        }
        
        return true;
    }

    @Override
    public List<Cliente> selectAll() throws DatabaseException {       
        List<Cliente> lista = new ArrayList<>();
        
        Connection conn = ConnectionFactory.getConnection();
        String query = "SELECT clienteId, * FROM Cliente";
        PreparedStatement pst = null;
        ResultSet rs=null;
        
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            int userId, clienteId;                  //Atributos temporarios para 
            String Primeiro_Nome, Ultimo_Nome, CPF,//um cliente,
                   Telefone, Endereco, Observacoes;//para add na lista
            Cliente cliente;
            
            while (rs.next()){
                userId = rs.getInt("userId");
                clienteId = rs.getInt("clienteId");
                Primeiro_Nome = rs.getString("Primeiro_Nome");
                Ultimo_Nome = rs.getString("Ulitmo_Nome");
                CPF = rs.getString("CPF");
                Telefone = rs.getString("Telefone");
                Endereco = rs.getString("Endereco");
                Observacoes = rs.getString("Observacoes");
                
                cliente = new Cliente(Primeiro_Nome, Ultimo_Nome,
                                     Observacoes, Endereco, Telefone,
                                     CPF, userId);
                cliente.setClienteId(clienteId);
                
                lista.add(cliente);
            }
            rs.close();
            
        } catch (Exception e) {
            //String error = e.getClass().getName() + ": " + e.getMessage();
            throw new DatabaseException(e);
        }  finally {
            if (pst != null) 
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new DatabaseException(ex);
                }
        }
        
        return lista;
    }

    @Override
    public Cliente search(int primaryKey) throws DatabaseException {
        Cliente cliente;
        
        Connection conn = ConnectionFactory.getConnection();
        String query = "SELECT clienteId, * FROM Cliente WHERE clienteId=?";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, primaryKey);
            rs = pst.executeQuery();
            
            cliente = new Cliente(
                rs.getString("Primeiro_Nome"),
                rs.getString("Ulitmo_Nome"),
                rs.getString("Observacoes"),
                rs.getString("Endereco"),
                rs.getString("Telefone"),
                rs.getString("CPF"),
                rs.getInt("userId")
            );

            cliente.setClienteId( rs.getInt("clienteId") );
            
            rs.close();
            
        } catch (Exception e) {
            //String error = e.getClass().getName() + ": " + e.getMessage();
            throw new DatabaseException(e);
        }  finally {
            if (pst != null) 
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new DatabaseException(ex);
                }
        }
        
        return cliente;
    }

    @Override
    public List<Cliente> select(String nome) throws DatabaseException {
        List<Cliente> lista = new ArrayList<>();
        
        Connection conn = ConnectionFactory.getConnection();
        String query = "SELECT clienteId, * FROM Cliente WHERE nome=?";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            
            int userId, clienteId;                  //Atributos temporarios para 
            String Primeiro_Nome, Ultimo_Nome, CPF,//um cliente,
                   Telefone, Endereco, Observacoes;//para add na lista
            Cliente cliente;
            
            while (rs.next()){
                userId = rs.getInt("userId");
                clienteId = rs.getInt("clienteId");
                Primeiro_Nome = rs.getString("Primeiro_Nome");
                Ultimo_Nome = rs.getString("Ulitmo_Nome");
                CPF = rs.getString("CPF");
                Telefone = rs.getString("Telefone");
                Endereco = rs.getString("Endereco");
                Observacoes = rs.getString("Observacoes");
                
                cliente = new Cliente(Primeiro_Nome, Ultimo_Nome,
                                     Observacoes, Endereco, Telefone,
                                     CPF, userId);
                cliente.setClienteId(clienteId);
                
                lista.add(cliente);
            }
            rs.close();
            
        } catch (Exception e) {
            //String error = e.getClass().getName() + ": " + e.getMessage();
            throw new DatabaseException(e);
        }  finally {
            if (pst != null) 
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new DatabaseException(ex);
                }
        }
        
        return lista;
    }
    
}

//<editor-fold defaultstate="collapsed" desc="Exemplo Prof. Luiz">
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
//</editor-fold>

package tolteco.sigma.model.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.FinancaDAO;
import tolteco.sigma.model.entidades.Financa;
import tolteco.sigma.model.entidades.FinancaTipo;
import tolteco.sigma.model.entidades.Situacao;
import tolteco.sigma.utils.SDate;
import tolteco.sigma.view.Sistema;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCFinancaDAO extends JDBCAbstractDAO<Financa> implements FinancaDAO {

    @Override
    public boolean insert(Financa t) throws DatabaseException {
        String query = "INSERT INTO Financa " + 
                "(userId,Tipo,Data,Valor,Situacao,Observacoes) " + 
                "VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);
            pst.setInt   (1, Sistema.getUserID());
            pst.setInt   (2, t.getTipo().getCodigo());
            pst.setString(3, t.getDateToDatabase());
            pst.setDouble(4, t.getValor());
            pst.setInt   (5, t.getSituacao().getCodigo());
            pst.setString(6, t.getObs());
            pst.execute();

        } catch (SQLException e) {
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
    public boolean remove(Financa t) throws DatabaseException {
        String query = "DELETE FROM Financa WHERE financaId=" + t.getRowid();
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);            
            pst.execute();

        } catch (SQLException e) {
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
    public boolean update(Financa t) throws DatabaseException {
        String query = "UPDATE Financa " + 
                "SET userId=?, Tipo=?, Data=?, " + 
                "Valor=?, Situacao=?, Observacoes=? " + 
                "WHERE financaId=" + t.getRowid();
        PreparedStatement pst = null;
             
        try {
            pst = connection.prepareStatement(query);
            pst.setInt   (1, Sistema.getUserID());
            pst.setInt   (2, t.getTipo().getCodigo());
            pst.setString(3, t.getDateToDatabase());
            pst.setDouble(4, t.getValor());
            pst.setInt   (5, t.getSituacao().getCodigo());
            pst.setString(6, t.getObs());
            pst.execute();
        } catch (SQLException e) {
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
    public List<Financa> selectAll() throws DatabaseException {
        List<Financa> lista = new ArrayList<>();
        
        String query = "SELECT financaId, * FROM Financa";
        PreparedStatement pst = null;
        ResultSet rs=null;
        
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()){
                lista.add(getInstance(rs));
            }
            rs.close();
            
        } catch (SQLException | DatabaseException e) {
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
    public Financa search(int primaryKey) throws DatabaseException {
        Financa cliente;
        
        String query = "SELECT financaId, * FROM Financa WHERE financaId=?";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, primaryKey);
            rs = pst.executeQuery();
            
            cliente = getInstance(rs);
            
            rs.close();
            
        } catch (SQLException | DatabaseException e) {
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
    public List<Financa> select(String nome) throws DatabaseException {
        List<Financa> lista = new ArrayList<>();
        
        String query = "SELECT financaId, * FROM Financa WHERE" +
                 " (Observacoes LIKE '%" + nome + "%')";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()){   
                lista.add(getInstance(rs));
            }
            rs.close();
            
        } catch (SQLException | DatabaseException e) {
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
    public List<Financa> select(Date date) throws DatabaseException {
        List<Financa> lista = new ArrayList<>();
        
        String query = "SELECT financaId, * FROM Financa WHERE"
               + " (Data LIKE '%" + SDate.sigmaSmallDateFormat(date) + "%')";
        PreparedStatement pst = null;
        ResultSet rs;
        
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()){   
                lista.add(getInstance(rs));
            }
            rs.close();
            
        } catch (SQLException | DatabaseException e) {
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

    /**
     * 04/09 - Maycon Funcao que retorna um Array de registros para serem
     * insridos no Latex
     *
     * @param tipo
     * @return
     * @throws DatabaseException
     */
    @Override
    public List<Financa> toReport(int tipo) throws DatabaseException {
        //String data = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        String data = SDate.sigmaSmallDateFormat(new Date());
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

    @Override
    protected Financa getInstance(ResultSet rs) throws DatabaseException{
        try {
            return new Financa(
                    rs.getInt("financaId"),
                    FinancaTipo.porCodigo(rs.getInt("Tipo")),
                    SDate.sigmaDateFormat(rs.getString("Data")),
                    rs.getDouble("Valor"),
                    rs.getString("Observacoes"),
                    Situacao.porCodigo(rs.getInt("Situacao")),
                    rs.getInt("userId"));
        } catch (SQLException | ParseException ex) {
            //Logger.getLogger(JDBCFinancaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex);
        }
    }

}

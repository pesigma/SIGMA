/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.ServicoDAO;
import tolteco.sigma.model.entidades.Servico;
import tolteco.sigma.model.entidades.Situacao;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCServicoDAO extends JDBCAbstractDAO<Servico> implements ServicoDAO{

    @Override
    public boolean insert(Servico t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Servico t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Servico t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Servico> selectAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Servico search(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Servico> select(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Servico getInstance(ResultSet rs) throws DatabaseException{
        try {
            return new Servico(
                    rs.getInt("sevicoId"),
                    rs.getInt("clienteId"),
                    rs.getString("Placa"),
                    rs.getString("Modelo"),
                    rs.getDouble("Quilometragem"),
                    Situacao.porCodigo(rs.getInt("Situacao")),
                    rs.getString("Observacoes"),
                    rs.getInt("userId"));
        } catch (SQLException ex) {
            //Logger.getLogger(JDBCClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex);
        }
    }
    
}

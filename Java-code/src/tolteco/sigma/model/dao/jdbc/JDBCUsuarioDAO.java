/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.util.List;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.UsuarioDAO;
import tolteco.sigma.model.entidades.Usuario;

/**
 *
 * @author Juliano_Felipe
 */
public class JDBCUsuarioDAO extends JDBCAbstractDAO implements UsuarioDAO{

    @Override
    public boolean insert(Usuario t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Usuario t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Usuario t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> selectAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario search(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> select(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Retorna uma instância de um usuário.
     * @return usuário instânciado.
     */
    private Usuario getInstance(){
        return null;
    }   
}

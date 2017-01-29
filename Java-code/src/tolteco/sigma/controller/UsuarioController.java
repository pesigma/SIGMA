/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.UsuarioDAO;
import tolteco.sigma.model.entidades.Usuario;

/**
 *
 * @author Juliano Felipe
 */
public class UsuarioController extends GenericController<Usuario>{

    private final UsuarioDAO usuarioDAO;
    
    public UsuarioController(DAOFactory dao) {
        super(dao);
        usuarioDAO = dao.getUsuarioDAO();
    }

    @Override
    public boolean insert(Usuario t) throws DatabaseException {
        return usuarioDAO.insert(t);
    }

    @Override
    public boolean remove(Usuario t) throws DatabaseException {
        return usuarioDAO.remove(t);
    }

    @Override
    public boolean update(Usuario t) throws DatabaseException {
        return usuarioDAO.update(t);
    }

    @Override
    public List<Usuario> selectAll() throws DatabaseException {
        return usuarioDAO.selectAll();
    }

    @Override
    public Usuario search(int primaryKey) throws DatabaseException {
        return usuarioDAO.search(primaryKey);
    }

    @Override
    public List<Usuario> select(String nome) throws DatabaseException {
        return usuarioDAO.select(nome);
    }
    
}

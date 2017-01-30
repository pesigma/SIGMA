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
import tolteco.sigma.model.entidades.Servico;
import tolteco.sigma.model.entidades.Usuario;
import tolteco.sigma.model.tables.SigmaAbstractTableModel;
import tolteco.sigma.model.tables.UsuarioTable;

/**
 *
 * @author Juliano Felipe
 */
public class UsuarioController extends GenericController<Usuario, UsuarioTable>{

    private final UsuarioDAO usuarioDAO;

    public UsuarioController(DAOFactory dao, SigmaAbstractTableModel model) {
        super(dao, model);
        usuarioDAO = dao.getUsuarioDAO();
    }
    
    @Override
    public boolean insert(Usuario t) throws DatabaseException {
        boolean ins = usuarioDAO.insert(t);
        
        if (ins){
            List<Usuario> usuarios = usuarioDAO.select(t.getUserName());
            int key = -1;
            
            for (Usuario usuario : usuarios){
                if (usuario.equals(t)){
                    key = usuario.getUserId();
                    t = usuario;
                }
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na inserção de usuario. Obtenção de código de inserção"
                + " falhou.");
            } else{
                model.addRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na inserção de usuario. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public boolean remove(Usuario t) throws DatabaseException {
        boolean rem = usuarioDAO.remove(t);
        
        if (rem){
            Usuario usuario = usuarioDAO.search(t.getUserId());
            int key = -1;
            if (usuario.equals(t)){
                key = usuario.getUserId();
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na remoção de usuário. Obtenção de ID falhou.");
            } else{
                model.removeRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na remoção de usuário. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
    }

    @Override
    public boolean update(Usuario t) throws DatabaseException {
        boolean upd = usuarioDAO.update(t);
        
        if (upd){
            Usuario usuario = usuarioDAO.search(t.getUserId());
            int key = -1;
            if (usuario.equals(t)){
                key = usuario.getUserId();
            }
            
            if (key==-1){ 
                throw new DatabaseException(
                "Falha na atualização de usuário. Obtenção de ID falhou.");
            } else{
                model.setRow(t);
            }
            
        } else {
            throw new DatabaseException(
                "Falha na atualização de usuário. Persistência no banco de dados"
                + " falhou.");
        }
        
        return false;
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

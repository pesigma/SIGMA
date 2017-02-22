/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import java.util.logging.Level;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.UsuarioDAO;
import tolteco.sigma.model.entidades.Usuario;
import tolteco.sigma.model.tables.UsuarioTable;
import tolteco.sigma.view.MainFrame;

/**
 *
 * @author Juliano Felipe
 */
public class UsuarioController extends GenericController<Usuario, UsuarioTable>{
    private final UsuarioTable model;
    private final UsuarioDAO usuarioDAO;

    @Override
    protected final void initTable() throws DatabaseException {
        if (model == null) System.out.println("ÇLAJÇLKSJDFÇLKSDKFLJ");
         model.addAll(usuarioDAO.selectAll());
    }
    
    public UsuarioController(DAOFactory dao, UsuarioTable model){
        super(dao);
        usuarioDAO = dao.getUsuarioDAO();
        this.model = model;
        
        //initTable();
    }
    
    @Override
    public int insert(Usuario t) throws DatabaseException {
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int ins = usuarioDAO.insert(t);
        
        if (ins != -1){      
            model.addRow(t);      
        } else {
            throw new DatabaseException(
                "Falha na inserção de usuario. Persistência no banco de dados"
                + " falhou.");
        }
        
        return ins;
    }

    @Override
    public boolean remove(Usuario t) throws DatabaseException {
        if (!isTableInitialized) initTable(); isTableInitialized=true;
        int key = t.getUserId();
        boolean rem = usuarioDAO.remove(t);
        
        if (rem){
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
        if (!isTableInitialized) initTable(); isTableInitialized=true;
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
    
    @Override
    public UsuarioTable getModel() {
        if (!isTableInitialized){
            try {
                initTable();
            } catch (DatabaseException ex) {
                MainFrame.LOG.log(Level.SEVERE, "Falha ao inicializar tabela.");
            } 
            isTableInitialized=true;
        }
        
        return model;
    }

}

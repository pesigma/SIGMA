/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.tables.SigmaAbstractTableModel;
import tolteco.sigma.utils.eventsAndListeners.ChangePropertyEvent;
import tolteco.sigma.utils.eventsAndListeners.DeletionEvent;
import tolteco.sigma.utils.eventsAndListeners.InsertionEvent;
import tolteco.sigma.utils.eventsAndListeners.SearchEvent;
import tolteco.sigma.utils.eventsAndListeners.SelectMultipleEvent;
import tolteco.sigma.utils.eventsAndListeners.SigmaListener;

/**
 * Controlador genérico do sistema.
 * @author Juliano Felipe
 * @param <T> Entidade para controle.
 */
public abstract class GenericController<T, Model extends SigmaAbstractTableModel> implements SigmaListener{
        
    protected DAOFactory dao = null;
    protected SigmaAbstractTableModel model = null;
    
    protected GenericController(DAOFactory dao, SigmaAbstractTableModel model) {
        this.dao = dao;
        this.model = model;
    }
    
    public abstract boolean insert(T t) throws DatabaseException;
    
    public abstract boolean remove(T t) throws DatabaseException;
    
    public abstract boolean update(T t) throws DatabaseException;
    
    public abstract List<T> selectAll() throws DatabaseException;
    
    public abstract T search(int primaryKey) throws DatabaseException;
    
    public abstract List<T> select(String nome) throws DatabaseException; //Buscar

    @Override
    public boolean changePropertyEventHappened(ChangePropertyEvent event) {
        boolean result = false;
        try {
            result = update((T)event.getSource());
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean deletionEventHappened(DeletionEvent event) {
        boolean result = false;
        try {
            result = remove((T)event.getSource());
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean insertionEventHappened(InsertionEvent event) {
        boolean result = false;
        try {
            result = insert((T)event.getSource());
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public T searchEventHappened(SearchEvent event) {
        try {
            return search((int)event.getSource());
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<T> selectMultipleEventHappened(SelectMultipleEvent event) {
        List<T> lista = null;
        boolean select = event.isSelectAll();
        try {
            if (select){
                lista = selectAll();
            } else {
                lista = select((String)event.getSource());
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    
}

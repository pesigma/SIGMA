/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;

/**
 *
 * @author Juliano Felipe
 */
public abstract class GenericController<T> {
        
    protected DAOFactory dao = null;
    
    protected GenericController(DAOFactory dao) {
        this.dao = dao;
    }
    
    public abstract boolean insert(T t) throws DatabaseException;
    
    public abstract boolean remove(T t) throws DatabaseException;
    
    public abstract boolean update(T t) throws DatabaseException;
    
    public abstract List<T> selectAll() throws DatabaseException;
    
    public abstract T search(int primaryKey) throws DatabaseException;
    
    public abstract List<T> select(String nome) throws DatabaseException; //Buscar
}

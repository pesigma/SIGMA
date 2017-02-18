/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 * @param <T> Dado genérico para ser
 *            atribuído à um DAO.
 */
public interface GenericDAO<T> {
    
    int insert(T t) throws DatabaseException;
    
    boolean remove(T t) throws DatabaseException;
    
    boolean update(T t) throws DatabaseException;
    
    List<T> selectAll() throws DatabaseException;
    
    T search(int primaryKey) throws DatabaseException;
    
    List<T> select(String nome) throws DatabaseException; //Buscar
    
    int getNextId() throws DatabaseException;
}

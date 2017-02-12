/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import tolteco.sigma.model.dao.DatabaseException;

/**
 *
 * @author Juliano Felipe da Silva
 */
public abstract class JDBCAbstractDAO<T> {
    protected Connection connection;
    
    public JDBCAbstractDAO() {
        connection = ConnectionFactory.getConnection();
    }
    
    protected abstract T getInstance(ResultSet rs) throws DatabaseException;
}

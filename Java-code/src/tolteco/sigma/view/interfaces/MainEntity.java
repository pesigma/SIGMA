/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view.interfaces;

import tolteco.sigma.controller.GenericController;
import tolteco.sigma.model.dao.DatabaseException;

/**
 *
 * @author Juliano Felipe
 * @param <T>
 */
public interface MainEntity<T extends GenericController> {
    public void displayException(Exception ex);
    public void displayDatabaseException(DatabaseException ex);
    
}

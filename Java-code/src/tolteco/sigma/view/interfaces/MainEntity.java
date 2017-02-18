/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view.interfaces;

import tolteco.sigma.controller.GenericController;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.tables.SigmaAbstractTableModel;

/**
 *
 * @author Juliano Felipe
 * @param <T>
 * @param <X>
 */
public interface MainEntity<T extends GenericController,X> {
    void displayException(Exception ex);
    void displayDatabaseException(DatabaseException ex);
 
    GenericController getController();
    SigmaAbstractTableModel getModel();
    
    void pressEdit(X toFill);
}

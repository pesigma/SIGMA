/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

import java.util.Date;
import java.util.List;
import tolteco.sigma.model.entidades.Financa;

/**
 *
 * @author Juliano_Felipe
 */
public interface FinancaDAO extends GenericDAO<Financa>{
    public List<Financa> toReport(int tipo) throws DatabaseException;
    
    public List<Financa> select(Date date) throws DatabaseException;
}

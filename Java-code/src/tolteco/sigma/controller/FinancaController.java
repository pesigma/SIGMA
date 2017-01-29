/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.FinancaDAO;
import tolteco.sigma.model.entidades.Financa;

/**
 *
 * @author Juliano Felipe
 */
public class FinancaController extends GenericController<Financa>{

    private final FinancaDAO financaDAO;
    
    public FinancaController(DAOFactory dao) {
        super(dao);
        financaDAO = dao.getFinancaDAO();
    }

    @Override
    public boolean insert(Financa t) throws DatabaseException {
        return financaDAO.insert(t);
    }

    @Override
    public boolean remove(Financa t) throws DatabaseException {
        return financaDAO.insert(t);
    }

    @Override
    public boolean update(Financa t) throws DatabaseException {
        return financaDAO.insert(t);
    }

    @Override
    public List<Financa> selectAll() throws DatabaseException {
        return financaDAO.selectAll();
    }

    @Override
    public Financa search(int primaryKey) throws DatabaseException {
        return financaDAO.search(primaryKey);
    }

    @Override
    public List<Financa> select(String nome) throws DatabaseException {
        return financaDAO.select(nome);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.ServicoDAO;
import tolteco.sigma.model.entidades.Servico;

/**
 *
 * @author Juliano Felipe
 */
public class ServicoController extends GenericController<Servico>{

    private final ServicoDAO servicoDAO;
    
    public ServicoController(DAOFactory dao) {
        super(dao);
        
        servicoDAO = dao.getServicoDAO();
    }

    @Override
    public boolean insert(Servico t) throws DatabaseException {
        return servicoDAO.insert(t);
    }

    @Override
    public boolean remove(Servico t) throws DatabaseException {
        return servicoDAO.insert(t);
    }

    @Override
    public boolean update(Servico t) throws DatabaseException {
        return servicoDAO.insert(t);
    }

    @Override
    public List<Servico> selectAll() throws DatabaseException {
        return servicoDAO.selectAll();
    }

    @Override
    public Servico search(int primaryKey) throws DatabaseException {
        return servicoDAO.search(primaryKey);
    }

    @Override
    public List<Servico> select(String nome) throws DatabaseException {
        return servicoDAO.select(nome);
    }
    
}

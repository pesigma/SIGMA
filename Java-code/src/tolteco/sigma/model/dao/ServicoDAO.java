/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

import java.util.List;
import tolteco.sigma.model.entidades.Servico;

/**
 *
 * @author Juliano_Felipe
 */
public interface ServicoDAO extends GenericDAO<Servico>{
    /**
     * Procura de serviço por placa. Retorna uma lista de
     * serviços associados aquela placa.
     * 
     * @param placa a ser pesquisada.
     * @return lista de serviços encontrados
     * @throws DatabaseException em erro. 
     */
    List<Servico> searchByPlaca(String placa) throws DatabaseException;
}

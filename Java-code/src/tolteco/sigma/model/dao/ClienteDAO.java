/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;
import tolteco.sigma.model.entidades.Cliente;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface ClienteDAO extends GenericDAO<Cliente> {
    
    /**
     * Pesquisa por cpf.
     * 
     * @param cpf do cliente.
     * @return Cliente com o cpf passado.
     * @throws DatabaseException em erro.
     */
    Cliente searchByCPF(String cpf) throws DatabaseException;
}

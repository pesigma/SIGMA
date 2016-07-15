/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;
import java.util.List;
import tolteco.sigma.model.entidades.Cliente;
//import br.unioeste.sgev.model.pessoas.Palestrante;
//import br.unioeste.sgev.model.pessoas.Pessoa;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface ClienteDAO extends GenericDAO<Cliente> {
    
    List<Cliente> selectAllClientes() throws DatabaseException;
    
    //Collection<Inscrito> listarInscritos(); 
    
    //...
}

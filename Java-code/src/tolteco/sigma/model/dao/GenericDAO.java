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
    /**
     * Insere um objeto na persistência.
     * 
     * @param t Objeto a ser inserido.
     * @return -1 em erro ou ID em sucesso.
     * @throws DatabaseException em erro. 
     */
    int insert(T t) throws DatabaseException;
    
    /**
     * Método para remoção do banco. Utiliza apenas
     * o id.
     * 
     * @param t objeto a ser removido.
     * @return false em erro ou true em sucesso.
     * @throws DatabaseException em erro.
     */
    boolean remove(T t) throws DatabaseException;
    
    /**
     * Método para atualização no banco. Atualiza todos
     * os dados para não ser necessária a verificação
     * de qual(is) foi(am) alterado(s).
     * 
     * @param t objeto a ser inserido.
     * @return false em erro ou true em sucesso.
     * @throws DatabaseException em erro.
     */
    boolean update(T t) throws DatabaseException;
    
    /**
     * Seleção de todos os objetos do banco de dados.
     * 
     * @return Lista com todos os objetos do banco.
     * @throws DatabaseException em erro. 
     */
    List<T> selectAll() throws DatabaseException;
    
    /**
     * Procura do objeto que possui a chave
     * primária passada.
     * 
     * @param primaryKey identificadora do objeto.
     * @return objeto com a chave ou null em falha.
     * @throws DatabaseException em erro. 
     */
    T search(int primaryKey) throws DatabaseException;
    
    /**
     * Procura todos os objetos em que seu atributo principal
     * String (e.g.: Nome do Cliente, Modelo do serviço)
     * possua como substring a String passada.
     * 
     * @param nome String de procura.
     * @return Lista com os objetos encontrados.
     * @throws DatabaseException em erro. 
     */
    List<T> select(String nome) throws DatabaseException; //Buscar
    
    /**
     * Obtém o próximo ID disponível para
     * inserção.
     * 
     * @return -1 em erro ou ID em sucesso.
     * @throws DatabaseException em erro. 
     */
    int getNextId() throws DatabaseException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tolteco.sigma.model.dao.DAOFactory;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.tables.SigmaAbstractTableModel;
import tolteco.sigma.utils.eventsAndListeners.ChangePropertyEvent;
import tolteco.sigma.utils.eventsAndListeners.DeletionEvent;
import tolteco.sigma.utils.eventsAndListeners.InsertionEvent;
import tolteco.sigma.utils.eventsAndListeners.SearchEvent;
import tolteco.sigma.utils.eventsAndListeners.SelectMultipleEvent;
import tolteco.sigma.utils.eventsAndListeners.SigmaListener;

/**
 * Controlador genérico do sistema.
 * @author Juliano Felipe
 * @param <T> Entidade para controle.
 * @param <Model> Modelo de tabela do Sigma do
 *                respectivo tipo de entidade.
 */
public abstract class GenericController<T, Model extends SigmaAbstractTableModel<T>> implements SigmaListener{
    /**
     * Factory para a obtenção dos
     * DAOs para os respectivos tipos de entidades.
     */
    protected DAOFactory dao = null;
    //protected SigmaAbstractTableModel model = null;
    
    protected GenericController(DAOFactory dao/*, SigmaAbstractTableModel model*/) {
        this.dao = dao;
        //this.model = model;
    }
    
    /**
     * Método para inserção no banco. Retorna o 
     * id em sucesso ou -1 em falha.
     * @param t objeto a ser inserido.
     * @return -1 em erro ou o id inserido.
     * @throws DatabaseException em erro.
     */
    public abstract int insert(T t) throws DatabaseException;
    
    /**
     * Método para remoção do banco. Utiliza apenas
     * o id.
     * 
     * @param t objeto a ser removido.
     * @return false em erro ou true em sucesso.
     * @throws DatabaseException em erro.
     */
    public abstract boolean remove(T t) throws DatabaseException;
    
    /**
     * Método para atualização no banco. Atualiza todos
     * os dados para não ser necessária a verificação
     * de qual(is) foi(am) alterado(s).
     * 
     * @param t objeto a ser inserido.
     * @return false em erro ou true em sucesso.
     * @throws DatabaseException em erro.
     */
    public abstract boolean update(T t) throws DatabaseException;
    
    /**
     * Seleção de todos os objetos do banco de dados.
     * 
     * @return Lista com todos os objetos do banco.
     * @throws DatabaseException em erro. 
     */
    public abstract List<T> selectAll() throws DatabaseException;
    
    /**
     * Procura do objeto que possui a chave
     * primária passada.
     * 
     * @param primaryKey identificadora do objeto.
     * @return objeto com a chave ou null em falha.
     * @throws DatabaseException em erro. 
     */
    public abstract T search(int primaryKey) throws DatabaseException;
    
    /**
     * Procura todos os objetos em que seu atributo principal
     * String (e.g.: Nome do Cliente, Modelo do serviço)
     * possua como substring a String passada.
     * 
     * @param nome String de procura.
     * @return Lista com os objetos encontrados.
     * @throws DatabaseException em erro. 
     */
    public abstract List<T> select(String nome) throws DatabaseException; //Buscar

    /**
     * Retorna o modelo de tabela referente a cada
     * tipo de entidade. 
     * @return Modelo de tabela.
     */
    public abstract SigmaAbstractTableModel<T> getModel();
    
    @Override
    public boolean changePropertyEventHappened(ChangePropertyEvent event) {
        boolean result = false;
        try {
            result = update((T)event.getSource());
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean deletionEventHappened(DeletionEvent event) {
        boolean result = false;
        try {
            result = remove((T)event.getSource());
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean insertionEventHappened(InsertionEvent event) {
        int result = -1;
        try {
            result = insert((T)event.getSource());
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result != -1;
    }

    @Override
    public T searchEventHappened(SearchEvent event) {
        try {
            return search((int)event.getSource());
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<T> selectMultipleEventHappened(SelectMultipleEvent event) {
        List<T> lista = null;
        boolean select = event.isSelectAll();
        try {
            if (select){
                lista = selectAll();
            } else {
                lista = select((String)event.getSource());
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    
}

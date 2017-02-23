/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.table.AbstractTableModel;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.entidades.PrimaryKeyComparable;
import tolteco.sigma.utils.eventsAndListeners.ChangePropertyEvent;
import tolteco.sigma.utils.eventsAndListeners.DeletionEvent;
import tolteco.sigma.utils.eventsAndListeners.InsertionEvent;
import tolteco.sigma.utils.eventsAndListeners.SigmaEvent;
import tolteco.sigma.utils.eventsAndListeners.SigmaListener;
import tolteco.sigma.view.MainFrame;
import tolteco.sigma.view.Sistema;

/**
 *
 * @author Juliano
 * @param <T>
 */
public abstract class SigmaAbstractTableModel<T extends PrimaryKeyComparable> extends AbstractTableModel{
    /**
     * Lista de listeners "ouvindo" alterações
     * na determinada tabela.
     * 
     * Lista utilizada pois um listener pode
     * remover a si mesmo ao estar iterando
     * sobre a lista de listeners.
     */
    private final List<SigmaListener> listeners = new CopyOnWriteArrayList();
    
    /**
     * Lista de entidades pertencentes à tabela.
     */
    protected List<T> entidades = new ArrayList<>();
    
    protected final List<T> getList(){
        return entidades;
    }
    
    /**
     * Retorna o objeto que está
     * na linha "row".
     * @param row Linha para obter
     *            o objeto.
     * @return    Objeto na linha "row".
     */
    public T getRow(int row){
        return entidades.get(row);
    }
    
    public T getRowById(int id){
        for (T entidade : entidades){
            if (entidade.getRowId() == id)
                return entidade;
        }
        return null;
    }
    
    public void setRow(T object, int row){
        entidades.add(row, object);
        fireTableRowsDeleted(row, row);
        fireChangeProperty(new ChangePropertyEvent(object));
        MainFrame.LOG.info("Objeto alterado. Classe:" + object.getClass() +
                ". Linha: " + row + " Usuário: " + Sistema.getUser().getUserName());
    }
    
    public void setRow(T object) throws DatabaseException{
        int indexToUpdate = -1;
        int counter=0;
        for(T entidade : entidades){
            if (entidade.getRowId() == object.getRowId()){
                indexToUpdate = counter;
                break;
            }
            counter++;
        }
        
        if (indexToUpdate == -1){
            throw new DatabaseException("Objeto inexistente na tabela de "
                  + object.getClass() + ". Impossível atualizar.");
        }
        
        entidades.set(indexToUpdate, object);
        fireChangeProperty(new ChangePropertyEvent(object));
        fireTableRowsUpdated(indexToUpdate, indexToUpdate);
        //fireTableDataChanged();
        MainFrame.LOG.info("Objeto alterado. Classe:" + object.getClass() +
                ". Linha: " + indexToUpdate + " Usuário: " + 
                Sistema.getUser().getUserName());
    }
    
    public void addRow(T object){
        entidades.add(object);
        final int LASTROW = entidades.size()-1;
        fireTableRowsInserted(LASTROW, LASTROW);
        fireInsertion(new InsertionEvent(object));
        MainFrame.LOG.info("Objeto adicionado. Classe:" + object.getClass() +
                ". Linha: " + LASTROW + " Usuário: " + 
                Sistema.getUser().getUserName());
    }
    
    public void addAll(Collection<T> lista){
        entidades.addAll(lista);
        fireTableDataChanged();
        MainFrame.LOG.info("Coleção adicionada. Classe:" + lista.getClass() +
                ". Usuário: " + 
                Sistema.getUser().getUserName());
    }
    
    public void removeRow(T object){
        int indexToUpdate = -1;
        int counter=0;
        for(T entidade : entidades){
            if (entidade.equals(object)){
                indexToUpdate = counter;
                break;
            }
            counter++;
        }

        entidades.remove(object);
        fireDeletion(new DeletionEvent(object));
        fireTableRowsDeleted(indexToUpdate, indexToUpdate);
        MainFrame.LOG.info("Objeto removido. Classe:" + object.getClass() +
                ". Linha: " + indexToUpdate + " Usuário: " + 
                Sistema.getUser().getUserName());
    }
    
    public void removeRow(int row){
        T object = entidades.get(row);
        entidades.remove(row);
        fireDeletion(new DeletionEvent(object));
        fireTableRowsDeleted(row, row);
        MainFrame.LOG.info("Objeto removido. Classe:" + object.getClass() +
                ". Linha: " + row + " Usuário: " + 
                Sistema.getUser().getUserName());
    }
    
    /**
     * Retorna o número de elementos
     * na tabela. A lista pode (em teoria),
     * ultrapassar o valor {@link Integer#MAX_VALUE},
     * então se tal ocorrer, o valor máximo é retornado.
     * 
     * @return inteiro representando o número de elementos.
     */
    @Override
    public int getRowCount() {
        return entidades.size();
    }

    public abstract T search(int key);
    public abstract int search(T object);
    
    /**
     * Método para adicionar Listeners à
     * tabela.
     * @param listener SigmaListener.
     */
    public final void addListener(SigmaListener listener){
        listeners.add( listener );
    }

    /**
     * Método para remover Listeners da
     * tabela.
     * @param listener SigmaListener.
     */
    public final void removeListener(SigmaListener listener){
        listeners.remove( listener );
    }
    
    /**
     * Método para disparar um evento
     * genérico.
     * @param event evento genérico Sigma.
     */
    protected final void fireSigmaEvent(SigmaEvent event){
        listeners.forEach((listener) -> {
            listener.eventHappened(event);
        });
    }
    
    /**
     * Método para disparar um evento de 
     * alteração de propriedade.
     * @param event evento Sigma.
     */
    protected final void fireChangeProperty(ChangePropertyEvent event){
        listeners.forEach((listener) -> {
            listener.changePropertyEventHappened(event);
        });
    }
    
    /**
     * Método para disparar um evento de 
     * deleção.
     * @param event evento Sigma.
     */
    protected final void fireDeletion(DeletionEvent event){
        listeners.forEach((listener) -> {
            listener.deletionEventHappened(event);
        });
    }
    
    /**
     * Método para disparar um evento de 
     * inserção.
     * @param event evento Sigma.
     */
    protected final void fireInsertion(InsertionEvent event){
        listeners.forEach((listener) -> {
            listener.insertionEventHappened(event);
        });
    }
}

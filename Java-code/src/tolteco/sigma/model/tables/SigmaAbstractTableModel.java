/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.table.AbstractTableModel;
import tolteco.sigma.utils.eventsAndListeners.ChangePropertyEvent;
import tolteco.sigma.utils.eventsAndListeners.DeletionEvent;
import tolteco.sigma.utils.eventsAndListeners.InsertionEvent;
import tolteco.sigma.utils.eventsAndListeners.SigmaEvent;
import tolteco.sigma.utils.eventsAndListeners.SigmaListener;

/**
 *
 * @author Juliano
 * @param <T>
 */
public abstract class SigmaAbstractTableModel<T> extends AbstractTableModel implements SigmaTableModel<T> {
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
    private List<T> entidades;
    
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
    
    public void setRow(T object, int row){
        entidades.add(row, object);
    }
    
    public void addRow(T object){
        entidades.add(object);
    }
    
    public void removeRow(T object){
        entidades.remove(object);
    }
    
    public void removeRow(int row){
        entidades.remove(row);
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

    /// MÉTODOS ABAIXO PRECISAM SER IMPLEMENTADOS POR CADA TIPO DE TABELA
    /*
    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
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

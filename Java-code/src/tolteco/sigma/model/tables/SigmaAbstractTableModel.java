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
    
    /// MÉTODOS ABAIXO PRECISAM SER IMPLEMENTADOS POR CADA TIPO DE TABELA
    /*@Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    public final void addListener(SigmaListener listener){
        listeners.add( listener );
    }

    public final void removeListener(SigmaListener listener){
        listeners.remove( listener );
    }
    
    protected final void fireSigmaEvent(SigmaEvent event){
        listeners.forEach((listener) -> {
            listener.eventHappened(event);
        });
    }
    
    protected final void fireChangeProperty(ChangePropertyEvent event){
        listeners.forEach((listener) -> {
            listener.changePropertyEventHappened(event);
        });
    }
    
    protected final void fireDeletion(DeletionEvent event){
        listeners.forEach((listener) -> {
            listener.deletionEventHappened(event);
        });
    }
    
    protected final void fireInsertion(InsertionEvent event){
        listeners.forEach((listener) -> {
            listener.insertionEventHappened(event);
        });
    }
}

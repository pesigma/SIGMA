/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils.eventsAndListeners;

import java.util.List;

/**
 * Interface de listeners do sistema.
 * @author JFPS
 */
public interface SigmaListener<T> {
    default public void eventHappened(SigmaEvent event){
               if (event instanceof ChangePropertyEvent){
            changePropertyEventHappened( (ChangePropertyEvent) event);
        } else if (event instanceof DeletionEvent){
            deletionEventHappened((DeletionEvent) event);
        } else if (event instanceof InsertionEvent){
            insertionEventHappened((InsertionEvent) event);
        } else {
            throw new IllegalStateException("Evento não catalogado.");
        }
    }
    
    public boolean changePropertyEventHappened(ChangePropertyEvent event);
    
    public boolean deletionEventHappened(DeletionEvent event);
    
    public boolean insertionEventHappened(InsertionEvent event);
    
    public T searchEventHappened(SearchEvent event);
    
    public List<T> selectMultipleEventHappened(SelectMultipleEvent event);
}

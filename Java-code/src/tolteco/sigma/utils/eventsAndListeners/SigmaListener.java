/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils.eventsAndListeners;

/**
 * Interface de listeners do sistema.
 * @author JFPS
 */
public interface SigmaListener {
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
    
    public void changePropertyEventHappened(ChangePropertyEvent event);
    
    public void deletionEventHappened(DeletionEvent event);
    
    public void insertionEventHappened(InsertionEvent event);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils.eventsAndListeners;

/**
 * Evento de retorno de listas.
 * 
 * @author Juliano Felipe
 */
public class SelectMultipleEvent extends SigmaEvent {
    
    private final boolean selectAll;
    
    public SelectMultipleEvent(Object source, boolean selectAll) {
        super(source);
        this.selectAll = selectAll;
    }

    public boolean isSelectAll() {
        return selectAll;
    }
    
    
    
}

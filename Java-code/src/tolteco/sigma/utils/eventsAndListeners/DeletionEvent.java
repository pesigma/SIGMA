/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils.eventsAndListeners;

/**
 * Evento do sistema, usado
 * para restringir o escopo em
 * passagem de parâmetros (e.g. 
 * Restringir para somente 
 * "SigmaEvent"s).
 * 
 * @author Juliano Felipe
 */
public class DeletionEvent extends SigmaEvent {
    
    public DeletionEvent(Object source) {
        super(source);
    }
    
}

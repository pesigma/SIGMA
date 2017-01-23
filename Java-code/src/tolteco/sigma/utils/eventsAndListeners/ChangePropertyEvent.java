/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils.eventsAndListeners;

/**
 * Evento onde uma ou mais propriedades do
 * objeto foram alteradas.
 * @author Juliano Felipe
 */
public class ChangePropertyEvent extends SigmaEvent {
    
    public ChangePropertyEvent(Object source) {
        super(source);
    }
    
}

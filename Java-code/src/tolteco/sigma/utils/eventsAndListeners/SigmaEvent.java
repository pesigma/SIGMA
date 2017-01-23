/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils.eventsAndListeners;

import java.util.EventObject;

/**
 *
 * @author JFPS
 */
public abstract class SigmaEvent extends EventObject{
    
    public SigmaEvent(Object source) {
        super(source);
    }
    
}

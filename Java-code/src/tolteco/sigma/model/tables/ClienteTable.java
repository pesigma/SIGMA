/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import tolteco.sigma.model.entidades.Cliente;
import tolteco.sigma.utils.eventsAndListeners.ChangePropertyEvent;

/**
 *
 * @author Juliano
 */
public class ClienteTable extends SigmaAbstractTableModel<Cliente>{
    private static final int COLUMN_COUNT = 8;
    private static final int NOME         = 0;
    private static final int SOBRENOME    = 1;
    private static final int OBS          = 2;
    private static final int ENDERECO     = 3;
    private static final int TELEFONE     = 4;
    private static final int CPF          = 5;
    private static final int CLIENTE_ID   = 6;
    private static final int USER_ID      = 7;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = getRow(rowIndex);
        switch(columnIndex){
            case NOME:       return cliente.getNome();
            case SOBRENOME:  return cliente.getSobrenome();
            case OBS:        return cliente.getObs();
            case ENDERECO:   return cliente.getEnd();
            case TELEFONE:   return cliente.getTel();
            case CPF:        return cliente.getCpf();
            case CLIENTE_ID: return cliente.getClienteId();
            case USER_ID:    return cliente.getUserId();
            default:
                throw new IndexOutOfBoundsException(
                    "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case NOME:       return String.class;
            case SOBRENOME:  return String.class;
            case OBS:        return String.class;
            case ENDERECO:   return String.class;
            case TELEFONE:   return String.class;
            case CPF:        return String.class;
            case CLIENTE_ID: return Integer.class;
            case USER_ID:    return Integer.class;
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case NOME:       return "Nome";
            case SOBRENOME:  return "Sobrenome";
            case OBS:        return "Observações";
            case ENDERECO:   return "Endereço";
            case TELEFONE:   return "Telefone";
            case CPF:        return "CPF";
            case CLIENTE_ID: return "ID Cliente";
            case USER_ID:    return "ID Usuário";
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + column +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente cliente = getRow(rowIndex);
        
        switch(columnIndex){
            case NOME:       
                cliente.setNome((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case SOBRENOME:  
                cliente.setSobrenome((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case OBS:        
                cliente.setObs((String) aValue);  
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case ENDERECO:  
                cliente.setEnd((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case TELEFONE:  
                cliente.setTel((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case CPF:        
                cliente.setCpf((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case CLIENTE_ID: 
                cliente.setClienteId((int) aValue);
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case USER_ID:    
                cliente.setUserId((int) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }      
    }

    @Override
    public Cliente search(int key) {
        for (Cliente cliente : getList()){
            if (cliente.getClienteId() == key)
                return cliente;
        }
        return null;
    }

    @Override
    public int search(Cliente object) {
        int DIDNOT_FIND_ROW=-1;
        int counter=0;
        for (Cliente cliente : getList()){
            if (cliente.equals(object))
                return counter;
            counter++;
        }
        return DIDNOT_FIND_ROW;
    }
}

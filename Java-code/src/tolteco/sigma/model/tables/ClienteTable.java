/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import tolteco.sigma.model.entidades.Cliente;

/**
 *
 * @author Juliano
 */
public class ClienteTable extends SigmaAbstractTableModel<Cliente>{
    private final int COLUMN_COUNT = 8;
    private final int NOME         = 0;
    private final int SOBRENOME    = 1;
    private final int OBS          = 2;
    private final int ENDERECO     = 3;
    private final int TELEFONE     = 4;
    private final int CPF          = 5;
    private final int CLIENTE_ID   = 6;
    private final int USER_ID      = 7;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = getValueAtRow(rowIndex);
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
                "Exceeded Max Column Count: " + columnIndex +  " out of " + columnIndex + ".");
        }
    }
}

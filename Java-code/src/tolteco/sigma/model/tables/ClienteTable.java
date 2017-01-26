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
            case NOME:       return "Placa";
            case SOBRENOME:  return "Modelo";
            case OBS:        return "Observações";
            case ENDERECO:   return "Quilometragem";
            case TELEFONE:   return "ID Cliente";
            case CPF:        return "Situação";
            case CLIENTE_ID: return "ID Serviço";
            case USER_ID:    return "ID Serviço";
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + column +  " out of " + COLUMN_COUNT + ".");
        }
    }
}

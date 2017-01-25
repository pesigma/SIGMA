/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import tolteco.sigma.model.entidades.Financa;

/**
 *
 * @author Juliano
 */
public class FinancaTable extends SigmaAbstractTableModel<Financa>{
    private final int COLUMN_COUNT = 6;
    private final int DATA         = 0;
    private final int OBS          = 1;
    private final int SITUACAO     = 2;
    private final int TIPO         = 3;
    private final int VALOR        = 4;
    private final int ROWID        = 5;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Financa financa = getValueAtRow(rowIndex);
        switch(columnIndex){
            case DATA:     return financa.getData();
            case OBS:      return financa.getObs();
            case SITUACAO: return financa.getSituacao();
            case TIPO:     return financa.getTipo();
            case VALOR:    return financa.getValor();
            case ROWID:    return financa.getRowid();
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + columnIndex + ".");
        }
    }
    
}

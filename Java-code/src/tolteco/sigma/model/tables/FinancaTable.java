/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import java.util.Date;
import tolteco.sigma.model.entidades.Financa;
import tolteco.sigma.model.entidades.FinancaTipo;
import tolteco.sigma.model.entidades.Situacao;

/**
 *
 * @author Juliano
 */
public class FinancaTable extends SigmaAbstractTableModel<Financa>{
    private static final int COLUMN_COUNT = 6;
    private static final int DATA         = 0;
    private static final int OBS          = 1;
    private static final int SITUACAO     = 2;
    private static final int TIPO         = 3;
    private static final int VALOR        = 4;
    private static final int ROWID        = 5;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Financa financa = getRow(rowIndex);
        switch(columnIndex){
            case DATA:     return financa.getData();
            case OBS:      return financa.getObs();
            case SITUACAO: return financa.getSituacao();
            case TIPO:     return financa.getTipo();
            case VALOR:    return financa.getValor();
            case ROWID:    return financa.getRowid();
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case DATA:     return Date.class;
            case OBS:      return String.class;
            case SITUACAO: return Situacao.class;
            case TIPO:     return FinancaTipo.class;
            case VALOR:    return Double.class;
            case ROWID:    return Integer.class;
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case DATA:     return "Data";
            case OBS:      return "Observações";
            case SITUACAO: return "Situação";
            case TIPO:     return "Tipo";
            case VALOR:    return "Valor";
            case ROWID:    return "ID Finança";
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + column +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Financa financa = getRow(rowIndex);
        
        switch(columnIndex){
            case DATA:     financa.setData((Date) aValue);
            case OBS:      financa.setDesc((String) aValue);
            case SITUACAO: financa.setSituacao((Situacao) aValue);
            case TIPO:     financa.setTipo((FinancaTipo) aValue);
            case VALOR:    financa.setValor((double) aValue);
            case ROWID:    financa.setRowid((int) aValue);
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }   
}

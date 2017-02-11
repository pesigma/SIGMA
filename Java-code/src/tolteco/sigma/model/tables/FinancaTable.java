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
import tolteco.sigma.utils.eventsAndListeners.ChangePropertyEvent;

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
            case DATA:     
                financa.setData((Date) aValue);
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case OBS:      
                financa.setDesc((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case SITUACAO: 
                financa.setSituacao((Situacao) aValue);
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case TIPO:     
                financa.setTipo((FinancaTipo) aValue);
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case VALOR:    
                financa.setValor((double) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case ROWID:    
                financa.setRowid((int) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }   
    
    @Override
    public Financa search(int key) {
        for (Financa financa : getList()){
            if (financa.getRowid() == key)
                return financa;
        }
        return null;
    }

    @Override
    public int search(Financa object) {
        int DIDNOT_FIND_ROW=-1;
        int counter=0;
        for (Financa financa : getList()){
            if (financa.equals(object))
                return counter;
            counter++;
        }
        return DIDNOT_FIND_ROW;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import tolteco.sigma.model.entidades.Servico;
import tolteco.sigma.model.entidades.Situacao;
import tolteco.sigma.utils.eventsAndListeners.ChangePropertyEvent;

/**
 *
 * @author Juliano
 */
public class ServicoTable extends SigmaAbstractTableModel<Servico>{
    public static final int COLUMN_COUNT = 7;
    public static final int PLACA        = 0;
    public static final int MODELO       = 1;
    public static final int OBS          = 2;
    public static final int KM           = 3;
    public static final int CLIENTE_ID   = 4;
    public static final int SITUACAO     = 5;
    public static final int ROWID        = 6;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Servico servico = getRow(rowIndex);
        switch(columnIndex){
            case PLACA:      return servico.getPlaca();
            case MODELO:     return servico.getModelo();
            case OBS:        return servico.getObs();
            case KM:         return servico.getKm();
            case CLIENTE_ID: return servico.getIdcliente();
            case SITUACAO:   return servico.getSituacao();
            case ROWID:      return servico.getRowid();
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case PLACA:      return String.class;
            case MODELO:     return String.class;
            case OBS:        return String.class;
            case KM:         return Integer.class;
            case CLIENTE_ID: return Integer.class;
            case SITUACAO:   return Situacao.class;
            case ROWID:      return Integer.class;
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case PLACA:      return "Placa";
            case MODELO:     return "Modelo";
            case OBS:        return "Observações";
            case KM:         return "Quilometragem";
            case CLIENTE_ID: return "ID Cliente";
            case SITUACAO:   return "Situação";
            case ROWID:      return "ID Serviço";
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + column +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Servico servico = getRow(rowIndex);
        
        switch(columnIndex){
            case PLACA:      
                servico.setPlaca((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case MODELO:     
                servico.setModelo((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case OBS:        
                servico.setObs((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case KM:         
                servico.setKm((int) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case CLIENTE_ID: 
                servico.setIdcliente((int) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case SITUACAO:   
                servico.setSituacao((Situacao) aValue);
                fireChangeProperty(new ChangePropertyEvent(aValue)); break; 
            case ROWID:      
                servico.setRowid((int) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }

    @Override
    public Servico search(int key) {
        for (Servico servico : getList()){
            if (servico.getRowid()== key)
                return servico;
        }
        return null;
    }

    @Override
    public int search(Servico object) {
        int DIDNOT_FIND_ROW=-1;
        int counter=0;
        for (Servico servico : getList()){
            if (servico.equals(object))
                return counter;
            counter++;
        }
        return DIDNOT_FIND_ROW;
    }
}

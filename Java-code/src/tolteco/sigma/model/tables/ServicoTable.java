/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import tolteco.sigma.model.entidades.Servico;
import tolteco.sigma.model.entidades.Situacao;

/**
 *
 * @author Juliano
 */
public class ServicoTable extends SigmaAbstractTableModel<Servico>{
    private final int COLUMN_COUNT = 7;
    private final int PLACA        = 0;
    private final int MODELO       = 1;
    private final int OBS          = 2;
    private final int KM           = 3;
    private final int CLIENTE_ID   = 4;
    private final int SITUACAO     = 5;
    private final int ROWID        = 6;
    
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
            case PLACA:      servico.setPlaca((String) aValue);
            case MODELO:     servico.setModelo((String) aValue);
            case OBS:        servico.setObs((String) aValue);
            case KM:         servico.setKm((int) aValue);
            case CLIENTE_ID: servico.setIdcliente((int) aValue);
            case SITUACAO:   servico.setSituacao((Situacao) aValue);
            case ROWID:      servico.setRowid((int) aValue);
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

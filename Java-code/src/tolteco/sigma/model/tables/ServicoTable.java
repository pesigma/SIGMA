/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import tolteco.sigma.model.entidades.Servico;

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
        Servico servico = getValueAtRow(rowIndex);
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
                "Exceeded Max Column Count: " + columnIndex +  " out of " + columnIndex + ".");
        }
    }
}

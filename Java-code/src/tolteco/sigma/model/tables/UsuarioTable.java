/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import tolteco.sigma.model.entidades.Usuario;

/**
 *
 * @author Juliano
 */
public class UsuarioTable extends SigmaAbstractTableModel<Usuario>{
    private final int COLUMN_COUNT = 4;
    private final int USER_ID      = 0;
    private final int USER_NAME    = 1;
    private final int ACCESS_LEVEL = 2;
    private final int PASS         = 3;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario user = getValueAtRow(rowIndex);
        switch(columnIndex){
            case USER_ID:      return user.getUserId();
            case USER_NAME:    return user.getUserName();
            case ACCESS_LEVEL: return user.getAccessLevel();
            case PASS:         return user.getPass();
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + columnIndex + ".");
        }
    }  
}

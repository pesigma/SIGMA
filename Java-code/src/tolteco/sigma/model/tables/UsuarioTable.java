/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import static java.sql.JDBCType.ROWID;
import tolteco.sigma.model.entidades.Access;
import tolteco.sigma.model.entidades.Usuario;

/**
 *
 * @author Juliano
 */
public class UsuarioTable extends SigmaAbstractTableModel<Usuario>{
    private static final int COLUMN_COUNT = 4;
    private static final int USER_ID      = 0;
    private static final int USER_NAME    = 1;
    private static final int ACCESS_LEVEL = 2;
    private static final int PASS         = 3;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario user = getRow(rowIndex);
        switch(columnIndex){
            case USER_ID:      return user.getUserId();
            case USER_NAME:    return user.getUserName();
            case ACCESS_LEVEL: return user.getAccessLevel();
            case PASS:         return user.getPass();
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }  
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case USER_ID:      return Integer.class;
            case USER_NAME:    return String.class;
            case ACCESS_LEVEL: return Access.class;
            case PASS:         return char[].class;
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case USER_ID:      return "ID Usuário";
            case USER_NAME:    return "Nome Usuário";
            case ACCESS_LEVEL: return "Nível de Acesso";
            case PASS:         return "Senha";
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + column +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Usuario usuario = getRow(rowIndex);
        
        switch(columnIndex){
            case USER_ID:      usuario.setUserId((int) aValue);
            case USER_NAME:    usuario.setUserName((String) aValue);
            case ACCESS_LEVEL: usuario.setAccessLevel((Access) aValue);
            case PASS:         usuario.setPass((char[]) aValue);
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public Usuario search(int key) {
        for (Usuario user : getList()){
            if (user.getUserId()== key)
                return user;
        }
        return null;
    }

    @Override
    public int search(Usuario object) {
        int DIDNOT_FIND_ROW=-1;
        int counter=0;
        for (Usuario user : getList()){
            if (user.equals(object))
                return counter;
            counter++;
        }
        return DIDNOT_FIND_ROW;
    }
}

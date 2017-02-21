/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import java.util.Date;
import tolteco.sigma.model.entidades.Version;
import tolteco.sigma.utils.eventsAndListeners.ChangePropertyEvent;

/**
 * Tabela de versão.
 * @author Juliano
 */
public class VersionTable extends SigmaAbstractTableModel<Version>{
    public static final int COLUMN_COUNT = 7;
    public static final int MAJOR_VER    = 0;
    public static final int MAJOR_NAME   = 1;
    public static final int MAJOR_DATE   = 2;
    public static final int MAJOR_NOTES  = 3;
    public static final int MINOR_VER    = 4;
    public static final int MINOR_DATE   = 5;
    public static final int MINOR_NOTES  = 6;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Version version = getRow(rowIndex);
        switch(columnIndex){
            case MAJOR_VER:   return version.getMajorVersion();
            case MAJOR_NAME:  return version.getMajorName();
            case MAJOR_DATE:  return version.getMajorDate();
            case MAJOR_NOTES: return version.getMajorNotes();
            case MINOR_VER:   return version.getMinorVersion();
            case MINOR_DATE:  return version.getMinorDate();
            case MINOR_NOTES: return version.getMinorNotes();
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case MAJOR_VER:   return Integer.class;
            case MAJOR_NAME:  return String.class;
            case MAJOR_DATE:  return Date.class;
            case MAJOR_NOTES: return String.class;
            case MINOR_VER:   return Integer.class;
            case MINOR_DATE:  return Date.class;
            case MINOR_NOTES: return String.class;
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case MAJOR_VER:   return "Major Version";
            case MAJOR_NAME:  return "Major Name";
            case MAJOR_DATE:  return "Major Date";
            case MAJOR_NOTES: return "Major Notes";
            case MINOR_VER:   return "Minor Version";
            case MINOR_DATE:  return "Minor Date";
            case MINOR_NOTES: return "Minor Notes";
            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + column +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Version version = getRow(rowIndex);
        
        switch(columnIndex){
            case MAJOR_VER:   
                version.setMajorVersion((int) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case MAJOR_NAME:  
                version.setMajorName((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case MAJOR_DATE:  
                version.setMajorDate((Date) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case MAJOR_NOTES: 
                version.setMajorNotes((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case MINOR_VER:   
                version.setMinorVersion((int) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case MINOR_DATE:  
                version.setMinorDate((Date) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;
            case MINOR_NOTES: 
                version.setMinorNotes((String) aValue); 
                fireChangeProperty(new ChangePropertyEvent(aValue)); break;

            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }
    
    @Override
    public Version search(int key) {
        for (Version version : getList()){
            if (version.getMajorVersion()== key)
                return version;
        }
        return null;
    }

    @Override
    public int search(Version object) {
        final int DIDNOT_FIND_ROW=-1;
        int counter=0;
        for (Version version : getList()){
            if (version.equals(object))
                return counter;
            counter++;
        }
        return DIDNOT_FIND_ROW;
    }
}

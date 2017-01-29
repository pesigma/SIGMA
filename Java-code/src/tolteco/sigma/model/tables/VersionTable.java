/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import java.util.Date;
import tolteco.sigma.model.entidades.Version;

/**
 * Tabela de versão.
 * @author Juliano
 */
public class VersionTable extends SigmaAbstractTableModel<Version>{
    private static final int COLUMN_COUNT = 7;
    private static final int MAJOR_VER    = 0;
    private static final int MAJOR_NAME   = 1;
    private static final int MAJOR_DATE   = 2;
    private static final int MAJOR_NOTES  = 3;
    private static final int MINOR_VER    = 4;
    private static final int MINOR_DATE   = 5;
    private static final int MINOR_NOTES  = 6;
    
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
            case MAJOR_VER:   version.setMajorVersion((int) aValue);
            case MAJOR_NAME:  version.setMajorName((String) aValue);
            case MAJOR_DATE:  version.setMajorDate((Date) aValue);
            case MAJOR_NOTES: version.setMajorNotes((String) aValue);
            case MINOR_VER:   version.setMinorVersion((int) aValue);
            case MINOR_DATE:  version.setMinorDate((Date) aValue);
            case MINOR_NOTES: version.setMinorNotes((String) aValue);

            default:
                throw new IndexOutOfBoundsException(
                "Exceeded Max Column Count: " + columnIndex +  " out of " + COLUMN_COUNT + ".");
        }
    }
}

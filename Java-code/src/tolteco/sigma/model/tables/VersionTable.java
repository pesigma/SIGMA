/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.tables;

import tolteco.sigma.model.entidades.Version;

/**
 *
 * @author Juliano
 */
public class VersionTable extends SigmaAbstractTableModel<Version>{
    private final int COLUMN_COUNT = 7;
    private final int MAJOR_VER    = 0;
    private final int MAJOR_NAME   = 1;
    private final int MAJOR_DATE   = 2;
    private final int MAJOR_NOTES  = 3;
    private final int MINOR_VER    = 4;
    private final int MINOR_DATE   = 5;
    private final int MINOR_NOTES  = 6;
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Version version = getValueAtRow(rowIndex);
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
                "Exceeded Max Column Count: " + columnIndex +  " out of " + columnIndex + ".");
        }
    }
}

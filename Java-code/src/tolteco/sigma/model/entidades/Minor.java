/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

import java.util.Date;

/**
 *
 * @author JFPS
 */
public class Minor implements PrimaryKeyComparable{
        int majorVer, minorVer;
        Date minorDate;
        String minorNotes;
        
        public Minor(int majorVer, int minorVer, Date minorDate, String minorNotes) {
            if (majorVer < 0) throw new IllegalArgumentException("Número de versão negativo: " + majorVer);
            else this.majorVer = majorVer; 
            
            if (minorVer < 0) throw new IllegalArgumentException("Número de versão negativo: " + minorVer);
            else this.minorVer = minorVer; 
            
            this.minorVer = minorVer;
            this.minorDate = minorDate;
            this.minorNotes = minorNotes;
        }
        
        public int getMajorVer() {
            return majorVer;
        }
        
        public int getMinorVer() {
            return minorVer;
        }
        
        public Date getMinorDate() {
            return minorDate;
        }
        
        public String getMinorNotes() {
            return minorNotes;
        }

    @Override
    public int getRowId() {
        return minorVer;
    }
}

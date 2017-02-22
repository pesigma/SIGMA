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
public class Major implements PrimaryKeyComparable{
        int majorVer;
        Date majorDate;
        String majorName,
               majorNotes;
        
        public Major(int majorVer, String majorName, Date majorDate, String majorNotes) {
            if (majorVer < 0) throw new IllegalArgumentException("Número de versão negativo: " + majorVer);
            else this.majorVer = majorVer;       

            this.majorDate = majorDate;
            this.majorName = majorName;
            this.majorNotes = majorNotes;
        }
        
        public int getMajorVer() {
            return majorVer;
        }
        
        public Date getMajorDate() {
            return majorDate;
        }
        
        public String getMajorNotes() {
            return majorNotes;
        }

        public String getMajorName() {
            return majorName;
        }

    @Override
    public int getRowId() {
        return majorVer;
    }
}
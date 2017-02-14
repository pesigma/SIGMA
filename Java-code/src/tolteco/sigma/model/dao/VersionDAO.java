/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

import java.util.Date;
import tolteco.sigma.model.entidades.Version;

/**
 * DAO referente à controle de
 * versões. Os dados do controle
 * de versões não podem ser
 * normalmente excluídos, então
 * eles serão privados.
 * @author Juliano Felipe da Silva
 */
public interface VersionDAO extends GenericDAO<Version>{
    int createMajorRelease(String name, Date date, String notes) throws DatabaseException;
    
    int createMinorRelease(int majorVer, Date date, String notes) throws DatabaseException;
    
    Version fetchLatestVersion() throws DatabaseException;
    
    
    Major getLatestMajor() throws DatabaseException;
    
    Minor getLatestMinor() throws DatabaseException;
    
    //<editor-fold defaultstate="collapsed" desc="Minor Class">
    public class Minor{
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
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Major Class">
    
    public class Major{
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
    }
//</editor-fold>
    
    default Version versionBuilder(Major major, Minor minor){
        return new Version(major.getMajorVer(),
                           major.getMajorName(), 
                           major.getMajorDate(), 
                           major.getMajorNotes(), 
                
                           minor.getMinorVer(), 
                           minor.getMinorDate(), 
                           minor.getMinorNotes());
    }
}

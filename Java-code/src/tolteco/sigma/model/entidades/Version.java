/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

import java.util.Date;

/**
 * Contém informações sobre
 * uma release (Data, major,
 * minor, etc).
 * @author Juliano Felipe
 */
public class Version implements Comparable<Version>{
    private static final int MAXTRAILING = 3;
    
    private int majorVersion;
    private String majorName;
    private Date majorDate;
    private String majorNotes;
    private int minorVersion;
    private Date minorDate;
    private String minorNotes;
    
    private int rowid;

    public Version(int majorVersion, String majorName, Date majorDate, String majorNotes, int minorVersion, Date minorDate, String minorNotes) {
        this.majorVersion = majorVersion;
        this.majorName = majorName;
        this.majorDate = majorDate;
        this.majorNotes = majorNotes;
        this.minorVersion = minorVersion;
        this.minorDate = minorDate;
        this.minorNotes = minorNotes;
    }

    public Version(int rowid) {
        this.rowid = rowid;
    }

    public Version(int rowid, int majorVersion, String majorName, Date majorDate, String majorNotes, int minorVersion, Date minorDate, String minorNotes) {
        this.majorVersion = majorVersion;
        this.majorName = majorName;
        this.majorDate = majorDate;
        this.majorNotes = majorNotes;
        this.minorVersion = minorVersion;
        this.minorDate = minorDate;
        this.minorNotes = minorNotes;
        
        this.rowid = rowid;
    }
    
    @Override
    public String toString() {
        return "Version{" + "majorVersion=" + majorVersion + ", majorName=" +
                majorName + ", majorDate=" + majorDate + ", majorNotes=" + 
                majorNotes + ", minorVersion=" + minorVersion + ", minorDate=" +
                minorDate + ", minorNotes=" + minorNotes + '}';
    }
    
    /**
     * Retorna a representação curta de
     * uma String, que é dada como:
     * major.minor (MajorName).
     * <p>
     * "Trailing zeros" são adicionados
     * baseados na constante 
     * {@link tolteco.sigma.model.entidades.Version#MAXTRAILING}.
     * Ex.: 1.001 (SANTIAGO), sendo o MAXTRAILING igual à 3.
     * @return 
     */
    public String shortString(){
        return Integer.toString(majorVersion) + "." + 
            String.format("%0" + MAXTRAILING + "d", minorVersion) + 
            " (" + majorName + ')';
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getMajorVersion() {
        return majorVersion;
    }
    
    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }
    
    public String getMajorName() {
        return majorName;
    }
    
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    
    public Date getMajorDate() {
        return majorDate;
    }
    
    public void setMajorDate(Date majorDate) {
        this.majorDate = majorDate;
    }
    
    public String getMajorNotes() {
        return majorNotes;
    }
    
    public void setMajorNotes(String majorNotes) {
        this.majorNotes = majorNotes;
    }
    
    public int getMinorVersion() {
        return minorVersion;
    }
    
    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }
    
    public Date getMinorDate() {
        return minorDate;
    }
    
    public void setMinorDate(Date minorDate) {
        this.minorDate = minorDate;
    }
    
    public String getMinorNotes() {
        return minorNotes;
    }
    
    public void setMinorNotes(String minorNotes) {
        this.minorNotes = minorNotes;
    }
    
    public int getRowid() {
        return rowid;
    }
    
    public void setRowid(int rowid) {
        this.rowid = rowid;
    }
//</editor-fold>
}

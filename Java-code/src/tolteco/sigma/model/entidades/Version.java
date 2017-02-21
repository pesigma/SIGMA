/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

import java.util.Date;
import java.util.Objects;

/**
 * Contém informações sobre uma release (Data, major, minor, etc).
 *
 * @author Juliano Felipe
 */
public class Version implements Comparable<Version> {

    private static final int MAXTRAILING = 3;

    private int majorVersion; //ID OF "MAJOR" Table
    private String majorName;
    private Date majorDate;
    private String majorNotes;
    private int minorVersion; //ID OF "MINOR" Table
    private Date minorDate;
    private String minorNotes;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Version(int majorVersion, String majorName, Date majorDate, String majorNotes, int minorVersion, Date minorDate, String minorNotes) {
        this.majorVersion = majorVersion;
        this.majorName = majorName;
        this.majorDate = majorDate;
        this.majorNotes = majorNotes;
        this.minorVersion = minorVersion;
        this.minorDate = minorDate;
        this.minorNotes = minorNotes;
    }
    
    public Version(int rowid, int minorId) {
        this.majorVersion = rowid;
        this.minorVersion = minorId;
    }
    
//</editor-fold>

    @Override
    public String toString() {
        return "Version{" + "majorVersion=" + majorVersion + ", majorName="
                + majorName + ", majorDate=" + majorDate + ", majorNotes="
                + majorNotes + ", minorVersion=" + minorVersion + ", minorDate="
                + minorDate + ", minorNotes=" + minorNotes + '}';
    }

    /**
     * Retorna a representação curta de uma String, que é dada como: major.minor
     * (MajorName).
     * <p>
     * "Trailing zeros" são adicionados baseados na constante
     * {@link tolteco.sigma.model.entidades.Version#MAXTRAILING}. Ex.: 1.001
     * (SANTIAGO), sendo o MAXTRAILING igual à 3.
     *
     * @return
     */
    public String shortString() {
        return Integer.toString(majorVersion) + "."
                + String.format("%0" + MAXTRAILING + "d", minorVersion)
                + " (" + majorName + ')';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.majorVersion;
        hash = 71 * hash + this.minorVersion;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Version other = (Version) obj;
        if (this.majorVersion != other.majorVersion) {
            return false;
        }
        if (this.minorVersion != other.minorVersion) {
            return false;
        }
        return true;
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
//</editor-fold>

    @Override
    public int compareTo(Version o) {
        Version that = null;
        if (o instanceof Version) {
            that = (Version) o;
        } else {
            throw new ClassCastException("Objeto a ser comparado não é uma Versão!");
        }

        Double thisDouble = asDouble(this);
        Double thatDouble = asDouble(that);

        return thisDouble.compareTo(thatDouble);
    }

    /**
     * Retorna uma versão como um Double
     * (Como referência para facilitar
     * comparação em 
     * {@link #compareTo(tolteco.sigma.model.entidades.Version)}).
     * 
     * O Double retornado sera como 
     * {@link #majorVersion}.{@link #minorVersion}.
     * 
     * @param v Versão para transformar.
     * @return  Double representando versão.
     */
    public static Double asDouble(Version v) {
        return Double.parseDouble(v.majorVersion + "." + v.minorVersion);
    }
    
     /**
     * Acopla um objeto {@link tolteco.sigma.model.entidades.Major} e um de 
     * {@link tolteco.sigma.model.entidades.Minor} para ter ambas as
     * informações unidas na classe {@link tolteco.sigma.model.entidades.Version}.
     * @param major
     * @param minor
     * @return 
     */
    public static Version versionBuilder(Major major, Minor minor){
        return new Version(major.getMajorVer(),
                           major.getMajorName(), 
                           major.getMajorDate(), 
                           major.getMajorNotes(), 
                
                           minor.getMinorVer(), 
                           minor.getMinorDate(), 
                           minor.getMinorNotes());
    }
    
    public void setMinor(Minor minor){
        this.minorDate = minor.getMinorDate();
        this.minorNotes = minor.getMinorNotes();
        this.minorVersion = minor.getMinorVer();
    }
}

/* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools  Templates
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
public final class Version {
    private static final int MAXTRAILING = 3;
    
    private final int majorVersion;
    private final int majorName;
    private final Date majorDate;
    private final String majorNotes;
    
    private final int minorVersion;
    private final Date minorDate;
    private final String minorNotes;

    public Version(int majorVersion, int majorName, Date majorDate, String majorNotes, int minorVersion, Date minorDate, String minorNotes) {
        this.majorVersion = majorVersion;
        this.majorName = majorName;
        this.majorDate = majorDate;
        this.majorNotes = majorNotes;
        this.minorVersion = minorVersion;
        this.minorDate = minorDate;
        this.minorNotes = minorNotes;
    }

    @Override
    public String toString() {
        return "Version{" + "majorVersion=" + majorVersion + ", majorName=" +
             majorName + ", majorDate=" + majorDate + ", majorNotes=" + 
             majorNotes + ", minorVersion=" + minorVersion + ", minorDate=" +
             minorDate + ", minorNotes=" + minorNotes + '}';
    }
    
    /**
     * Retorna String do tipo
     * majorVersion.minorVersion.
     * <p>
     * Considera-se 
     * {@link tolteco.sigma.model.entidades.Version#MAXTRAILING}
     * para o número de zeros a ser adicionado caso o número
     * de algarismos da minorVersion não seja igual ao primeiro.
     * Em outras palavras, se MAXTRAILING for 3, majorVersion for 1
     * e minorVersion for 99, a representação é 1.099.
     * @return 
     */
    private String versionWithTrailing(){
        return Integer.toString(majorVersion) + '.' + 
            String.format("%0" + MAXTRAILING + "d", minorVersion);
    }
    
    
    /**
     * Retorna a representação curta de
     * uma String, que é dada como
     * major.minor (MajorName).
     * <p>
     * Trailing zeros são adicionados
     * baseados na constante 
     * {@link tolteco.sigma.model.entidades.Version#MAXTRAILING}.
     * Ex. 1.001 (SANTIAGO), sendo o MAXTRAILING igual à 3.
     *
     * @return String representativa de versão
     */ 
    public String shortString(){
        return versionWithTrailing() + 
             '(' + majorName + ')';
    }
    
    
    /**
     * Equivalente à
     * {@link tolteco.sigma.model.entidades.Version#versionWithTrailing()},
     * mas sem trailing.
     * <p>
     * Exemplo majorVersion é 1 e minorVersion é 99, o resultado é 1.99.
     * 
     * @return Representação do número de versão, sem trailing.
     */ 
    private String versionWithNoTrailing(){
        return Integer.toString(majorVersion) + '.'
             + minorVersion;
    }
    
    
    /**
     * Retorna representação curta de
     * uma versão, sem Trailing Zeros,
     * dada como:
     * major.minor (majorName).
     * 
     * @return String representativa de versão
     */
    public String shortStringNoTrailing(){
        return versionWithNoTrailing() + 
                '(' + majorName + ')';
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public int getMajorVersion() {
        return majorVersion;
    }
    
    public int getMajorName() {
        return majorName;
    }
    
    public Date getMajorDate() {
        return majorDate;
    }
    
    public String getMajorNotes() {
        return majorNotes;
    }
    
    public int getMinorVersion() {
        return minorVersion;
    }
    
    public Date getMinorDate() {
        return minorDate;
    }
    
    public String getMinorNotes() {
        return minorNotes;
    }
//</editor-fold>

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.majorVersion;
        hash = 17 * hash + this.minorVersion;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
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
}

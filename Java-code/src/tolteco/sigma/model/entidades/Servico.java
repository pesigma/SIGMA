/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

import java.util.Comparator;

/**
 * Entidade Servico, possui métodos e valores para o objeto serviço
 * @author Maycon
 */
public class Servico implements Comparable{
    //Atributos
    String placa, 
           modelo,
           obs;
    int km, 
        idc;
    boolean sit;
    int rowid;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    
    public Servico(int idc, String placa, String modelo, int km, boolean sit, String obs) {
        this.idc = idc;
        this.placa = placa;
        this.modelo = modelo;
        this.km = km;
        this.sit = sit;
        this.obs = obs;
    }

    public Servico(int rowid) {
        this.rowid = rowid;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    
    public int getIdcliente() {
        return idc;
    }
    
    public void setIdcliente(int idc) {
        this.idc = idc;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKm() {
        return km;
    }

    public void setNome(int km) {
        this.km = km;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    public boolean getSit() {
        return sit;
    }

    public void setSit(boolean sit) {
        this.sit = sit;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }
    
    // </editor-fold>
    
    @Override
    public String toString() {
        String sitStr;
        if (sit == true) sitStr = "Quitado";
        else sitStr = "Pendente";
        
        return "Servico{" + "Placa: " + placa + ", Modelo: " + modelo + ", Obs.: " + obs + ", Km: " + km + ", IDC: " + idc + ", Situação: " + sitStr + ", Rowid: " + rowid + '}';
    }

    //<editor-fold defaultstate="collapsed" desc="Comparators">
    
    /**
     * Odenação pela situação. Preferência para "true" ser maior.
     * Assim, se dois forem falsos ou dois forem true, é 0.
     * Se {@link Servico#sit} for true e that for false, é 1.
     * Se {@link Servico#sit} for false e that for true, é -1.
     * @param that Boolean para ser comparado.
     * @return     int referente ao valor da comparação.
     */
    @Override
    public int compareTo(Object that) {
        boolean bool = false; //Parece deselegante
        if (that instanceof Boolean) bool = (boolean) that;
        else throw new ClassCastException ("Objeto a ser comparado não é um Boolean!");
        
        int thisB = 0;
        if (this.sit == true) thisB = 1;
        
        int thatB = 0;
        if (bool == true) thatB = 1;
        
        return thisB - thatB;
    }
    
    public static Comparator<String> placaComparator (){
        return (String o1, String o2) -> {
            if (o1== null || o2 == null)
                return 0;
            return o1.compareTo(o2);
        };
    }
    
    public static Comparator<String> modeloComparator (){
        return Servico.placaComparator();
    }
    
    public static Comparator<String> obsComparator (){
        return Servico.placaComparator();
    }
    
    public static Comparator<Integer> kmComparator (){
        return (Integer o1, Integer o2) -> {
            if (o1== null || o2 == null)
                return 0;
            return o1.compareTo(o2);
        };
    }
    
    public static Comparator<Integer> idClienteComparator (){
        return Servico.kmComparator();
    }
    
    public static Comparator<Boolean> situacaoComparator (){
        return (Boolean o1, Boolean o2) -> o1.compareTo(o2);
    }
    
//</editor-fold>
}

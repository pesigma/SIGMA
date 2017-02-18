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
public class Servico implements Comparable<Servico>{
    //Atributos
    String placa, 
           modelo,
           obs;
    double km;
    int   idCliente;
    Situacao situacao;
    int rowid;
    int userId;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    
    public Servico(int idc, String placa, String modelo, double km, Situacao sit, String obs) {
        this.idCliente = idc;
        this.placa = placa;
        this.modelo = modelo;
        this.km = km;
        this.situacao = sit;
        this.obs = obs;
    }
    
    public Servico(int rowId, int idCliente, String placa, String modelo, double km, Situacao sit, String obs, int userId) {
        this.idCliente = idCliente;
        this.placa = placa;
        this.modelo = modelo;
        this.km = km;
        this.situacao = sit;
        this.obs = obs;
        this.rowid = rowId;
        this.userId = userId;
    }

    public Servico(int rowid) {
        this.rowid = rowid;
    }
    
    public int getUserId() {
        return userId;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public void setUserId(int userId) {    
        this.userId = userId;
    }

    public int getIdcliente() {
        return idCliente;
    }
    
    public void setIdcliente(int idc) {
        this.idCliente = idc;
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

    public double getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
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
    
    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
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
        return "Servico{" + "Placa: " + placa + 
               ", Modelo: " + modelo + ", Obs.: " + obs + 
               ", Km: " + km + ", IDC: " + idCliente + 
               ", Situação: " + situacao.getDescricao() + 
               ", Rowid: " + rowid + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.rowid;
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
        final Servico other = (Servico) obj;
        if (this.rowid != other.rowid) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
   

    //<editor-fold defaultstate="collapsed" desc="Comparators">
    
    /**
     * Odenação pela situação. Preferência para codigos mais próximos
     * de zero serem maiores. Por exemplo:
     * {@link tolteco.sigma.model.entidades.Situacao#PENDENTE}
     * tem um valor menor que
     * {@link tolteco.sigma.model.entidades.Situacao#SERVICOPAGO},
     * portanto, o primeiro é mais prioritário.
     * @param that Objeto para ser comparado.
     * @return     int referente ao valor da comparação.
     */
    @Override //Checar o objeto de passagem como param (É Servico ou inteiro, etc).
    public int compareTo(Servico that) {
        Servico thatService = null; //Parece deselegante
        if (that instanceof Servico) thatService = (Servico) that;
        else throw new ClassCastException ("Objeto a ser comparado não é um Serviço!");
        
             if (this.situacao.getCodigo() > thatService.situacao.getCodigo()) return -1;
        else if (this.situacao.getCodigo() == thatService.situacao.getCodigo()) return 0;
        else return 1;
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

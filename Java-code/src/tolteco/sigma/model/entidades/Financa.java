/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import tolteco.sigma.utils.SDate;

/**
 * Entidade financa, possui métodos e valores para o objeto finança
 * @author Maycon
 */
public class Financa implements Comparable<Financa> {
    
    //Atributos
    Date data;
    String obs;
    Situacao situacao;
    FinancaTipo tipo;
    double valor;
    int rowid;
    int userId;
    
    // <editor-fold defaultstate="collapsed" desc="Construtores">
    
    public Financa(FinancaTipo tipo, Date data, double valor, String obs, Situacao sit) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.obs = obs;
        this.situacao = sit;
    }
    
    public Financa(int rowid, FinancaTipo tipo, Date data, double valor, String obs, Situacao sit) {
        this.rowid = rowid;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.obs = obs;
        this.situacao = sit;
    }
    
    public Financa(int rowid, FinancaTipo tipo, Date data, double valor, String obs, Situacao sit, int userId) {
        this.rowid = rowid;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.obs = obs;
        this.situacao = sit;
        this.userId = userId;
    }

    public Financa(int rowid) {
        this.rowid = rowid;
    }
    
    public Financa() {

    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    
    public String getDateToDatabase(){
        return SDate.sigmaDateFormat(this.data);
    }
    
    public int getRowid() {
        return rowid;
    }
    
    public FinancaTipo getTipo() {
        return tipo;
    }

    public Date getData() {
        return data;
    }

    public String getObs() {
        return obs;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public double getValor() {
        return valor;
    }

    public void setRowid(int rowid){
        this.rowid = rowid;
    }
    
    public void setTipo(FinancaTipo tipo) {
        this.tipo = tipo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDesc(String obs) {
        this.obs = obs;
    }

    public void setSituacao(Situacao sit) {
        this.situacao = sit;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    //</editor-fold>
    
    @Override
    public String toString() {       
        return "Financa{" + "Data: " + data + 
               ", Obs.: " + obs + ", Situação: " + situacao.getDescricao() + 
               ", Tipo: " + tipo.getDescricao() + ", Valor: " + valor + 
               ", Rowid: " + rowid + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.rowid;
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
        final Financa other = (Financa) obj;
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
     * Compara por data.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Financa o) {
        Financa fin=null;
        if (o instanceof Financa) fin = (Financa) o;
        else throw new ClassCastException ("Objeto a ser comparado não é uma Finança!");
        
        if (this.getData() == null || fin.getData() == null)
            return 0;
        return this.getData().compareTo(fin.getData());
    }
    
    public static Comparator<String> obsComparator (){
        return (String o1, String o2) -> {
            if (o1== null || o2 == null)
                return 0;
            return o1.compareTo(o2);
        };
    }
    
    public static Comparator<Boolean> situacaoComparator (){
        return (Boolean o1, Boolean o2) -> o1.compareTo(o2);
    }
    
    public static Comparator<Boolean> tipoComparator (){
        return Financa.situacaoComparator ();
    }
    
    public static Comparator<Double> valorComparator (){
        return (Double o1, Double o2) -> o1.compareTo(o2);
    }
    
    //RowId não será necessário, já que ao pegar do banco
    //vem ordenado por RowId
    
    //</editor-fold>
}

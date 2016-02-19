/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 * Entidade financa, possui métodos e valores para o objeto finança
 * @author Maycon
 */
public class Financa {
    
    //Atributos
    String data, obs;
    boolean sit, tipo;
    double valor;

    //Construtores
    public Financa(boolean tipo, String data, double valor, String obs, boolean sit) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.obs = obs;
        this.sit = sit;
    }
    
    //Métodos
    public boolean getTipo() {
        return tipo;
    }

    public String getData() {
        return data;
    }

    public String getobs() {
        return obs;
    }

    public boolean isSit() {
        return sit;
    }

    public double getValor() {
        return valor;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDesc(String obs) {
        this.obs = obs;
    }

    public void setSit(boolean sit) {
        this.sit = sit;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}

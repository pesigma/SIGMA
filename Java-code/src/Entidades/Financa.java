/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Maycon
 */
public class Financa {
    
    //Atributos
    String tipo, data, desc;
    boolean sit;
    float valor;

    //Construtores
    public Financa(String tipo, String data, float valor, String desc, boolean sit) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.desc = desc;
        this.sit = sit;
    }
    
    //Métodos
    public String getTipo() {
        return tipo;
    }

    public String getData() {
        return data;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isSit() {
        return sit;
    }

    public float getValor() {
        return valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSit(boolean sit) {
        this.sit = sit;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}

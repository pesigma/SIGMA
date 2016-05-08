/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 * Entidade Servico, possui métodos e valores para o objeto serviço
 * @author Maycon
 */
public class Servico {
    //Atributos
    String placa, 
           modelo,
           obs;
    int km, 
        idc;
    boolean sit;
    int rowid;

    //Construtores
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

    //Métodos
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
    
}

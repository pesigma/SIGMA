/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 * Entidade cliente, possui métodos e valores para o objeto cliente
 * @author Maycon
 */
public class Cliente {

    //Atributos
    String nome,
           sobrenome,
           obs, 
           end, 
           tel, 
           cpf;
    int rowid;

    //Construtores
    public Cliente(String nome, String sobrenome, String obs, String end, String tel, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.obs = obs;
        this.end = end;
        this.tel = tel;
        this.cpf = cpf;
    }
    
    public Cliente (int rowid){
        this.rowid = rowid;
    }

    //Métodos
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }
}

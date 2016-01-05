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
public class Cliente {

    //Atributos
    String nome, obs, end, tel, cpf;

    //Construtores
    public Cliente(String tel, String cpf, String nome, String obs, String end) {
        this.tel = tel;
        this.cpf = cpf;
        this.nome = nome;
        this.obs = obs;
        this.end = end;
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

}

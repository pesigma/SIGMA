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
    int tel, cpf;
    String nome, obs;

    //Construtores
    public Cliente(int tel, int cpf, String nome, String obs) {
        this.tel = tel;
        this.cpf = cpf;
        this.nome = nome;
        this.obs = obs;
    }

    //Métodos
    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
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

}

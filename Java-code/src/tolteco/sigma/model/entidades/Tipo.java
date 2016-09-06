/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

/**
 * Identifica os tipos no modelo.
 * Ex.: Cliente, Financa, Servico
 * @author Juliano_Felipe
 */
public enum Tipo {
    USER(1),
    CONFIG(2),
    FINANCA(4),
    SERVICO(8),
    RELATORIO(16),
    CLIENTE(32);

    
    private final int value;
    private Tipo (int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}

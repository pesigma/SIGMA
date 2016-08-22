/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

/**
 * Identifica as opções entre
 * deletar, modificar e/ou
 * adicionar.
 * @author Juliano_Felipe
 */
public enum Opcao {
    DELETE(0), //Mais prox. de zero, mais permissao necessaria.
    MODIFY(1),
    ADD(2);
    
    private final int value;
    private Opcao (int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}

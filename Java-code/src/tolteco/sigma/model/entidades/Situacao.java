/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

/**
 *
 * @author Juliano Felipe
 */
public enum Situacao {
    PENDENTE(0, "Pendente"), //Pendente é 0,
    SERVICOPAGO(1, "Quitado"), //ZERO tem mais "prioridade".
    FINACAPAGA(1, "Quitada");
    
    private final int value;
    private final String descricao;
    private Situacao (int value, String descricao){
        this.value = value;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return value;
    }
    
    public String getDescricao(){
        return descricao;
    }
}

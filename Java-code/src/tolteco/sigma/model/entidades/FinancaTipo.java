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
public enum FinancaTipo {
    DESPESA(0, "Despesa"),
    RECEITA(1, "Receita");
    
    private final int value;
    private final String descricao;
    private FinancaTipo (int value, String descricao){
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

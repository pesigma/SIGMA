package tolteco.sigma.model.entidades;

/**
 * Identifica o tipo de uma financa cadastrada no sistema
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

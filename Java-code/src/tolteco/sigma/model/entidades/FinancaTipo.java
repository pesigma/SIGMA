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

    /**
     * Dado um inteiro, retorna o 
     * tipo de Financa relacionado.
     * @param codigo do tipo inteiro.
     * @return Access correspondente.
     * @throws IllegalArgumentException Caso seja fora do limite.
     */
    public static FinancaTipo porCodigo (int codigo){
        for (FinancaTipo op : FinancaTipo.values())
            if (codigo == op.value) return op;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public int getCodigo() {
        return value;
    }
    
    public String getDescricao(){
        return descricao;
    }
}

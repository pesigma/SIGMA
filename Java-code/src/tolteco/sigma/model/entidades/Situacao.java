package tolteco.sigma.model.entidades;

/**
 * Identifica a situacao de uma ginanca ou servico
 * @author Juliano Felipe
 */
public enum Situacao {
    PENDENTE(0, "Pendente"), //Pendente é 0,
    SERVICOPAGO(1, "Quitado"), //ZERO tem mais "prioridade".
    FINACAPAGA(2, "Quitada");
    
    private final int value;
    private final String descricao;
    private Situacao (int value, String descricao){
        this.value = value;
        this.descricao = descricao;
    }

    /**
     * Dado um inteiro, retorna o 
     * tipo de Situacao relacionado.
     * @param codigo do tipo inteiro.
     * @return Access correspondente.
     * @throws IllegalArgumentException Caso seja fora do limite.
     */
    public static Situacao porCodigo (int codigo){
        for (Situacao op : Situacao.values())
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

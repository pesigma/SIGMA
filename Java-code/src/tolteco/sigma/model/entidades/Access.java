package tolteco.sigma.model.entidades;

/**
 * Identifica o nível de acesso
 * de um usuário.
 * Não é necessariamente seguro,
 * seu intuito é "tornar o código
 * mais legível".
 * @author Juliano_Felipe
 */
public enum Access {
    ROOT(0),
    ADMINISTRADOR(1),
    FUNCIONARIO(2);
    
    private final int value;
    private Access (int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    /**
     * Dado um inteiro, retorna o 
     * nível de acesso relacionado.
     * @param codigo do tipo inteiro.
     * @return Access correspondente.
     * @throws IllegalArgumentException Caso seja fora do limite.
     */
    public static Access porCodigo (int codigo){
        for (Access op : Access.values())
            if (codigo == op.value) return op;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
}

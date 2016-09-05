package tolteco.sigma.model.entidades;

/**
 * Identifica a situacao de uma ginanca ou servico
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

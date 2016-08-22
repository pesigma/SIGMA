/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view.cliente;

import java.awt.Component;

/**
 *
 * @author Juliano
 */
public enum OperacaoCliente {
    Adicionar(0),
    Modificar(1),
    Buscar(2),
    Listar(3),
    Remover(4);
    
    private final int valor;
    private OperacaoCliente(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    /**
     * Dado um inteiro, retorna a
     * {@link tolteco.sigma.view.cliente.OperacaoCliente}
     * relacionada.
     * @param codigo do tipo inteiro.
     * @return OperacaoCliente correspondente.
     * @throws IllegalArgumentException Caso seja fora do limite.
     */
    public static OperacaoCliente porCodigo (int codigo){
        for (OperacaoCliente op : OperacaoCliente.values())
            if (codigo == op.valor) return op;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    /**
     * Dado um componente das views de clientes, retorna o código
     * referente à ela. Joga {@link } 
     * @param componente para se obter o código.
     * @return {@link tolteco.sigma.view.cliente.OperacaoCliente}
     *         correspondente.
     * @throws IllegalArgumentException Caso qualquer outro tipo
     *                                  de componente seja passado.
     */
    public static OperacaoCliente porInstancia (Component componente){
             if (componente instanceof AdicionarCliente) return Adicionar;
        else if (componente instanceof ModificarCliente) return Modificar;
        else if (componente instanceof BuscarCliente)    return Buscar;
        else if (componente instanceof ListarCliente)    return Listar; 
        else if (componente instanceof RemoverCliente)   return Remover;
        else throw new IllegalArgumentException("Componente inválido.");
    }
    
    /**
     * Método usado para retornar uma instância de um componente
     * usado nas views do cliente, dado a OperacaoCliente.
     * @param tipo OperacaoCliente identificando a operação.
     * @return Instância do componente identificado pela enum.
     */
    public static Component operacaoPorTipo (OperacaoCliente tipo){
             if (tipo == Adicionar)   return new AdicionarCliente();
        else if (tipo == Modificar)   return new ModificarCliente();
        else if (tipo == Buscar)      return new BuscarCliente();
        else{
            String methodName = Thread.currentThread().getStackTrace()[1]
                                .getMethodName();
            throw new IllegalArgumentException("Alguém esqueceu de adicionar"
                         + " a operação nessa função :D -> " + methodName);
        } //Check de sanidade para não esquecer de adicionar nada
    }
}

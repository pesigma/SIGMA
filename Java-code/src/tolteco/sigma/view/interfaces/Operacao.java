/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view.interfaces;

import java.awt.Component;

/**
 *
 * @author Juliano
 */
public enum Operacao {
    Adicionar(0),
    Modificar(1),
    Buscar   (2),
    Listar   (3),
    Remover  (4),
    Nenhum   (5);
    
    private final int valor;
    private Operacao(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    /**
     * Dado um inteiro, retorna a
     * {@link tolteco.sigma.view.interfaces.Operacao}
     * relacionada.
     * @param codigo do tipo inteiro.
     * @return Operacao correspondente.
     * @throws IllegalArgumentException Caso seja fora do limite.
     */
    public static Operacao porCodigo (int codigo){
        for (Operacao op : Operacao.values())
            if (codigo == op.valor) return op;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    /**
     * Dado um componente das views, retorna o código
     * referente à ela. Joga {@link } 
     * @param componente para se obter o código.
     * @return {@link tolteco.sigma.view.interfaces.Operacao}
     *         correspondente.
     * @throws IllegalArgumentException Caso qualquer outro tipo
     *                                  de componente seja passado.
     */
    public static Operacao porInstancia (Component componente){
             if (componente instanceof Adicionar) return Adicionar;
        else if (componente instanceof Modificar) return Modificar;
        else if (componente instanceof Buscar)    return Buscar;
        else if (componente instanceof Listar)    return Listar; 
        else if (componente instanceof Remover)   return Remover;
        else throw new IllegalArgumentException("Componente inválido.");
    }
    
    /*public static Component operacaoPorTipo (Operacao tipo){
             if (tipo == Adicionar)   return new AdicionarCliente();
        else if (tipo == Modificar)   return new ModificarCliente();
        else if (tipo == Buscar)      return new BuscarCliente();
        else{
            String methodName = Thread.currentThread().getStackTrace()[1]
                                .getMethodName();
            throw new IllegalArgumentException("Alguém esqueceu de adicionar"
                         + " a operação nessa função :D -> " + methodName);
        } //Check de sanidade para não esquecer de adicionar nada
    }*/
}

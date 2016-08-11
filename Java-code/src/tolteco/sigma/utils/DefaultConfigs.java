/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils;

import java.awt.Dimension;

/**
 * Não faz nada ainda.
 * Só possui algumas configs
 * @author Juliano
 */
public class DefaultConfigs {
    /**
     * Define o look and feel do sistema.
     */
    public static final String SYSTEMLOOK = "Windows";
    
    /**
     * Tais são as dimensões MÁXIMAS dos paineis internos,
     * no padrão estabelecido agora.
     * Ex.: {@link tolteco.sigma.view.cliente.AdicionarCliente}
     */
    private static final Dimension subPanel = new Dimension(705, 480);
    /**
     * Tais são as dimensões MÁXIMAS dos paineis que fazem as operacões
     * nos tipos (Cliente, finança, etc...),
     * no padrão estabelecido agora.
     * Ex.: {@link tolteco.sigma.view.cliente.MainCliente}
     */
    private static final Dimension panel = new Dimension(754, 580);
    
    /**
     * Tais são as dimensões MÁXIMAS do frame principal (com menus e abas), 
     * no padrão estabelecido agora.
     * Ex.: {@link tolteco.sigma.view.MainFrame}
     */
    private static final Dimension frame = new Dimension(760, 616);

}

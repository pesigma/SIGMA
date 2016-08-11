/*
 * Conjunto de utilidades aplicadas
   aos frames da view. Como centralizar
   setar ícones e etc.
 */
package tolteco.sigma.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Utilidades para as
 * views do sistema,
 * como controle de
 * posicionamento,
 * ícones e etc.
 * @author Juliano
 */
public class ViewUtils {
    private static final String logoResource =
    "/tolteco/sigma/view/images/Logo 100x100.png";
    
    /**
     * Centraliza um {@link javax.swing.JFrame}
     * num monitor, independente de sua resolução.
     * <p>
     * Possíveis problemas se executado em um ambiente
     * com mais de um monitor.
     * @param frame para centralizar.
     */
    public static void centerFrame(JFrame frame){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
    
    /**
     * Coloca o ícone do sistema no canto
     * superior esquerdo de um frame.
     * <p>
     * Estando o path na variável:
     * {@link tolteco.sigma.view.ViewUtils#logoResource}.
     * @param frame para colocar a logo do sistema.
     */
    public static void setSIGMAIcon(JFrame frame){
        frame.setIconImage(Toolkit
                .getDefaultToolkit().
                getImage(frame.getClass().getResource(logoResource)));
    }
    
    /**
     * Coloca um ícone de 1x1 px no
     * canto superior esquerdo do frame,
     * para simular a "inexistência" de um
     * ícone.
     * @param frame para "retirar" ícone. 
     */
    public static void setNoIcon(JFrame frame){
        Image No_ico = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        frame.setIconImage(No_ico);
    }
}

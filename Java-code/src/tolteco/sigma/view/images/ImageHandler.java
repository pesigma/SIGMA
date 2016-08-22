/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view.images;

import java.net.URL;

/**
 *
 * @author Juliano_Felipe
 */
public final class ImageHandler {
    public static URL getWindowIcon(){
        ImageHandler img = new ImageHandler();
        return img.getClass().getResource("/tolteco/sigma/view/images/Logo 100x100.png");
    }
}

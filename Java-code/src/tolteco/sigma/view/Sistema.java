/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view;

import tolteco.sigma.model.entidades.Usuario;

/**
 *
 * @author JFPS
 */
public class Sistema {
    private static Sistema system;
    private static Usuario user=null;

    private Sistema(Usuario user) {
        Sistema.user = user;
    }
    
    public static void login(Usuario user){
        if (user != null) system = new Sistema(user);
    }
    
    public static void logout(){
        user = null;
        Sistema.shutdown();
    }
    
    public static int getUserId(){
        return user.getUserId();
    }
    
    private static void shutdown(){
        throw new UnsupportedOperationException("Não implementado");
        /*
        Realizar flush de logs.
        */
    }
}

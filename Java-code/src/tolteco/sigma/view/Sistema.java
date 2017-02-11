/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view;

import tolteco.sigma.model.entidades.Access;
import tolteco.sigma.model.entidades.Usuario;

/**
 *
 * @author JFPS
 */
public class Sistema {
    private static Sistema system;
    //private static Usuario user=null;
    private static Usuario user = new Usuario(0, "Teste", Access.ROOT, new char[]{123}); //Para testes
    
    private Sistema(Usuario user) {
        Sistema.user = user;
    }
    
    public static void login(Usuario user){
        if (user != null) system = new Sistema(user);
    }
    
    private static void startUp(){
        
    }
    
    public static void logout(){
        user = null;
        Sistema.shutdown();
    }
    
    public static int getUserID(){
        return user.getUserId();
    }

    public static Usuario getUser() {
        return user;
    }
    
    private static void shutdown(){
        throw new UnsupportedOperationException("Não implementado");
        /*
        Realizar flush de logs.
        */
    }
}

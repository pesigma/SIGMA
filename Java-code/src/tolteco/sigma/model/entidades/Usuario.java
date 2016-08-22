/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

import java.util.Observable;

/**
 * Classe totalmente segura
 * que identifica o usuário
 * e o nível de acesso. Não
 * armazena a senha por motivos
 * de "segurança".
 * @author Juliano_Felipe
 */
public class Usuario extends Observable implements Comparable<Usuario> {
    private final int userId;
    private final String userName;
    private final Access accessLevel;
    private char[] pass;
    
    public Usuario(int userId, String userName, Access accessLevel, char[] pass) {
        this.userId = userId;
        this.userName = userName;
        this.accessLevel = accessLevel;
        this.pass = pass;
        
        for (int i=0; i<pass.length; i++){
            pass[0] = '\0';
        }
    }

    /**
     * Construtor de cópia.
     * @param user para copiar
     */
    public Usuario(Usuario user){
        this.accessLevel = user.getAccessLevel();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
    }
    
    public boolean canDoOperation(Tipo tipo, Opcao op){
        switch (accessLevel) {
            case ROOT:
                return true;
                
            case ADMINISTRADOR:
                //Nao pode fazer nada com usuários nem configs.
                return !(tipo==Tipo.CONFIG || tipo==Tipo.USER);
                
            case FUNCIONARIO: //Funcionario
                //Nao pode fazer nada com financa nem relatorios.
                //Tambem nao pode deletar nada.
                if (tipo==Tipo.FINANCA || tipo==Tipo.RELATORIO) return false;
                return op != Opcao.DELETE;
                
            default:
                return false;
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Access getAccessLevel() {
        return accessLevel;
    }

    public char[] getPass(){
        return pass;
    }
    
    /**
     * Nulifica todos os chars
     * da senha. Por segurança.
     */
    public void clearPass(){
        for (int i=0; i<pass.length; i++)
            pass[0] = '\0';
    }

    @Override
    public int compareTo(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

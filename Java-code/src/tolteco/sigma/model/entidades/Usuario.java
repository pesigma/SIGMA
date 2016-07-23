/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

/**
 * Classe totalmente segura
 * que identifica o usuário
 * e o nível de acesso. Não
 * armazena a senha por motivos
 * de "segurança".
 * @author Juliano_Felipe
 */
public class Usuario {
    private final int userId;
    private final String userName;
    private final int accessLevel;

    public Usuario(int userId, String userName, int accessLevel ) {
        this.userId = userId;
        this.userName = userName;
        this.accessLevel = accessLevel;
    }

    public boolean canDoOperation(Tipo tipo, Opcao op){
        switch (accessLevel) {
            case 0: //Root
                return true;
                
            case 1: //Admin
                //Nao pode fazer nada com usuários nem configs.
                return !(tipo==Tipo.CONFIG || tipo==Tipo.USER);
                
            case 2: //Funcionario
                //Nao pode fazer nada com financa nem relatorios.
                //Tambem nao pode deletar nada.
                if (tipo==Tipo.FINANCA || tipo==Tipo.RELATORIO) return false;
                return op != Opcao.DELETE;
                
            default: //Nao identificado
                return false;
        }
    }
}

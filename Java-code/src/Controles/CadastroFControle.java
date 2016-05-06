/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Entidades.Financa;

/**
 * Controlador de financas; verifica se campos preenchidos satisfazem as condições necessárias para criação do objeto
 * @author Maycon
 */
public class CadastroFControle {
    public boolean cadastrarfinanca(Financa p) {
        boolean result = false;
        if (p != null && p.getValor()!=0 && p.getData()!=null && p.getObs()!=null) {
            result = true;
        }
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Entidades.Cliente;
import java.sql.Connection;

/**
 * Controlador de clientes; verifica se campos preenchidos satisfazem as condições necessárias para criação do objeto
 * @author Maycon
 */
public class CadastroCControle {
    public boolean cadastrarcliente(Cliente p) {
        boolean result = false;
        if (p != null && p.getNome().length() > 0) {
            result = true;
        }
        return result;
    }
}
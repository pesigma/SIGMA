/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;
import Entidades.Servico;
/**
 * 04/01 - Maycon
 * Controlador do recebimento de serviços
 * @author Maycon
 */
public class CadastroSControle {
    public boolean cadastrarcliente(Servico p) {
        boolean result = false;
        if (p != null && p.getPlaca().length() > 8 && p.getIdcliente() != 0) {
            result = true;
        }
        return result;
    }
}

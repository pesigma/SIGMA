/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

import java.util.Date;

/**
 * DAO referente à controle de
 * versões. Os dados do controle
 * de versões não podem ser
 * normalmente excluídos, então
 * eles serão privados.
 * @author Juliano Felipe da Silva
 */
public interface VersionDAO {
    void createMajorRelease(String name, String notes);
    
    void createMinorRelease(Date date, String notes);
}

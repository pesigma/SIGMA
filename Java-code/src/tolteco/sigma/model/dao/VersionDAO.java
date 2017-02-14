/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

import java.util.Date;
import tolteco.sigma.model.entidades.Version;

/**
 * DAO referente à controle de
 * versões. Os dados do controle
 * de versões não podem ser
 * normalmente excluídos, então
 * eles serão privados.
 * @author Juliano Felipe da Silva
 */
public interface VersionDAO extends GenericDAO<Version>{
    int createMajorRelease(String name, Date date, String notes) throws DatabaseException;
    
    int createMinorRelease(int majorVer, Date date, String notes) throws DatabaseException;
    
    Version fetchLatestVersion() throws DatabaseException;
    
}

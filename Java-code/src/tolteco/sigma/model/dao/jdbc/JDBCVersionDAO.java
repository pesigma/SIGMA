/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao.jdbc;

import java.util.Date;
import tolteco.sigma.model.dao.VersionDAO;
import tolteco.sigma.model.entidades.Version;

/**
 * DAO para criar releases, retornar descrições, e nomes
 * das últimas versões, etc.
 * @author Juliano Felipe
 */
public class JDBCVersionDAO extends JDBCAbstractDAO implements VersionDAO{

    @Override
    public void createMajorRelease(String name, String notes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createMinorRelease(Date date, String notes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Version fetchLatestVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

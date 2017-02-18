/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.dao;

import java.util.Date;
import tolteco.sigma.model.entidades.Major;
import tolteco.sigma.model.entidades.Minor;
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
    /**
     * Cria uma Release "Major".
     * 
     * @param name da Versão Maior.
     * @param date Data de release da versão.
     * @param notes Notas associadas.
     * @return Id da versão inserida, ou -1 em falha.
     * @throws DatabaseException em erro.  em erro.
     */
    int createMajorRelease(String name, Date date, String notes) throws DatabaseException;
    
    /**
     * Cria uma release "Maior". Chama o método 
     * {@link #createMajorRelease(java.lang.String, java.util.Date, java.lang.String)},
     * propagando os parâmetros adequadamente.
     * 
     * @param major versão major a ser criada.
     * @return ID da versão major inserida, ou -1 em falha.
     * @throws DatabaseException em erro.
     */
    default int createMajorRelease(Major major) throws DatabaseException{
        return createMajorRelease(major.getMajorName(), major.getMajorDate(), major.getMajorNotes());
    }
    
    /**
     * Cria uma release "Menor".
     * 
     * @param majorVer ID de versão maior associado.
     * @param date Data de release da versão.
     * @param notes Notas associadas.
     * @return ID da versão menor inserida, ou -1 em falha.
     * @throws DatabaseException em erro.
     */
    int createMinorRelease(int majorVer, Date date, String notes) throws DatabaseException;
    
    /**
     * Cria uma release "Menor". Chama o método {@link #createMinorRelease(int, java.util.Date, java.lang.String)},
     * propagando os parâmetros adequadamente.
     * 
     * @param minor versão menor a ser criada.
     * @return ID da versão menor inserida, ou -1 em falha.
     * @throws DatabaseException em erro.
     */
    default int createMinorRelease(Minor minor) throws DatabaseException{
        return createMinorRelease(minor.getMajorVer(), minor.getMinorDate(), minor.getMinorNotes());
    }
    
    /**
     * Retorna a última versão cadastrada.
     * Em essência, pega a útima "Minor" com
     * {@link #getLatestMinor()} e a última
     * "Major", com {@link #getLatestMajor()}
     * e as une com {@link #versionBuilder(tolteco.sigma.model.dao.VersionDAO.Major, tolteco.sigma.model.dao.VersionDAO.Minor)}.
     * 
     * @return Última Version.
     * @throws DatabaseException em erro.
     */
    Version fetchLatestVersion() throws DatabaseException;
    
    /**
     * Retorna a última Major Version registrada.
     * @return Major Version.
     * @throws DatabaseException em erro.
     */
    Major getLatestMajor() throws DatabaseException;
    
    /**
     * Retorna a Major com a chave
     * passada. 
     * @param primaryKey da Major
     * @return MajorVersion
     * @throws DatabaseException em erro.
     */
    Major getMajor(int primaryKey) throws DatabaseException;
    
    /**
     * Retorna a última Minor Version registrada.
     * @return Minor Version.
     * @throws DatabaseException em erro.
     */
    Minor getLatestMinor() throws DatabaseException;
    
    /**
     * Pega a última "Minor Version" associada ao id
     * da Major passado como parâmetro.
     * 
     * @param majorVer da Major Version.
     * @return Última Minor Version.
     * @throws DatabaseException em erro.
     */
    Minor getLatestMinor(int majorVer) throws DatabaseException;
        
    /**
     * Acopla um objeto {@link tolteco.sigma.model.entidades.Major} e um de 
     * {@link tolteco.sigma.model.entidades.Minor} para ter ambas as
     * informações unidas na classe {@link tolteco.sigma.model.entidades.Version}.
     * @param major
     * @param minor
     * @return 
     */
    default Version versionBuilder(Major major, Minor minor){
        return new Version(major.getMajorVer(),
                           major.getMajorName(), 
                           major.getMajorDate(), 
                           major.getMajorNotes(), 
                
                           minor.getMinorVer(), 
                           minor.getMinorDate(), 
                           minor.getMinorNotes());
    }
    
    /**
     * Cria uma versão "Menor" acoplada à última
     * versão "Maior" existente. Para isso, obtém-se
     * o id da última "Maior" pelo getter utilizando o
     * retorno do método {@link #getLatestMajor()}.
     * <p>
     * Ao obter o id, chama-se o método {@link #createMinorRelease(int, java.util.Date, java.lang.String)},
     * passando o id e os outros parâmetros passados aqui.
     * 
     * @param date reference ao lançamento da release menor.
     * @param notes associadas à release menor.
     * @return True em sucesso; falso em falha.
     * @throws DatabaseException em erro.
     */
    default boolean createAutoMinorRelease(Date date, String notes) throws DatabaseException{
        int latestMajor = getLatestMajor().getMajorVer();
        int retId = createMinorRelease(latestMajor, date, notes);
        
        return retId >= 0; //Verdadeiro se ID é maior ou igual a zero (Sucesso).
    }
}

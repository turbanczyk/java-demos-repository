/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Configuration is the class which is responsible for set all application
 * configuration parameters.
 * 
 * Configuration object is responsible for check that current configuration exist
 * and if not to create preliminary application configuration.
 *  
 * @author tomeku
 */
@Getter
@Setter
public class Configuration {
    
    private Path configurationDirectoryPath;
    private boolean configurationDataFolderExist;
    
    /**
     * Class constructor with one parameter.
     * @param configurationDirectoryPath Parameter determines where configuration
     * folder should be located.
     */
    public Configuration (Path configurationDirectoryPath) {
        Preconditions.checkArgument(configurationDirectoryPath != null, 
                "configurationDirectoryPath can't be null");
        this.configurationDirectoryPath = configurationDirectoryPath;
        
        if(Files.exists(configurationDirectoryPath)){
            configurationDataFolderExist = true;
        } else {
            configurationDataFolderExist = false;
        }
    }
    
    /**
     * The method used to create application configuration folder where all 
     * configuration files will be storage
     * @return Return true if the operation is successful.
     * @throws IOException Throw exception if operation of creation application 
     * configuration folder fail.
     */
    public boolean createApplicationDataFolder () throws IOException {
        boolean i = true;
        if(!configurationDataFolderExist){
            Files.createDirectories(configurationDirectoryPath);
            configurationDataFolderExist = true;
        } else return false;
        
        return i;
    }
    
    /**
     * The method used to create prelimiary list of companies (objects Company) in
     * case where such list doesn't exist.
     * @param xmlStorage Parameter of object XMLStorage necesseary for create xml file
     * @return Return true if the operation is successful.
     * @throws TransformerException Throw exception if operation of creation prelimiary
     * list fail.
     * @throws ParserConfigurationException Throw exception if operation of creation
     * preliminary list fail.
     */
    public boolean createPreliminaryListOfCompanies (XMLStorage xmlStorage) 
            throws TransformerException, ParserConfigurationException {
        
        Preconditions.checkArgument(xmlStorage != null, 
                "Object xmlStorage can't be null");
        Preconditions.checkArgument(configurationDataFolderExist == true, 
                "Configuration data folder must be first create.");
        
        boolean i = true;
        //create new list if in directory is not existing xml file
        File f = new File(configurationDirectoryPath+File.separator+"XMLDataFile.xml");
        if(!f.exists()){
            ArrayList<Company> c = new ArrayList<Company>();
            c.add(new Company("CD-PROJEKT", "CD Projekt S.A.", "WIG20"));
            c.add(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
            c.add(new Company("PKO", "Powszechna Kasa Oszczędności Bank Polski S.A.", "WIG20"));
            c.add(new Company("PEKAO", "Bank Polska Kasa Opieki S.A.", "WIG20"));
            c.add(new Company("PZU", "Powszechny Zakład Ubezpieczeń S.A.", "WIG20"));
            c.add(new Company("PKN-ORLEN", "Polski Koncern Naftowy Orlen S.A.", "WIG20"));
            c.add(new Company("KGHM", "KGHM Polska Miedź S.A.", "WIG20"));
            c.add(new Company("LPP", "LPP S.A.", "WIG20"));
            c.add(new Company("DNP", "Dino S.A.", "WIG20"));
            c.add(new Company("ALE", "Allegro.EU S.A.", "WIG20"));
            c.add(new Company("SPL", "Santander Bank Polska S.A.", "WIG20"));
            c.add(new Company("PGNIG", "Polskie Górnictwo Naftowe i Gazownictwo S.A.", "WIG20"));
            c.add(new Company("CYFROWY-POLSAT", "Cyfrowy Polsat S.A.", "WIG20"));
            c.add(new Company("PGE", "PGE Polska Grupa Energetyczna S.A.", "WIG20"));
            c.add(new Company("ORANGE", "Orange Polska S.A.", "WIG20"));
            c.add(new Company("LOTOS", "Grupa Lotos S.A.", "WIG20"));
            c.add(new Company("ASSECO-POLAND", "Asseco Poland S.A.", "WIG20"));
            c.add(new Company("JSW-JASTRZEBSKA-SPOLKA-WEGLOWA", "Jastrzębska Spółka Węglowa S.A.", "WIG20"));
            c.add(new Company("CCC", "CCC S.A.", "WIG20"));
            c.add(new Company("MERCATOR", "Mercator Medical S.A.", "WIG20"));
            c.add(new Company("AMICA", "Amica Wronki S.A.", "mWIG40"));
            
            //create default file
            xmlStorage.setCompanies(c);
        } else return false;
        
        return i;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author tomeku
 */
public class ConfigurationTest {
    
    public ConfigurationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        System.out.println("testConstructor");
        
        //test null argument
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Configuration n = new Configuration(null);
        });
        
        //test boolean value
        String directoryWithXMLfile = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"
                +File.separator+"fundamentalanalysis"+File.separator+"testData";
        //true conditions
        Configuration c = new Configuration(Paths.get(directoryWithXMLfile));
        assertThat(c.isConfigurationDataFolderExist()).isEqualTo(true);
        //false conditions
        c = new Configuration(Paths.get(Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"sr"));
        assertThat(c.isConfigurationDataFolderExist()).isEqualTo(false);
    }
    
    /**
     * Test of createApplicationDataFolder method, of class Configuration.
     */
    @Test
    public void testCreateApplicationDataFolder() /*throws Exception*/ {
        System.out.println("createApplicationDataFolder");
        //create path to directory with temporary files
        
        String src = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testTemporaryFiles"+
                File.separator+"FundamentalAnalysisConfiguration";
        Configuration c = new Configuration(Paths.get(src));
        boolean b = false;
        try{
            b = c.createApplicationDataFolder();
        } catch (Exception e){
            fail(e);
        }
        assertThat(b).isEqualTo(true);
        //check change in boolean condition
        assertThat(c.isConfigurationDataFolderExist()).isEqualTo(true);
        //clean
        File f = new File(src);
        f.delete();
        
    }

    /**
     * Test of createPreliminaryListOfCompanies method, of class Configuration.
     */
    @Test
    public void testCreatePreliminaryListOfCompanies() throws Exception {
        System.out.println("createPreliminaryListOfCompanies");
        //check null
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Configuration n = new Configuration(Paths.get("path"));
           n.createPreliminaryListOfCompanies(null);
        });
        
        //next
        
        String src = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testTemporaryFiles";
        Configuration c = new Configuration(Paths.get(src));
        boolean b = c.createPreliminaryListOfCompanies(new XMLStorage(src));
        assertThat(b).isEqualTo(true);
        File f = new File(src+File.separator+"XMLDataFile.xml");
        assertThat(f.exists()).isEqualTo(true);
        //clean
        f.delete();
    }

    /**
     * Test of getConfigurationFilesPath method, of class Configuration.
     */
    @Test
    public void testGetConfigurationDirectoryPath() {
        System.out.println("getConfigurationFilesPath");
        Configuration c = new Configuration(Paths.get("/somewhere"));
        assertThat(c.getConfigurationDirectoryPath()).isEqualTo(Paths.get("/somewhere"));
    }

    /**
     * Test of isPreliminaryConfigurationDidntExist method, of class Configuration.
     */
    @Test
    public void testIsConfigurationDataFolderExist() {
        System.out.println("isPreliminaryConfigurationDidntExist");
        //test with right values
        String src = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testTemporaryFiles";
        Configuration c = new Configuration(Paths.get(src));
        assertThat(c.isConfigurationDataFolderExist()).isEqualTo(true);
        //test with not existing directory
        String src2 = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testTemporaryFileeeeeeeeee";
        Configuration c2 = new Configuration(Paths.get(src2));
        assertThat(c2.isConfigurationDataFolderExist()).isEqualTo(false);
    }

    /**
     * Test of setConfigurationFilesPath method, of class Configuration.
     */
    @Test
    public void testSetConfigurationDirectoryPath() {
        System.out.println("setConfigurationFilesPath");
        Configuration c = new Configuration(Paths.get("/somewhere"));
        c.setConfigurationDirectoryPath(Paths.get("/elsewhere"));
        assertThat(c.getConfigurationDirectoryPath()).isEqualTo(Paths.get("/elsewhere"));
    }

    /**
     * Test of setPreliminaryConfigurationDidntExist method, of class Configuration.
     */
    @Test
    public void testSetConfigurationDataFolderExist() {
        System.out.println("setPreliminaryConfigurationDidntExist");
        Configuration c = new Configuration(Paths.get("/somewhere22222"));
        assertThat(c.isConfigurationDataFolderExist()).isEqualTo(false);
        c.setConfigurationDataFolderExist(true);
        assertThat(c.isConfigurationDataFolderExist()).isEqualTo(true);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import java.nio.file.Paths;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
/**
 *
 * @author tomeku
 */
public class XMLStorageTest {
    
    private XMLStorage storage;
    
    public XMLStorageTest() {
        //set prelimiary data 
        String testPath = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testData";
        storage = new XMLStorage(testPath);
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
    public void testConstructor() throws Exception {
        System.out.println("testConstructor");
        
        //check null
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           XMLStorage t = new XMLStorage(null);
        });
        
        //check empty
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           XMLStorage t = new XMLStorage("");
        });
    }
    
    /**
     * Test of getAllCompanies method, of class XMLStorage.
     */
    @Test
    public void testGetAllCompanies() throws Exception {
        System.out.println("getAllCompanies");
        
        ArrayList<Company> list = storage.getAllCompanies();
        for (int i =0; i < storage.getAllCompanies().size(); i++){
            assertThat(storage.getAllCompanies().get(i).getCompanyId()).isEqualTo(list.get(i).getCompanyId());
            assertThat(storage.getAllCompanies().get(i).getCompanyName()).isEqualTo(list.get(i).getCompanyName());
            assertThat(storage.getAllCompanies().get(i).getIndex()).isEqualTo(list.get(i).getIndex());
        }
    }

    /**
     * Test of getXmlPath method, of class XMLStorage.
     */
    @Test
    public void testGetConfigurationFolderPath() {
        System.out.println("getXmlPath");
        
        String testPath = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testData";
        
        assertThat(storage.getConfigurationFolderPath()).isEqualTo(testPath);
    }

    /**
     * Test of setXmlPath method, of class XMLStorage.
     */
    @Test
    public void testSetConfigurationFolderPath() {
        System.out.println("setXmlPath");
        
        XMLStorage s = new XMLStorage ("path");
        s.setConfigurationFolderPath("newPath/newPath");
        assertThat(s.getConfigurationFolderPath()).isEqualTo("newPath/newPath");
    }

    /**
     * Test of setCompanies method, of class XMLStorage.
     */
    @Test
    public void testSetCompanies() throws Exception {
        System.out.println("setCompanies");
        
        //create path to directory with temporary files
        String temporaryTestPath = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testTemporaryFiles";
        //create conditions for test
        ArrayList<Company> companiesList = new ArrayList<Company>();
        companiesList.add(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
        companiesList.add(new Company("CD-PROJEKT", "CD Projekt S.A.", "WIG20"));
        storage.setConfigurationFolderPath(temporaryTestPath);
        //test condition that file exist
        File f = new File(storage.getConfigurationFolderPath()+File.separator+"XMLDataFile.xml");
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           //create file in place where will be created by method file
           f.createNewFile();
           storage.setCompanies(companiesList);
        });
        //clean
        f.delete();
        
        //next test
        storage.setCompanies(companiesList);
        ArrayList<Company> pp = storage.getAllCompanies();
        //check sizes
        assertThat(pp.size()).isEqualTo(2);
        //compare each element
        for(int i=0; i< pp.size(); i++){
            assertThat(pp.get(i).getCompanyId()).isEqualTo(companiesList.get(i).getCompanyId());
            assertThat(pp.get(i).getCompanyName()).isEqualTo(companiesList.get(i).getCompanyName());
            assertThat(pp.get(i).getIndex()).isEqualTo(companiesList.get(i).getIndex());
        }
        
        //clean, remove created during test XMLDataFile.xml
        File f2 = new File(storage.getConfigurationFolderPath()+File.separator+"XMLDataFile.xml"); 
        f.delete();
        
    }
    
}

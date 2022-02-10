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
import java.util.Scanner;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author tomeku
 */
public class ErrorReportTest {
    
    public ErrorReportTest() {
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
        System.out.println("saveInFile");
        //test with null or empty
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           ErrorReport e = new ErrorReport (null);
        });
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           ErrorReport e = new ErrorReport ("");
        });
    }
    
    /**
     * Test of saveInFile method, of class ErrorReport.
     */
    @Test
    public void testSaveInFile() throws Exception {
        System.out.println("saveInFile");
        ErrorReport e = new ErrorReport ("Kod błędu");
        //test creation of file
        String src = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testTemporaryFiles";
        String b = e.saveInFile(Paths.get(src));
        assertThat(b).isNotEmpty();
        //test file was created
        File f = new File(src, b);
        assertThat(f.exists()).isEqualTo(true);
        //test writing correct wording to file
        Scanner reader = new Scanner(f);
        String str = "";
        while (reader.hasNextLine()) {
          str = reader.nextLine();
        }
        reader.close();
        assertThat(str).isEqualTo("Kod błędu");
        //clean
        f.delete();
    }

    /**
     * Test of showErrorInFrame method, of class ErrorReport.
     */
    @Test
    @Disabled
    public void testShowErrorInFrame() {
        System.out.println("showErrorInFrame");
        
    }

    /**
     * Test of showErrorInConsole method, of class ErrorReport.
     */
    @Test
    @Disabled
    public void testShowErrorInConsole() {
        System.out.println("showErrorInConsole");
        
    }

    /**
     * Test of getErrorMessage method, of class ErrorReport.
     */
    @Test
    public void testGetErrorMessage() {
        System.out.println("getErrorMessage");
        ErrorReport r = new ErrorReport("komunikat błędu");
        assertThat(r.getErrorMessage()).isEqualTo("komunikat błędu");
    }

    /**
     * Test of setErrorMessage method, of class ErrorReport.
     */
    @Test
    public void testSetErrorMessage() {
        System.out.println("setErrorMessage");
        ErrorReport r = new ErrorReport("komunikat błędu");
        r.setErrorMessage("nowy komunikat");
        assertThat(r.getErrorMessage()).isEqualTo("nowy komunikat");
    }
    
}

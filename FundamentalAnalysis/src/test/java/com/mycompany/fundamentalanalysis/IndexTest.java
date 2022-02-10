/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tomeku
 */
public class IndexTest {
    
    private ArrayList<Company> c;
    
    public IndexTest() {
        c = new ArrayList<Company>();
        c.add(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
        c.add(new Company("CD-PROJEKT", "CD Projekt S.A.", "WIG20"));
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
        
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Index t = new Index(null);
        });
    }
    
    /**
     * Test of getComp method, of class Index.
     */
    @Test
    public void testGetCompanyList() {
        System.out.println("getCompanyList");
        
        //check size
        assertThat(c.size()).isEqualTo(2);
        
        //check all elements
        ArrayList<Company> testList = new ArrayList<Company>();
        testList.add(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
        testList.add(new Company("CD-PROJEKT", "CD Projekt S.A.", "WIG20"));
        
        for (int i =0; i < c.size(); i++) {
            assertThat(c.get(i).getCompanyId()).contains(testList.get(i).getCompanyId());
            assertThat(c.get(i).getCompanyName()).contains(testList.get(i).getCompanyName());
            assertThat(c.get(i).getIndex()).contains(testList.get(i).getIndex());
        }
    }

    /**
     * Test of addCompanyToIndex method, of class Index.
     */
    @Test
    public void testAddCompanyToIndex() {
        System.out.println("addCompanyToIndex");
        
        //check null argument
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Index t = new Index();
           t.addCompanyToIndex(null);
        });
        
        //next
        Index i = new Index();
        i.addCompanyToIndex(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
        i.addCompanyToIndex(new Company("CD-PROJEKT", "CD Projekt S.A.", "WIG20"));
        
        assertThat(i.getCompanyList().size()).isEqualTo(2);
    }

    /**
     * Test of buildIndex method, of class Index.
     */
    @Test
    public void testBuildIndex() {
        System.out.println("buildIndex");
        
        //check with null
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Index t = new Index();
           t.buildIndex(null, "WIG20");
        });
        
        //check with illegal index
        ArrayList<Company> list = new ArrayList<Company>();
           list.add(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
           list.add(new Company("CD-PROJEKT", "CD Projekt S.A.", "WIG20"));
           list.add(new Company("AMICA", "Amica Wronki S.A.", "mWIG40"));
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Index t = new Index();
           t.buildIndex(list, "jakis");
        });
        
        //check adding
        Index i = new Index();
        assertThat(i.buildIndex(list, "WIG20").getCompanyList().size()).isEqualTo(2);
        assertThat(i.buildIndex(list, "mWIG40").getCompanyList().size()).isEqualTo(1);
    }
    
}

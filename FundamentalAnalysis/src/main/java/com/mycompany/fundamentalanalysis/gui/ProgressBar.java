/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis.gui;

import com.google.common.base.Preconditions;

import javax.swing.JProgressBar;
import javax.swing.JFrame;

/**
 * ProgressBar is the class which allow an application to 
 * show the progress of the process.
 *  
 * @author tomeku
 */
public class ProgressBar extends JFrame{
    
    /**
     * Progress bar object.
     */
    private final JProgressBar jb;        
    
    /**
     * Constructor with no parameters. 
     */
    public ProgressBar() {    
        jb=new JProgressBar(0,100);    
        jb.setBounds(40,40,160,30);         
        jb.setValue(0);    
        jb.setStringPainted(true);
        add(jb);    
        setSize(250,150);    
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pobieranie danych...");
    }    
    
    /**
     * The method used to set progress value.
     * @param settedValue Parameter specifying the advance.
     */
    public void setPercents(int settedValue){
        Preconditions.checkArgument(settedValue<=jb.getMaximum(), 
                "Progress bar value can't be higher than maximum");
        jb.setValue(settedValue);
    }
    
    /**
     * The method used to close progress bar windows.
     */
    public void closeProgressBar() {
        dispose();
    } 
    
    /**
     * The method used to set maximum value of process progress.
     * @param settedValue Parameter specifying the maximum value of process progress.
     */
    public void setMaximum(int settedValue) {
        jb.setMaximum(settedValue);
    }
}

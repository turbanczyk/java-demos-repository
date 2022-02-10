/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.FileWriter;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;

/**
 * ErrorReport is the class which allow an application to 
 * handle with error message.
 * 
 * ErrorReport object is responsible for show error message in standard output,
 * graphical windows form and save error message to file.
 *  
 * @author tomeku
 */
@Getter
@Setter
public class ErrorReport {
    
    private String errorMessage;
    
    /**
     * Class constructor with one parameter.
     * @param errorMessage Parameter is error message.
     */
    public ErrorReport (String errorMessage) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(errorMessage), 
                "Error message can't be empty or null");
        this.errorMessage = errorMessage;
    }
    
    /**
     * The method used to save in file error message.
     * @param directory Parameter determine directory where error message will be
     * save
     * @return Return name of file where error message was saved.
     * @throws IOException Throw exception in case where operation failed. 
     */
    public String saveInFile (Path directory) throws IOException  {
        Preconditions.checkArgument(directory != null, 
                "Argument directory can't be null");
        
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        
        String fileName = "error log " + date.format(DateTimeFormatter.ofPattern("yy-MM-dd")) +
                " " + time.format(DateTimeFormatter.ofPattern("HH-mm-ss-SSS"));
        
        File errorFile = new File(new File(directory.toUri()), fileName);
        errorFile.createNewFile();
        FileWriter saveToFile = new FileWriter (errorFile);
        saveToFile.write(errorMessage);
        saveToFile.close();
        return fileName;
    }
    
    /**
     * The method used to show in graphical form error message (as a window).
     */
    public void showErrorInFrame () {
        JOptionPane.showMessageDialog(null, errorMessage, "Błąd programu", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * The method used to show in standard system output error message. 
     */
    public void showErrorInConsole () {
        System.out.println(errorMessage);
    }
}

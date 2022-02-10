/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import lombok.Getter;
import lombok.Setter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.ArrayList;

/**
 * Index is the class which allow an application to 
 * agregate information about group of companies which belon to the same index
 * (group of Company objects).
 * A Index object encapsulate group of Company objects.
 *  
 * @author tomeku
 */
@Getter
public class Index {
    
    private ArrayList<Company> companyList;
    
    /**
     * Constructor with one parameter.
     * @param listOfCompanies Parameter lisf of Company objects.
     */
    public Index (ArrayList<Company> listOfCompanies){
        Preconditions.checkArgument(companyList != null, 
                "Argument can't be null");
        this.companyList = listOfCompanies;
    }
    
    /**
     * Constructor without parameters.
     */
    public Index () {
        companyList = new ArrayList<Company>();
    }
    
    /**
     * The method used to add object Company to index.
     * @param c Parameter Company which will be add to index.
     */
    public void addCompanyToIndex (Company c) {
        Preconditions.checkArgument(c != null, 
                "Argument can't be null");
        
        companyList.add(c);
    }
    
    /**
     * The method used to build specific index (WIG20 or mWIG40 or sWIG80) from
     * objects Company.
     * @param listOfCompanies Paramter specifying the group of companies 
     * (objects Company) from which the index will be built 
     * @param indexId Parameter determine index name (only names are
     * allowed: WIG20, mWIG40, sWIG80
     * @return Return object Index which contains a group of companies.
     */
    public Index buildIndex (ArrayList<Company> listOfCompanies, String indexId){
        Preconditions.checkArgument(listOfCompanies != null, 
                "ArrayList can't be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(indexId), 
                "Index can't be null.");
        Preconditions.checkArgument(indexId.equals("WIG20") || indexId.equals("mWIG40") 
                || indexId.equals("sWIG80"), 
                "Index can be only: WIG20 or mWIG40 or sWIG80.");
        
        Index ind = new Index();
        for (int i =0; i < listOfCompanies.size(); i++){
            if (listOfCompanies.get(i).getIndex().equals(indexId)){
                ind.addCompanyToIndex(listOfCompanies.get(i));
            }
        }
        return ind;
    }
}

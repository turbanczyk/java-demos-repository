/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.io.IOException;
import java.io.File;

import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import lombok.Getter;
import lombok.Setter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XMLStorage is the class which allow an application to 
 * save information on hard drive in form of xml document and read them.
 * 
 * XMLStorage object is responsible for read information from xml file and
 * save information to xml file
 *  
 * @author tomeku
 */
@Getter
@Setter
public class XMLStorage {
    
    private String configurationFolderPath;
    
    /**
     * Constructor with one parameter.
     * @param configurationFolderPath Parameter is path to configuration directory.
     */
    public XMLStorage(String configurationFolderPath) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(configurationFolderPath), 
                "Patch to directory can't be empty or null");
        this.configurationFolderPath = configurationFolderPath;
    }
    
    /**
     * The method used to gathering list of objects Company from xml file.
     * @return Return list of objects Company.
     * @throws ParserConfigurationException Throw exception when method fail.
     * @throws SAXException Throw exception when method fail.
     * @throws IOException Throw exception when method fail.
     */
    public ArrayList<Company> getAllCompanies() throws ParserConfigurationException, 
            SAXException , IOException {
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(configurationFolderPath+File.separator + "XMLDataFile.xml"));
        
        doc.getDocumentElement().normalize();
        
        NodeList list = doc.getElementsByTagName("company");
        
        ArrayList<Company> companies = new ArrayList<Company>();
        
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                Company c = new Company(element.getElementsByTagName("id").item(0).getTextContent(), 
                    element.getElementsByTagName("companyname").item(0).getTextContent(), 
                    element.getElementsByTagName("index").item(0).getTextContent());
                companies.add(c);
            }
        }
        return companies;
    }
    
    /**
     * The method used to save list of object Company to xml file.
     * @param companiesList List of objects Company which will be save do xml file.
     * @throws TransformerException Throw exception when method fail.
     * @throws ParserConfigurationException Throw exception when method fail.
     */
    public void setCompanies(ArrayList<Company> companiesList) throws TransformerException, 
            ParserConfigurationException {
        Preconditions.checkArgument(!companiesList.isEmpty() || companiesList != null, 
                "Companies list argument can't be empty or null");
        
        //check condition that file doesn't exist
        File f = new File(configurationFolderPath+File.separator+"XMLDataFile.xml");
        if(f.exists()){
            throw new IllegalArgumentException("File XMLDataFile.xml already exist in this direction.");
        } else {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // create root element
            Element root = document.createElement("companies");
            document.appendChild(root);

            for (int i =0; i<companiesList.size(); i++){
                // company element
                Element company = document.createElement("company");
                root.appendChild(company);

                // id element
                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(companiesList.get(i).getCompanyId()));
                company.appendChild(id);

                // companyname element
                Element companyname = document.createElement("companyname");
                companyname.appendChild(document.createTextNode(companiesList.get(i).getCompanyName()));
                company.appendChild(companyname);

                // index element
                Element index = document.createElement("index");
                index.appendChild(document.createTextNode(companiesList.get(i).getIndex()));
                company.appendChild(index);
            }

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(configurationFolderPath)
                    +File.separator+"XMLDataFile.xml");

            transformer.transform(domSource, streamResult);
        }
    } 
}


package ua.epam.beerparser;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.epam.beer.Beer;
import ua.epam.beer.BeerChars;
import static ua.epam.beerparser.BeerParser.validate;

/**
 * (SAX) XML Beer Parser
 * Based on materials taken from
 * javacodegeeks.com and mkyoung.com
 * 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 12.05.2014
 */
public class BeerSAXParser extends DefaultHandler {
    
    //Declare initial variables
    private static ArrayList<Beer> beerList;
    private Beer currentBeer;
    private BeerChars currentChars;
    private StringBuilder dataBuffer;
    
    /**
     * Take actions at the start of each element
     * @param uri NameSpace URI
     * @param localName local name without prefix
     * @param qName qualified name with prefix
     * @param attributes attributes of the current element
     * @throws SAXException in case of SAX error or warnings
     */
    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            
            //In case of Bottle, create new Beer Object and pull attributes
            case "Bottle":
                currentBeer = new Beer();
                currentBeer.setID(attributes.getValue("id"));
                break;
            
            //In case of Chars, create new BeerChars object and pull attributes
            case "Chars":
                currentChars = new BeerChars();
                if(attributes.getValue("abv") != null) currentChars.setABV(attributes.getValue("abv")); //rescue for optional abv attribute
                currentChars.setIsFiltered(attributes.getValue("filtered"));
                currentChars.setNutrition(attributes.getValue("nutrition"));
                currentChars.setTransparency(attributes.getValue("transparency"));
                break;
        }
    }

    /**
     * Take actions at the end of each element
     * @param uri NameSpace URI
     * @param localName local name without prefix
     * @param qName qualified name with prefix
     * @throws SAXException in case of SAX errors or warnings
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName) {
            
            //means end of the Bottle element, Beer object is ready to use
            case "Bottle":
                currentBeer.setChars(currentChars);
                beerList.add(currentBeer);
                break;
                
            //In all other cases, set appropriate fields with info from the buffer
            case "Name":
                currentBeer.setName(dataBuffer.toString());
                break;
            case "Type":
                currentBeer.setType(dataBuffer.toString());
                break;
            case "Al":
                currentBeer.setIsAlcoholic(dataBuffer.toString());
                break;
            case "Manufacturer":
                currentBeer.setManufacturer(dataBuffer.toString());
                break;
            case "Ingredients":
                currentBeer.setIngredients(dataBuffer.toString());
                break;
            case "volume":
                currentChars.setVolume(dataBuffer.toString());
                break;
            case "pack":
                currentChars.setPack(dataBuffer.toString());
                break;
        }
    }

    /**
     * Take actions for each chunk of character data
     * @param ch characters
     * @param start start position of the character array
     * @param length length of current chunk of data
     * @throws SAXException in case of SAX errors or warnings
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        dataBuffer = new StringBuilder();
        dataBuffer.append(ch, start, length);
    }
    
    /**
     * Parse Beer XML file using SAX Parser
     * @return ArrayList of parsed Beer Objects
     */
    public static ArrayList<Beer> parseBeer() {
        
        //validate the Beer XML file
        if(!validate()) throw new RuntimeException("XML File is not Valid");
        
        beerList = new ArrayList();
        String path = "/Users/DruidKuma/Dropbox/EPAM Trainings/Works/Tasks/Task 3/src/ua/epam/beer/beer.xml";
        
        try {
            //create new SAX parser and handler
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new BeerSAXParser();
            
            //parse the XML file
            saxParser.parse(path, handler); 
        } 
        
        //In case of IO, configuration or SAX errors or warnings
        catch(IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e.getMessage());
        }
        
        return beerList;
    }
}


package ua.epam.beerparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import ua.epam.beer.Beer;
import ua.epam.beer.BeerChars;
import static ua.epam.beerparser.BeerParser.validate;

/**
 * (StAX) XML Beer Parser
 * Based on materials taken from
 * javacodegeeks.com and mkyoung.com
 * 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 13.05.2014
 */
public class BeerStAXParser extends BeerParser {
    
    /**
     * Create Streamed XML Reader for the Beer XML file
     * @return Beer Reader, ready for use
     */
    private static XMLStreamReader createBeerReader() {
        
        //location of the xml file
        String path = "/Users/DruidKuma/Dropbox/EPAM Trainings/Works/Tasks/Task 3/src/ua/epam/beer/beer.xml";
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader beerReader = factory.createXMLStreamReader(new FileInputStream(path));
            return beerReader;
        } 
        
        //In case of file not found or unexpected processing errors
        catch(FileNotFoundException | XMLStreamException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Parse Beer XML file using StAX Parser
     * @return ArrayList of Beer objects from the XML file
     */
    public static ArrayList<Beer> parseBeer() {
        
        //validate the Beer XML file
        if(!validate()) throw new RuntimeException("XML File is not Valid");
        
        //get stream reader
        XMLStreamReader beerReader = createBeerReader();
        
        //declare initial variables
        ArrayList<Beer> beerList = new ArrayList();
        Beer currentBeer = new Beer();
        BeerChars currentChars = new BeerChars();
        String data = "";
        
        try {
            
            //while not end of the file
            while(beerReader.hasNext()) {
                
                //get next piece
                int event = beerReader.next();
                
                switch(event) {
                    
                    //if piece is starting tag
                    case XMLStreamConstants.START_ELEMENT:
                        
                        //if tag is Bottle, create new Beer Object and get possible attributes
                        if(beerReader.getLocalName().equals("Bottle")) {
                            currentBeer = new Beer();
                            currentBeer.setID(beerReader.getAttributeValue(0));
                        }
                        
                        //if tag is Chars, create new BeerChars Object and get all attributes
                        else if(beerReader.getLocalName().equals("Chars")) {
                            currentChars = new BeerChars();
                            int attrIndex = 0;
                            
                            //rescue from optional "abv" attribute
                            if(beerReader.getAttributeCount() >= 4) {
                                currentChars.setABV(beerReader.getAttributeValue(attrIndex));
                                attrIndex++;
                            }
                            
                            currentChars.setTransparency(beerReader.getAttributeValue(attrIndex));
                            attrIndex++;
                            currentChars.setIsFiltered(beerReader.getAttributeValue(attrIndex));
                            attrIndex++;
                            currentChars.setNutrition(beerReader.getAttributeValue(attrIndex));
                        }
                        break;
                    
                    //if piece is just a CDATA, accumulate it to buffer
                    case XMLStreamConstants.CHARACTERS:
                        data = beerReader.getText();
                        break;
                    
                    //if piece is ending tag, set appropriate field from the buffer
                    case XMLStreamConstants.END_ELEMENT:
                        switch(beerReader.getLocalName()) {
                            case "Name":
                                currentBeer.setName(data);
                                break;
                            case "Type":
                                currentBeer.setType(data);
                                break;
                            case "Al":
                                currentBeer.setIsAlcoholic(data);
                                break;
                            case "Manufacturer":
                                currentBeer.setManufacturer(data);
                                break;
                            case "Ingredients":
                                currentBeer.setIngredients(data);
                                break;
                            case "volume":
                                currentChars.setVolume(data);
                                break;
                            case "pack":
                                currentChars.setPack(data);
                                break;
                                
                            //means end of current Bottle, add ready object to the list
                            case "Chars":
                                currentBeer.setChars(currentChars);
                                beerList.add(currentBeer);
                                break;
                        }
                        break;
                }
            }
        } 
        
        //In case of unexpected processing errors
        catch (XMLStreamException ex) {
            System.out.println(ex.getMessage());
        }
        return beerList;
    }
}

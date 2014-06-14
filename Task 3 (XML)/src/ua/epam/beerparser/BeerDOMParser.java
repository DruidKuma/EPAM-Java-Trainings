
package ua.epam.beerparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.epam.beer.Beer;
import ua.epam.beer.BeerChars;

/**
 * (DOM) XML Beer Parser
 * Based on materials taken from
 * javacodegeeks.com and mkyoung.com
 * 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 13.05.2014
 */
public class BeerDOMParser extends BeerParser {
    
    /**
     * Parse Beer XML file using DOM Parser
     * @return 
     */
    public static ArrayList<Beer> parseBeer() {
        
        //validate the Beer XML file
        if(!validate()) throw new RuntimeException("XML File is not Valid");
        
        ArrayList<Beer> beerList = new ArrayList();
        
        //get Document of the XML File
        Document beerDocument = getBeerDocument();
        
        //get all Bottle pieces from the document
        NodeList nodeList = beerDocument.getElementsByTagName("Bottle");
        
        //Parse each Bottle element into Beer Object separatedly
        for(int pos = 0; pos < nodeList.getLength(); pos++) {
            Node current = nodeList.item(pos);
            beerList.add(parseElement((Element)current));
        }
        return beerList;
    }
    
    /**
     * Get Beer DOM from the XML File
     * @return Document of the Beer XML file
     */
    private static Document getBeerDocument() {
        try {
            File file = new File("/Users/DruidKuma/Dropbox/EPAM Trainings/Works/Tasks/Task 3/src/ua/epam/beer/beer.xml");
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } 
        
        //In case of IO, configuration or SAX errors or warnings
        catch(IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Parse Element into Beer Object
     * @param element element to parse
     * @return parsed Beer Object
     */
    private static Beer parseElement(Element element) {
        
        //Create new Beer Object with attribute
        Beer beer = new Beer();
        beer.setID(element.getAttribute("id"));
        
        //Pull all data from the element into created object
        beer.setName(element.getElementsByTagName("Name").item(0).getTextContent());
        beer.setType(element.getElementsByTagName("Type").item(0).getTextContent());
        beer.setIsAlcoholic(element.getElementsByTagName("Al").item(0).getTextContent());
        beer.setIngredients(element.getElementsByTagName("Ingredients").item(0).getTextContent());
        beer.setManufacturer(element.getElementsByTagName("Manufacturer").item(0).getTextContent());
        
        //Create separatedly Beer Characteristics
        Element beerChars = (Element) element.getElementsByTagName("Chars").item(0);
        BeerChars chars = new BeerChars();
        
        //Pull all data from the element into created object
        if(!beerChars.getAttribute("abv").equals("")) chars.setABV(beerChars.getAttribute("abv")); //rescue for optional abv attribute
        chars.setTransparency(beerChars.getAttribute("transparency"));
        chars.setIsFiltered(beerChars.getAttribute("filtered"));
        chars.setNutrition(beerChars.getAttribute("nutrition"));
        chars.setVolume(beerChars.getElementsByTagName("volume").item(0).getTextContent());
        chars.setPack(beerChars.getElementsByTagName("pack").item(0).getTextContent());
        
        //Concatenate characteristics with the beer
        beer.setChars(chars);
        
        //object is ready to use
        return beer;
    }
}

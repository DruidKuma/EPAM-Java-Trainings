
package ua.epam.beerparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ua.epam.beer.Beer;

/**
 * Beer Writer to XML
 * @author Yuriy Miedviediev
 * @version 1.0 Build 13.05.2014
 */
public class BeerXMLWriter {
    
    /**
     * Serialize given Beer List to XML file
     * @param beerList ArrayList to serialize
     * @throws ParserConfigurationException in case of configuration errors
     * @throws TransformerConfigurationException in case of configuration errors
     * @throws TransformerException in case of configuration errors
     */
    public static void writeBeerToXML(ArrayList<Beer> beerList) throws ParserConfigurationException, 
            TransformerConfigurationException, TransformerException {
        
        //Get new Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder(); 
        
        //new Document and root element
        Document doc = builder.newDocument();
        Element root = doc.createElement("Beer");
        doc.appendChild(root);
        
        for(Beer beer : beerList) {
            
            //new Bottle element with id attribute
            Element bottle = doc.createElement("Bottle");
            bottle.setAttribute("id", beer.getID());
            
            //Serialize Name of the Bottle
            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(beer.getName()));
            bottle.appendChild(name);
            
            //Serialize Type of the Bottle
            Element type = doc.createElement("Type");
            type.appendChild(doc.createTextNode(beer.getType()));
            bottle.appendChild(type);
            
            //Serialize Alc. of the Bottle
            Element al = doc.createElement("Al");
            al.appendChild(doc.createTextNode(Boolean.toString(beer.isAlcoholic())));
            bottle.appendChild(al);
            
            //Serialize Manufacturer of the Bottle
            Element manufacturer = doc.createElement("Manufacturer");
            manufacturer.appendChild(doc.createTextNode(beer.getManufacturer()));
            bottle.appendChild(manufacturer);
            
            //Serialize Ingredients of the Bottle
            Element ingredients = doc.createElement("Ingredients");
            ingredients.appendChild(doc.createTextNode(Arrays.toString(beer.getIngredients())));
            bottle.appendChild(ingredients);
            
            //Serialize Chars of the Bottle
            Element chars = doc.createElement("Chars");
            
            //Set all the attributes
            if(beer.getChars().getABV() > 0.0) chars.setAttribute("abv", Double.toString(beer.getChars().getABV()));
            chars.setAttribute("transparency", Double.toString(beer.getChars().getTransparency()));
            chars.setAttribute("filtered", Boolean.toString(beer.getChars().isFiltered()));
            chars.setAttribute("nutrition", Double.toString(beer.getChars().getNutrition()));
            
            Element draught = doc.createElement("draught");
            
            //Serialize volume
            Element volume = doc.createElement("volume");
            volume.appendChild(doc.createTextNode(Double.toString(beer.getChars().getVolume())));
            draught.appendChild(volume);
            
            //Serialize pack
            Element pack = doc.createElement("pack");
            pack.appendChild(doc.createTextNode(beer.getChars().getPack()));
            draught.appendChild(pack);
            
            chars.appendChild(draught);
            bottle.appendChild(chars);
            
            //Ready Bottle Element
            root.appendChild(bottle);
        }
        
        //Get new Transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        //Set indentation Properties for pretty look
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        //Set source and destination documents
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new File("/Users/DruidKuma/Dropbox/EPAM Trainings/Works/Tasks/Task 3/src/ua/epam/beer/output.xml"));
        
        //do the magic
        transformer.transform(source, result);
    }
    
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        BeerXMLWriter.writeBeerToXML(BeerSAXParser.parseBeer());
    }
}


package ua.epam.beerparser;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ua.epam.beer.Beer;
import ua.epam.beer.BeerChars;

/**
 * Tests for Beer XML Parsers
 * @author Yuriy Miedviediev
 * @version 1.0 Build 13.05.2014
 */
public class BeerParserTest {
    
    private Beer leffe;
    private Beer stella;
    private Beer bud;
    
    private ArrayList<Beer> beerList;
    
    @Before
    public void setUp() {
        BeerChars forLeffe = new BeerChars(5.6, 25, false, 59.0, 0.33, "glass");
        BeerChars forStella = new BeerChars(21, true, 23.0, 0.5, "glass");
        BeerChars forBud = new BeerChars(4.5, 27, false, 45.0, 0.5, "can");
        
        leffe = new Beer("_1", "Leffe", "dark", true, "AnheuserBush", new String[] {"water", "hop", "sugar", "malt"}, forLeffe);
        stella = new Beer("_3", "Stella Artois", "lager", false, "Germany", new String[] {"water", "malt"}, forStella);
        bud = new Beer("_5", "Budweiser", "live", true, "Britain", new String[] {"water", "hop", "sugar", "malt"}, forBud);
    }
    
    @Test
    public void DOMParserParsesAllElementsOfTheXMLDocument() {
        beerList = BeerDOMParser.parseBeer();
        assertSame("DOM Parser does not parse all elements of the XML file", 5, beerList.size());
    }

    @Test
    public void DOMParserCorreclyParsesFirstElement() {
        beerList = BeerDOMParser.parseBeer();
        assertEquals("DOM Parser does not correctly parse first element of the beer XML file", leffe, beerList.get(0));
    }
    
    @Test
    public void DOMParserCorreclyParsesMiddleElement() {
        beerList = BeerDOMParser.parseBeer();
        assertEquals("DOM Parser does not correctly parse middle element of the beer XML file", stella, beerList.get(2));
    }
    
    @Test
    public void DOMParserCorreclyParsesLastElement() {
        beerList = BeerDOMParser.parseBeer();
        assertEquals("DOM Parser does not correctly parse last element of the beer XML file", bud, beerList.get(4));
    }
    
    @Test
    public void SAXParserParsesAllElementsOfTheXMLDocument() {
        beerList = BeerSAXParser.parseBeer();
        assertSame("SAX Parser does not parse all elements of the XML file", 5, beerList.size());
    }
    
    @Test
    public void SAXParserCorrectlyParsesFirstElement() {
        beerList = BeerSAXParser.parseBeer();
        assertEquals("SAX Parser does not correctly parse first element of the beer XML file", leffe, beerList.get(0));
    }
    
    @Test
    public void SAXParserCorrectlyParsesMiddleElement() {
        beerList = BeerSAXParser.parseBeer();
        assertEquals("SAX Parser does not correctly parse middle element of the beer XML file", stella, beerList.get(2));
    }
    
    @Test
    public void SAXParserCorrectlyParsesLastElement() {
        beerList = BeerSAXParser.parseBeer();
        assertEquals("SAX Parser does not correctly parse last element of the beer XML file", bud, beerList.get(4));
    }
    
    @Test
    public void StAXParserParsesAllElementsOfTheXMLDocument() {
        beerList = BeerStAXParser.parseBeer();
        assertSame("StAX Parser does not parse all elements of the XML file", 5, beerList.size());
    }
    
    @Test
    public void StAXParserCorrectlyParsesFirstElement() {
        beerList = BeerStAXParser.parseBeer();
        assertEquals("StAX Parser does not correctly parse first element of the beer XML file", leffe, beerList.get(0));
    }
    
    @Test
    public void StAXParserCorrectlyParsesMiddleElement() {
        beerList = BeerStAXParser.parseBeer();
        assertEquals("StAX Parser does not correctly parse middle element of the beer XML file", stella, beerList.get(2));
    }
    
    @Test
    public void StAXParserCorrectlyParsesLastElement() {
        beerList = BeerStAXParser.parseBeer();
        assertEquals("StAX Parser does not correctly parse last element of the beer XML file", bud, beerList.get(4));
    }
}

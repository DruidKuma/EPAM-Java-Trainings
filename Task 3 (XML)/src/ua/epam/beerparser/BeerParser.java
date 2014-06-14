
package ua.epam.beerparser;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 * Abstract Beer Parser (Validator)
 * @author Yuriy Miediediev
 * @version 1.0 Build 13.05.2014
 */
public abstract class BeerParser {
    public static boolean validate() {
        String xsdPath = "/Users/DruidKuma/Dropbox/EPAM Trainings/Works/Tasks/Task 3/src/ua/epam/beer/beer.xsd";
        String xmlPath = "/Users/DruidKuma/Dropbox/EPAM Trainings/Works/Tasks/Task 3/src/ua/epam/beer/beer.xml";
        
        try {
            SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}

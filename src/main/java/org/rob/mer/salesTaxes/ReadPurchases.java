package org.rob.mer.salesTaxes;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;

public class ReadPurchases {
	
	/**
	 * Note: DOM Parser is slow and consumes a lot of memory when it loads an XML document which contains a lot of data. 
	 *       It is recommended to consider SAX parser as solution (but this is not the case).

	 * @param fileName
	 * @return the DOM structure of the xml file
	 */
	public final Document readXmlFile(final String fileName) {
	 Document doc = null;
	 try {
		    File fXmlFile = new File(fileName);
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    doc = dBuilder.parse(fXmlFile);
		            
		    doc.getDocumentElement().normalize();

		    //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
	 	 } catch (Exception e) {
	 			e.printStackTrace();
		 }
	 
	 	return doc;
	 }
}

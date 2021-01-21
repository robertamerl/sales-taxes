package org.rob.mer.salesTaxes;

import org.w3c.dom.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final ReadPurchases purchases = new ReadPurchases();
        Document doc = purchases.readXmlFile("resource/goods.xml");
        
        // process the purchases and create the output 
        final ProcessPurchases process = new ProcessPurchases();
        process.xmlDomReader(doc);
        
        // write the output
        process.printReceiptsList();
    }
}

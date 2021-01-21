package org.rob.mer.salesTaxes;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// ELEMENT NODES
//purchase
// purchase attribute = id

// goods
// goods attribute = name



public class ProcessPurchases {
	
	private ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
	
	public ArrayList<Receipt> getReceiptList() {
		return receiptList;
	}
	
	// traverse the DOM and extract information	
	public void xmlDomReader (final Document doc) {

	        NodeList nList = doc.getElementsByTagName("purchase");

	        for (int i = 0; i < nList.getLength(); i++) {

	            Node nNode = nList.item(i);

	            //System.out.println("\nCurrent Element: " + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	                Element elem = (Element) nNode;

	                String purchaseId = elem.getAttribute("id");

	  	          //filling the receipt for the purchase
	                Receipt receipt = new Receipt();
	                double salesTaxes = 0.0;
	                double totalPrice = 0.0;
	                

	                NodeList goodsNodeList = elem.getElementsByTagName("goods");
	                int size = goodsNodeList.getLength();
	                
	                for (int index = 0; index < goodsNodeList.getLength(); index++) {
	                	
	                	Element element = (Element ) goodsNodeList.item(index);
	                	String goodsName = element.getAttribute("name");
	                
	                	Node node1 = elem.getElementsByTagName("price").item(index);
	                	String goodsPrice = node1.getTextContent();

	                	Node node2 = elem.getElementsByTagName("basic_tax").item(index);
	                	String basicTax = node2.getTextContent();

	                	Node node3 = elem.getElementsByTagName("import_duty").item(index);
	                	String importDuty = node3.getTextContent();
	                	
	                	double salTax = calculateSalesTaxes(goodsPrice, basicTax, importDuty);
	                	
	                	salesTaxes += salTax;
	                	
	                	double updatedGoodsPrice = calculateGoodsPrice(goodsPrice, salTax);
	                	totalPrice += updatedGoodsPrice;
	                	
	                	receipt.addGoodsToShoppingBag(goodsName, updatedGoodsPrice);
	                }
	                
	                receipt.setSalesTaxes(salesTaxes);
                	receipt.setTotalPrice(totalPrice);
	                receiptList.add(receipt);
	            }
	            
	        }
	        	        
	    }
	
	// calculate taxes
	private double calculateSalesTaxes(final String goodsPrice, final String basicTax, final String importDuty) {
		double salesTax;
		
		double dGoodsPrice = Double.parseDouble(goodsPrice);
		double dBasicTax = Double.parseDouble(basicTax);
		double dImportDuty = Double.parseDouble(importDuty);
		
		salesTax = dGoodsPrice*(dBasicTax+dImportDuty)/100;
		
     	return (double)Math.ceil(salesTax * 20.0) / 20.0;
	}
	
	// apply taxes
	private double calculateGoodsPrice(final String goodsPrice, final double salesTaxes) {
		double finalGoodsPrice;
		
		double dGoodsPrice = Double.parseDouble(goodsPrice);
		finalGoodsPrice = dGoodsPrice+salesTaxes;
		return finalGoodsPrice;
	}
	
	// print the receipt list
	public void printReceiptsList() {
		for(int i = 0; i < receiptList.size(); i++) {
			receiptList.get(i).printReceipt(i+1);
		}
	}

}

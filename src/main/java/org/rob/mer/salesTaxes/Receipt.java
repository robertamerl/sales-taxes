package org.rob.mer.salesTaxes;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	
	private ArrayList<Goods> goodsList = new ArrayList<Goods>(); 
	private double salesTaxes;
	private double totalPrice;
	
	public Receipt () {
	}
	
	public void setSalesTaxes(final double tax) {
		salesTaxes = tax;
	}
	
	public double getSalesTaxes() {
		return salesTaxes; 
	}
	
	public void setTotalPrice(final double total) {
		totalPrice = total;
	}
	
	public double getTotalPrice() {
		return totalPrice; 
	}
	
	public void addGoodsToShoppingBag(final String goodsName, final double price) {
		Goods myGoods = new Goods(goodsName, price);
		goodsList.add(myGoods);
	}
	
	public void printReceipt(final int purchaseId) {
		System.out.printf("Output %s: %n", purchaseId);
		for(int i = 0; i < goodsList.size(); i++) {
		    goodsList.get(i).printGoods();
		}
		System.out.printf("Sales Taxes: %.2f%n", salesTaxes);
     	System.out.printf("Total: %.2f%n", totalPrice);
     	System.out.printf("%n");
	}
	
	private class Goods {
		private String goodsName;
		private double goodsPrice;
		
		public Goods(final String name, final double price) {
			goodsName = name;
			goodsPrice = price;
		}
		
		public void setGoodsName(final String name) {
			this.goodsName = name;
		}
		
		public void setGoodsPrice(final double price) {
			this.goodsPrice = price;
		}
		
		public String getGoodsName() {
			return goodsName;
		}
		
		public double getGoodsPrice() {
			return goodsPrice;
		}
		
		private void printGoods() {
			System.out.printf(goodsName + ": %.2f%n", goodsPrice);
		}
	}
}

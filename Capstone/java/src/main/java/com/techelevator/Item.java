package com.techelevator;
public class Item {
	
	String itemName;
	double itemPrice;
	String itemSound;
	String itemType;
	int remainingStock;
	
	public Item(String itemName,double itemPrice,String itemType,int remainingStock) {
			this.itemName		= itemName;
			this.itemPrice		= itemPrice;
			this.itemType		= itemType;
			this.remainingStock	= remainingStock;
			
				if(this.itemType.equals("Candy")) {			// create if statements to apply sounds to each item
				this.itemSound 	= "Munch, Munch, Yum!";
				}
				if(this.itemType.equals("Chip")) {
				this.itemSound	= "Crunch, Crunch, Yum!";
				}
				if(this.itemType.equals("Drink")) {
				this.itemSound 	= "Glug, Glug, Yum!";
				}
				if(this.itemType.equals("Gum")) {
				this.itemSound 	= "Chew, Chew, Yum!";
				}
	}
	
	public void setRemainingStock(int remainingStock) {		// @param remainingStock the remainingStock to set
				this.remainingStock = remainingStock;
				}
	public String getItemName() {							// @return the itemName
				return itemName;
				}
	public double getItemPrice(){							// @return the itemPrice
				return itemPrice;
				}
	public String getItemSound(){							// @return the itemSound
				return itemSound;
				}
	public String getItemType() {							// @return the itemType
				return itemType;
				}
	public int getRemainingStock() {						// @return the remainingStock
	 			return remainingStock;
	 			}
}
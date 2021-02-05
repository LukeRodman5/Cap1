package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Inventory {

	private TreeMap<String, Item> inventoryList = new TreeMap<String, Item>();

	public Inventory() throws FileNotFoundException {
		
		stocksVendingMachineAtStartup();
	}

	private void stocksVendingMachineAtStartup() throws FileNotFoundException {


			final int initalStock = 5;
			// load the file into the inventory list map
		File fileToGrabData = new File("vendingmachine.csv");
		try(Scanner fileData = new Scanner(fileToGrabData)){
		// Loop through the file one line at a time while there are lines in the file
		while(fileData.hasNextLine()) {
			// Read a line from the file and store it in theLine
			String theLine = fileData.nextLine();
			// Break the line up into separate values based on the | separating the value
			String[] theValues = theLine.split("\\|");
			// Take the values out of the Array & assign them to individual variables
			String slotID = theValues[0];
			String itemName = theValues[1];
			double itemPrice = Double.parseDouble(theValues[2]);
			String itemType = theValues[3];
			// put the variables into an Item object
			Item anItem = new Item(itemName, itemPrice, itemType, initalStock);
			
			// adding item to map w/ slotID
			inventoryList.put(slotID, anItem);
		}
	}
	}
	public void displayInventory() {
		// loop through the inventory displaying an item
		// get the set of keys for the Map
		Set<String> theKeys=inventoryList.keySet();
		// we have to use the keys to loop through the Map
		for(String aKey : theKeys) {
			Item anItem = inventoryList.get(aKey); // get the item for the current key
			System.out.println(aKey + "|" + anItem.getItemName() + "|" + anItem.getItemPrice() + "|" + anItem.getItemType() + " Number Left "+ anItem.getRemainingStock());
		}
	}

//	public void subtractFromInventory(String slotLocation) {
//		inventoryList.put(slotLocation, inventoryList.get(slotLocation) - 1);
//	}
//
//	public int returnCurrentInventory(String slotLocation) {
//		return inventoryList.get(slotLocation);
//	}
//
//	public Map<String, Integer> returnInventoryMap() {
//		return inventoryList;
//	}
} 
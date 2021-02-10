package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {

    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String SALES_REPORT				   = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT, SALES_REPORT
													    };
	private static final String PURCHASE_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_OPTIONS = {	PURCHASE_OPTION_FEED_MONEY, 
														PURCHASE_OPTION_SELECT_PRODUCT,
														PURCHASE_OPTION_FINISH_TRANSACTION
														};
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	private TreeMap<String, Item> inventoryList = new TreeMap<String, Item>();

	private double balance;
	
	public double totalSales;

	public double getTotalSales() {							// Getters & Setters
		return totalSales;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	private int initialStock;

	public void setInitialStock(int initialStock) {
		this.initialStock = initialStock;
	}

	public VendingMachineCLI(Menu menu) throws FileNotFoundException {  // Constructor - user will pas a menu for this class to use

		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu

	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	 * @throws IOException 
	*
	***************************************************************************************************************************/

	public void run() throws IOException {			// starts new log each run
		 File lastLog = new File("./Log.txt");
		 if (lastLog.exists()){
		     lastLog.delete();
		 }

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
		setInitialStock(5);
		// put the variables into an Item object
		Item anItem = new Item(itemName, itemPrice, itemType, initialStock);
		
		// adding item to map w/ slotID
		inventoryList.put(slotID, anItem);
		
		setTotalSales(0.00);
		
		
	}
	nowRun();

	}
	}
	public void nowRun() throws IOException {

		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
				case SALES_REPORT:
					salesReport();
					break;
					
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() {
		// loop through the inventory displaying an item
		// get the set of keys for the Map
		//Set<String> theKeys=inventoryList.keySet();
		// we have to use the keys to loop through the Map
		//for(String aKey : theKeys) {
			//Item anItem = inventoryList.get(aKey); // get the item for the current key
	
		
		
		for (Map.Entry<String,Item>loop:inventoryList.entrySet()) {
		System.out.println(loop.getKey() + "|" + loop.getValue().getItemName() + "|" + loop.getValue().getItemPrice() + "|" + loop.getValue().getItemType() + " Number Left "+ loop.getValue().getRemainingStock());
		}
	}
	
	public void purchaseItems() throws IOException {	 // static attribute used as method is not associated with specific object instance
									// Code to purchase items from Vending Machine
		String choice = (String)vendingMenu.getChoiceFromOptions(PURCHASE_OPTIONS);  // Display menu and get choice
		
		switch(choice) {                  // Process based on user menu choice
		
			case PURCHASE_OPTION_FEED_MONEY:
				feedMoney();           // invoke method to display items in Vending Machine
				break;                    // Exit switch statement
		
			case PURCHASE_OPTION_SELECT_PRODUCT:
				selectProduct();          // invoke method to purchase items from Vending Machine
				break;                    // Exit switch statement
		
			case PURCHASE_OPTION_FINISH_TRANSACTION:
				finishTransaction();    // Invoke method to perform end of method processing
											// Set variable to end loop
				break;                    // Exit switch statement
		}	
	}
		
	
	
	private void feedMoney() throws IOException {
		@SuppressWarnings("resource")
		Scanner moneyReader = new Scanner(System.in);
		
		System.out.println("Please Insert Bills");
		try {
				String moneyInserted = moneyReader.nextLine();
				
				if (moneyInserted.equals("1") || moneyInserted.equals("2") || Integer.parseInt(moneyInserted) == 5 || Integer.parseInt(moneyInserted) == 10) {
				
					setBalance(getBalance() + Double.parseDouble(moneyInserted));		
					System.out.println("Current Balance: " + String.format("%.2f",getBalance()));
					
					FileWriter itemWriter = new FileWriter("./Log.txt", true);
					PrintWriter printItemWriter = new PrintWriter(itemWriter);
					
					printItemWriter.println(dateTime() + " FEED MONEY " + moneyInserted + " " + String.format("%.2f",getBalance()));
					printItemWriter.close();
				} else {
					System.out.println("Please Insert Valid Currency");
				}
		}
				catch (NumberFormatException ex){  
		            System.err.println("Please Insert Valid Currency");	
		        }

	}
	public static String dateTime() {
		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		String dateString2 = dateFormat2.format(new Date()).toString();
			return dateString2;
		}
	
	private void selectProduct() throws IOException {
		displayItems();
		@SuppressWarnings("resource")
		Scanner itemSelect = new Scanner(System.in);
		
		System.out.println("Enter Item Code: ");
			String codeEntered = itemSelect.nextLine();
			if (inventoryList.containsKey(codeEntered)) {
				inventoryList.get(codeEntered).getRemainingStock();
				if (balance >= inventoryList.get(codeEntered).getItemPrice()) {
					if (inventoryList.get(codeEntered).getRemainingStock() >= 1) {
						inventoryList.get(codeEntered).setRemainingStock(inventoryList.get(codeEntered).getRemainingStock() - 1);
						setBalance(getBalance() - inventoryList.get(codeEntered).getItemPrice());
						System.out.println("You purchased: " + inventoryList.get(codeEntered).getItemName());
						System.out.println("Your remaining balance is: $" + getBalance());
						
						FileWriter itemWriter = new FileWriter("./Log.txt", true);
						PrintWriter printItemWriter = new PrintWriter(itemWriter);

						printItemWriter.println(dateTime() + " " + inventoryList.get(codeEntered).getItemName() + " " + codeEntered + balance + " " + getBalance());
						printItemWriter.close();
						
						setTotalSales(getTotalSales() + inventoryList.get(codeEntered).getItemPrice());

					} else {
						System.out.println("Item Out Of Stock");
					}
			} else {
				System.out.println("Not Enough Money");
			}
		} else {
			System.out.println("Incorrect Code Entered");
		}
	}
		
	

	public void finishTransaction() { 
		
			int tracker = ((int)(getBalance() * 100));
			
			int totalQuartersToReturn = 0;
			int totalDimesToReturn = 0;
			int totalNickelsToReturn = 0;

			int quarter = 25;
			int dime = 10;
			int nickel = 5;

			while (tracker > 0) {
				if (tracker >= quarter) {
					totalQuartersToReturn++;
					tracker -= quarter;
				} else if (tracker >= dime) {
					totalDimesToReturn++;
					tracker -= dime;
				} else if (tracker >= nickel) {
					totalNickelsToReturn++;
					tracker -= nickel;	
				}
			}
			this.balance = 0;
			
			System.out.println("Your change is " + totalQuartersToReturn + " quarters, " + totalDimesToReturn +
					" dimes, " + "and " + totalNickelsToReturn + " nickles.");
		}
		
	public void endMethodProcessing() {
		
	}
	
	public void salesReport() throws IOException {
		String salesReportList = new SimpleDateFormat("'Sales Report 'yyyy-MM-dd-HH.mm.ss'.txt'").format(new Date());

		PrintWriter printSalesReportList = new PrintWriter(salesReportList);
		
		for (Map.Entry<String,Item>loop:inventoryList.entrySet()) {
		printSalesReportList.println(loop.getValue().getItemName() + "|"  + loop.getValue().getRemainingStock());
		}				
		printSalesReportList.println("Total Sales: $" + getTotalSales());
		printSalesReportList.close();

	}
		
		
		
	}
		// static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	

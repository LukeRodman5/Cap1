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
import java.util.Set;
import java.util.TreeMap;
import com.techelevator.view.Menu;

public class VendingMachineCLI { 
	private static final String   MAIN_MENU_OPTION_DISPLAY_ITEMS 		= "Display Vending Machine Items";
	private static final String   MAIN_MENU_OPTION_PURCHASE     		= "Purchase";
	private static final String   MAIN_MENU_OPTION_EXIT 		 		= "Exit";
	private static final String   SALES_REPORT		   			 		= "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS 			 		= {
								  MAIN_MENU_OPTION_DISPLAY_ITEMS,MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, SALES_REPORT
								  };
	
	private static final String   PURCHASE_OPTION_FEED_MONEY 	 		= "Feed Money";
	private static final String   PURCHASE_OPTION_SELECT_PRODUCT 		= "Select Product";
	private static final String   PURCHASE_OPTION_FINISH_TRANSACTION 	= "Finish Transaction";
	private static final String[] PURCHASE_OPTIONS 				 		= { 
								  PURCHASE_OPTION_FEED_MONEY, PURCHASE_OPTION_SELECT_PRODUCT,PURCHASE_OPTION_FINISH_TRANSACTION
								  };
	
	private static final String	  FEED_MENU_OPTION_ONE 					= "$1.00";
	private static final String   FEED_MENU_OPTION_TWO 					= "$2.00";
	private static final String   FEED_MENU_OPTION_FIVE 				= "$5.00";
	private static final String   FEED_MENU_OPTION_TEN 					= "$10.00";
	private static final String   FEED_MENU_OPTION_EXIT 				= "Go Back";
	private static final String[] FEED_MENU_OPTIONS 					= {
								  FEED_MENU_OPTION_ONE,FEED_MENU_OPTION_TWO,FEED_MENU_OPTION_FIVE,FEED_MENU_OPTION_TEN,FEED_MENU_OPTION_EXIT
								  };
	
	private Menu menu;
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	
	/*************************************************************************************************************************/
	private Menu vendingMenu;              								// Menu object to be used by an instance of this class
	private TreeMap<String, Item> inventoryList = new TreeMap<String, Item>();
	private double balance;
	public double totalSales;
	public double getTotalSales() {
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

	public void run() throws IOException {								// @throws IOException
		File lastLog = new File("./Log.txt");							// create last log file
		if (lastLog.exists()){
			lastLog.delete();
			}
		
		File fileToGrabData = new File("vendingmachine.csv");			// linking to the file with data
		try(Scanner fileData = new Scanner(fileToGrabData)){			// Loop through the file one line at a time while there are lines in the file
			while(fileData.hasNextLine()) {								// Read a line from the file and store it in theLine
				String theLine = fileData.nextLine();					// Break line up into separate values based on the | separating the value
				String[] theValues = theLine.split("\\|");				// Take the values out of the Array & assign them to individual variables
				String slotID = theValues[0];							// define the values for each slotID
				String itemName = theValues[1];							// define item names for each value
				double itemPrice = Double.parseDouble(theValues[2]);	// define item price for each value
				String itemType = theValues[3];							// define item type for each value
				setInitialStock(5);										// define initial stock of all of the items & put the variables into an Item object
				Item anItem = new Item(itemName, itemPrice, itemType, initialStock);
				inventoryList.put(slotID, anItem);						// adding item to map w/ slotID
				setTotalSales(0.00);									// set total sales amount 
				}
		
			nowRun();
	}
		}
	
	public void nowRun() throws IOException {							// now run throws IOException
		boolean shouldProcess = true;            						// Loop control variable
		
		while(shouldProcess) {											// while loop until user indicates they want to exit
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS); // Display menu and get choice
			
			switch(choice) {											// Process based on user menu choice
			case MAIN_MENU_OPTION_DISPLAY_ITEMS:displayItems();			// invoke method to display items in Vending Machine
			break;														// Exit switch statement
			
			case MAIN_MENU_OPTION_PURCHASE:
				purchaseItems();										// invoke method to purchase items from Vending Machine
				break;                    	 							// Exit switch statement
				
			case MAIN_MENU_OPTION_EXIT:
				endMethodProcessing();    								// Invoke method to perform end of method processing
				shouldProcess = false;    								// Set variable to end while loop
				break;                 	   								// Exit switch statement
				
			case SALES_REPORT:
				salesReport();											// invoke method to sales report from Vending Machine
				break;													// Exit switch statement
				}
			}
		return;						                            	    // End method & return to caller
	}
	
	public void displayItems() {
		
		
		Item anItem = inventoryList.get(itemName);
		for (Map.Entry<String,Item>loop:inventoryList.entrySet()) {
			System.out.println(loop.getKey() + "|" + loop.getValue().getItemName() + "|" + loop.getValue().getItemPrice() + "|" + loop.getValue().getItemType() + " Number Left "+ loop.getValue().getRemainingStock());
			}
		}
	public void purchaseItems() throws IOException {
				 // static attribute used as method is not associated with specific object instance
				// Code to purchase items from Vending Machine
	String choice = (String)vendingMenu.getChoiceFromOptions(PURCHASE_OPTIONS);  
				// Display menu and get choice
	switch(choice) {                  // Process based on user menu choice			
		case PURCHASE_OPTION_FEED_MONEY:
		feedMoney();      // invoke method to display items in Vending Machine
			break;           // Exit switch statement
		case PURCHASE_OPTION_SELECT_PRODUCT:
		selectProduct();          // invoke method to purchase items from Vending Machine
			break;             // Exit switch statement
		case PURCHASE_OPTION_FINISH_TRANSACTION:
			finishTransaction();    // Invoke method to perform end of method processing
					       // Set variable to end loop
			break;                                  // Exit switch statement
			}	
		}	
		private void feedMoney() throws IOException {
			Scanner moneyReader = new Scanner(System.in);
			System.out.println("Please Insert Bills");
			
		try {
		String moneyInserted = moneyReader.nextLine();		
		if (moneyInserted.equals("1")
									|| moneyInserted.equals("2")
									|| Integer.parseInt(moneyInserted) == 5
									|| Integer.parseInt(moneyInserted) == 10) {
			 setBalance(getBalance() + Double.parseDouble(moneyInserted));
		   
		System.out.println("Current Balance: " + String.format("%.2f",getBalance()));				
			FileWriter itemWriter = new FileWriter("./Log.txt", true);
			PrintWriter printItemWriter = new PrintWriter(itemWriter);			
			printItemWriter.println(dateTime()
									+ " FEED MONEY " + moneyInserted
									+ " " + String.format("%.2f",getBalance()));
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
			Scanner itemSelect = new Scanner(System.in);
			System.out.println("Enter Item Code: ");
			String codeEntered = itemSelect.nextLine();
			
			if (inventoryList.containsKey(codeEntered)) {
				inventoryList.get(codeEntered).getRemainingStock();
				
				if (balance >= inventoryList.get(codeEntered).getItemPrice()) {
					
					if (inventoryList.get(codeEntered).getRemainingStock() >= 1) {
						inventoryList.get(codeEntered).setRemainingStock(inventoryList.get(codeEntered).getRemainingStock()-1);
													   setBalance(getBalance()-inventoryList.get(codeEntered).getItemPrice());
						
						System.out.println("You purchased: " + inventoryList.get(codeEntered).getItemName());
						System.out.println("Your remaining balance is: $" + getBalance());
						
						FileWriter itemWriter = new FileWriter("./Log.txt", true);
						PrintWriter printItemWriter = new PrintWriter(itemWriter);
						
						printItemWriter.println(dateTime() + " " + inventoryList.get(codeEntered).getItemName()
														   + " " + codeEntered + balance + " " + getBalance());
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
				int tracker 						= ((int)(getBalance() * 100));
				int totalQuartersToReturn 			= 0;
				int totalDimesToReturn 				= 0;
				int totalNickelsToReturn 			= 0;
				int quarter 						= 25;
				int dime 							= 10;
				int nickel 							= 5;
				
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
				
				System.out.println("Your change is " + totalQuartersToReturn + " quarters, " + totalDimesToReturn + " dimes, " + "and " + totalNickelsToReturn + " nickles.");
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
	
			public static void main(String[] args) throws IOException {
					Menu menu = new Menu(System.in, System.out);
					VendingMachineCLI vendingMenu  = new VendingMachineCLI (menu);
					vendingMenu.run();
				
			}
}
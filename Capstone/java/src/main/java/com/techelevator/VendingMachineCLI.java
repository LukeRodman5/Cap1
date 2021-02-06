package com.techelevator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
													    };
	private static final String PURCHASE_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_OPTIONS = {	PURCHASE_OPTION_FEED_MONEY,
														PURCHASE_OPTION_SELECT_PRODUCT,
														PURCHASE_OPTION_FINISH_TRANSACTION
														};
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	private Inventory stuffWeSell;
	private double balance;
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public VendingMachineCLI(Menu menu) throws FileNotFoundException {  // Constructor - user will pas a menu for this class to use
		this.stuffWeSell = new Inventory();
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
	public void run() throws IOException {
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
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() {      // static attribute used as method is not associated with specific object instance
		stuffWeSell.displayInventory();// Code to display items in Vending Machine
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
				endMethodProcessing();    // Invoke method to perform end of method processing
											// Set variable to end loop
				break;                    // Exit switch statement
		}	
	}
		
	
	
	private void feedMoney() throws IOException {
		Scanner moneyReader = new Scanner(System.in);
		
		System.out.println("Please Insert Bills");
		try {
				String moneyInserted = moneyReader.nextLine();
				
				if (moneyInserted.equals("1") || moneyInserted.equals("2") || Integer.parseInt(moneyInserted) == 5 || Integer.parseInt(moneyInserted) == 10) {
				
					setBalance(getBalance() + Double.parseDouble(moneyInserted));		
					System.out.println("Current Balance: " + String.format("%.2f",getBalance()));
				} else {
					System.out.println("Please Insert Valid Currency");
				}
		}
				catch (NumberFormatException ex){
		            System.err.println("Please Insert Valid Currency");	
		        }
	}
	
	private void selectProduct() {
		stuffWeSell.displayInventory();
		Scanner itemSelect = new Scanner(System.in);
		
		System.out.println("Enter Item Code: ");
			String codeEntered = itemSelect.nextLine();
				if (inventoryList.containsKey) {
					
					
				}
		
	}
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}
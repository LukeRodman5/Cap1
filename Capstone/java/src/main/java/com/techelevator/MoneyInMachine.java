package com.techelevator;
public class MoneyInMachine {
	
	public int balance;
	
	public MoneyInMachine() {								// amount of money in the machine
		balance = 0;
		}
	
	public void addMoney(int amountToDeposit) {				// amount of money to deposit
		balance = balance + amountToDeposit;
		}
	
	public void withdrawMoney(int amountToWithdraw) {		// amount of money to withdraw
		balance = balance - (amountToWithdraw);
		}
	
	public int getBalanceInPennies() {
		return balance;
		}
	
	public String getBalanceAsString() {
		int currentBalanceAsInt 	   = balance;
		double currentBalanceAsDouble  = (currentBalanceAsInt/100);
		String formattedDoubleAsString = "$" + String.format("%.2f", currentBalanceAsDouble);
		return formattedDoubleAsString;
		}
	
	public String returnChangeAsCoins(int balance) {		// returning change only as coins bc no paper bills get returned
		int tracker 				   = balance;
		int totalQuartersToReturn      = 0;
		int totalDimesToReturn         = 0;
		int totalNickelsToReturn 	   = 0;
		int quarter 				   = 25;
		int dime   					   = 10;
		int nickel 					   = 5;
		
		while  (tracker > 0) {								// while loop to return: quarters, dimes, & nickels
			
			if (tracker >= quarter) {						// total quarters returned
				totalQuartersToReturn++;
				tracker -= quarter;
				
			} else if (tracker >= dime) {					// total dimes returned
				totalDimesToReturn++;
				tracker -= dime;
				
				} else if (tracker >= nickel) {				// total nickels returned
					totalNickelsToReturn++;
					tracker -= nickel;
					}
			}
		
		this.balance = 0;									// balance is zero'd out as change is returned to user 
		
		String returnString = ("Your change is "			// displays message to user as change is returned
							+ totalQuartersToReturn
							+ " quarters, "
							+ totalDimesToReturn
							+ " dimes, "
							+ "and "
							+ totalNickelsToReturn
							+ " nickles.");
		return returnString;
		}
}
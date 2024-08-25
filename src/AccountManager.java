/*
 * 
 * Module 2 CTA Option 1: Creating a GUI Bank Balance Application
 * Brian Gunther
 * CSC372: Programming II
 * Colorado State University Global
 * Dr. Vanessa Cooper
 * August 25, 2024
 * 
 */

import javax.swing.JFrame;

public class AccountManager {

	public static void main(String[] args) {
		BankAccount myBankAccount = null;
		myBankAccount = new BankAccount();
		
		// Set up test information
		myBankAccount.setFirstName("Test");
		myBankAccount.setLastName("Account");
		myBankAccount.setAccountID(999);
		
		// Make an initial deposit
		myBankAccount.deposit(1000.0);

		// Instantiate the user interface
		AccountManagerGUI accountInterface = new AccountManagerGUI(myBankAccount);
	}

}

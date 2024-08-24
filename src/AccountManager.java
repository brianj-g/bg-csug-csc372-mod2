/*
 * Module 2 CTA Option 1: Creating a GUI Bank Balance Application
 * Brian Gunther
 * CSC372: Programming II
 * Colorado State University Global
 * Dr. Vanessa Cooper
 * August 18, 2024
 * 
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * AccountManager class provides GUI for the bank account application
 */
public class AccountManager extends JFrame implements ActionListener {
	private JPanel accountDetailPanel;					// Contains account details components
	private JPanel textEntryPanel;						// Contains text entry components
	private JPanel actionPanel;							// Contains application control components
	private JLabel accountIDLabel;						// Label for the account ID
	private JLabel accountNameLabel;					// Label for account holder's name
	private JLabel accountBalanceLabel;					// Label for account balance field
	private JLabel withdrawalLabel;						// Label for withdrawal field
	private JLabel depositLabel;						// Label for deposit field
	private JFormattedTextField withdrawalField;		// Holds withdrawal input
	private JFormattedTextField depositField;			// Holds deposit input
	private JTextField accountBalanceField;				// Displays account balance
	private JTextField accountIDField;					// Displays account ID
	private JTextField accountNameField;				// Displays account holder's name
	private JButton depositButton;						// Creates event for deposit
	private JButton withdrawalButton;					// Creates event for withdrawal
	private JButton exitButton;							// Creates event for program exit
	
	/**
	 * Default constructor for AccountManager class
	 */
	public AccountManager(BankAccount account) {
		GridBagConstraints abpConst = null;				// GBL constraints for accountDetailPanel orientation
		GridBagConstraints teConst = null;				// GBL constraints for textEntryPanel orientation
		GridBagConstraints aConst = null;				// GBL constraints for actionPanel orientation
		
		this.setTitle("Bank Account Management");
		
		this.accountIDLabel.setText("Account ID: ");
		this.accountIDField.setText(Integer.toString(account.getAccountID()));
		this.accountNameField.setText("Account Holder: ");
		this.accountNameField.setText(account.getFirstName() + " " + account.getLastName());
		this.accountBalanceField.setText("Account Balance: ");
		this.accountBalanceField.setText(Double.toString(account.getBalance()));
		this.withdrawalLabel.setText("Withdrawal amount: ");
		this.depositLabel.setText("Deposit amount: ");
		
	}

}

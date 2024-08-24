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
import java.text.NumberFormat;

/**
 * AccountManager class provides GUI for the bank account application
 */
public class AccountManagerGUI extends JFrame implements ActionListener {
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
	public AccountManagerGUI(BankAccount account) {
		GridBagConstraints abpConst = null;				// GBL constraints for accountDetailPanel orientation
		GridBagConstraints teConst = null;				// GBL constraints for textEntryPanel orientation
		GridBagConstraints aConst = null;				// GBL constraints for actionPanel orientation
		
		setTitle("Bank Account Management");
		
		accountIDLabel = new JLabel("Account ID: ");
		accountIDField.setText(Integer.toString(account.getAccountID()));
		
		accountNameLabel = new JLabel("Account Holder: ");
		accountNameField.setText(account.getFirstName() + " " + account.getLastName());
		
		accountBalanceLabel = new JLabel("Account Balance: ");
		accountBalanceField.setText(Double.toString(account.getBalance()));
		
		withdrawalLabel = new JLabel("Withdrawal amount: ");
		depositLabel = new JLabel("Deposit amount: ");
		
		depositButton = new JButton("Submit Deposit");
		depositButton.addActionListener(this);
		withdrawalButton = new JButton("Submit Withdrawal");
		withdrawalButton.addActionListener(this);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		
		withdrawalField = new JFormattedTextField(NumberFormat.getNumberInstance());
		withdrawalField.setEditable(true);
		depositField = new JFormattedTextField(NumberFormat.getNumberInstance());
		depositField.setEditable(true);
		
		
		
	}
	
	/**
	 * Actions for button clicks
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO: Add actions
	}

}

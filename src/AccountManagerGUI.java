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
		GridBagConstraints layoutConstraints = null;		// Re-usable layout constraints variable			

		this.setTitle("Bank Account Management");
		
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
		withdrawalField.setColumns(15);
		depositField = new JFormattedTextField(NumberFormat.getNumberInstance());
		depositField.setEditable(true);
		depositField.setColumns(15);
		
		accountDetailPanel = new JPanel(new GridBagLayout());
		textEntryPanel = new JPanel(new GridBagLayout());
		actionPanel = new JPanel(new GridBagLayout());
		
		// New layout for the window
		this.setLayout(new GridBagLayout());
		this.add(accountDetailPanel, setConstraints(0,0,10,10,10,10));
		this.add(textEntryPanel, setConstraints(0,1,10,10,10,10));
		this.add(actionPanel, setConstraints(0,2,10,10,10,10));
		
		// Layout for the accountDetailPanel
		accountDetailPanel.add(accountIDLabel, setConstraints(0,0,10,10,10,1));
		accountDetailPanel.add(accountIDField, setConstraints(1,0,10,1,10,10));
		accountDetailPanel.add(accountNameLabel, setConstraints(0,1,10,10,10,1));
		accountDetailPanel.add(accountNameField, setConstraints(1,1,10,1,10,10));
		accountDetailPanel.add(accountBalanceLabel, setConstraints(0,2,10,10,10,1));
		accountDetailPanel.add(accountBalanceField, setConstraints(1,2,10,1,10,10));

		
	}
	
	/**
	 * Actions for button clicks
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO: Add actions
	}
	
	/**
	 * Utility function to quickly set up a GridBag constraint with basic x, y, and inset values
	 * @param x Sets gridx
	 * @param y Sets gridy
	 * @param t Sets top inset
	 * @param l Sets left inset
	 * @param b Sets bottom inset
	 * @param r Sets right inset
	 * @return GridBagConstraints 
	 */
	private GridBagConstraints setConstraints(int x, int y, int t, int l, int b, int r) {
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		layoutConstraints.gridx = x;
		layoutConstraints.gridy = y;
		layoutConstraints.insets = new Insets(t,l,b,r);
		
		return layoutConstraints;
	}

}

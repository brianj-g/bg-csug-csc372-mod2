/*
 * Module 2 CTA Option 1: Creating a GUI Bank Balance Application
 * Brian Gunther
 * CSC372: Programming II
 * Colorado State University Global
 * Dr. Vanessa Cooper
 * August 25, 2024
 * 
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;

/**
 * AccountManager class provides GUI for the bank account application
 */
public class AccountManagerGUI extends JFrame implements ActionListener {
	private JPanel accountDetailPanel;					// Contains account details components
	private JPanel textEntryPanel;						// Contains text entry components
	private JPanel actionPanel;							// Contains application control components
	private JLabel accountIDLabel;						// Label for the account ID
	private JLabel accountIDDisplayLabel;				// Label to display the account ID
	private JLabel accountNameLabel;					// Label for account holder's name
	private JLabel accountNameDisplayLabel;				// Label to display the account holder's name
	private JLabel accountBalanceLabel;					// Label for account balance field
	private JLabel accountBalanceDisplayLabel;			// Label to display the balance
	private JLabel withdrawalLabel;						// Label for withdrawal field
	private JLabel depositLabel;						// Label for deposit field
	private JLabel notificationLabel;					// Label for informational messages
	private JFormattedTextField withdrawalField;		// Holds withdrawal input
	private JFormattedTextField depositField;			// Holds deposit input
	private JTextField accountBalanceField;				// Updates account balance
	private JTextField accountNameField;				// Updates account holder's name
	private JButton manualBalanceButton;				// Creates event to manually update balance
	private JButton manualNameButton;					// Creates event to manually update name
	private JButton depositButton;						// Creates event for deposit
	private JButton withdrawalButton;					// Creates event for withdrawal
	private JButton exitButton;							// Creates event for program exit
	private BankAccount account;						// Creates a bank account object to reference the user's account
	private Timer notificationTimer;					// Creates a timer for blanking the notification label
	
	// Use output streams to build a formatted string for the exit dialog
	private StringWriter exitStringStream = new StringWriter();
	private PrintWriter exitStringOut = new PrintWriter(exitStringStream);
	
	/**
	 * Default constructor for AccountManager class
	 */
	public AccountManagerGUI(BankAccount account) {
		this.account = account;

		this.setTitle("Bank Account Management");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		accountIDLabel = new JLabel("Account ID: ");
		accountIDDisplayLabel = new JLabel(Integer.toString(account.getAccountID()));
		
		accountNameLabel = new JLabel("Account Holder: ");
		accountNameDisplayLabel = new JLabel(account.getFirstName() + " " + account.getLastName());
		accountNameField = new JTextField(15);
		accountNameField.setEditable(true);
		
		accountBalanceLabel = new JLabel("Account Balance: ");
		accountBalanceDisplayLabel = new JLabel(Double.toString(account.getBalance()))
		accountBalanceField = new JTextField(15);
		accountBalanceField.setEditable(true);
		
		withdrawalLabel = new JLabel("Withdrawal amount: ");
		depositLabel = new JLabel("Deposit amount: ");
		
		depositButton = new JButton("Submit Deposit");
		depositButton.addActionListener(this);
		withdrawalButton = new JButton("Submit Withdrawal");
		withdrawalButton.addActionListener(this);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		manualBalanceButton = new JButton("Set New Balance");
		manualNameButton = new JButton("Set New Name");
		
		withdrawalField = new JFormattedTextField(NumberFormat.getNumberInstance());
		withdrawalField.setEditable(true);
		withdrawalField.setColumns(15);
		depositField = new JFormattedTextField(NumberFormat.getNumberInstance());
		depositField.setEditable(true);
		depositField.setColumns(15);
		
		notificationLabel = new JLabel(" ");
		/**
		 * Timer object is initialized to clear the notification label
		 */
	    notificationTimer = new Timer(3000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            notificationLabel.setText(" ");  // Clear the notification label
	        }
	    });
	    notificationTimer.setRepeats(false);
		
		// Layout for the accountDetailPanel
		accountDetailPanel = new JPanel(new GridBagLayout());
		accountDetailPanel.add(accountIDLabel, setConstraints(0,0,10,10,10,1));
		accountDetailPanel.add(accountIDDisplayLabel, setConstraints(1,0,10,1,10,10));
		accountDetailPanel.add(accountNameLabel, setConstraints(0,1,10,10,10,1));
		accountDetailPanel.add(accountNameDisplayLabel, setConstraints(1,1,10,1,10,10));
		accountDetailPanel.add(accountBalanceLabel, setConstraints(0,2,10,10,10,1));
		accountDetailPanel.add(accountBalanceDisplayLabel, setConstraints(1,2,10,1,10,10));
		
		// Layout for the textInputPanel
		textEntryPanel = new JPanel(new GridBagLayout());
		textEntryPanel.add(depositLabel, setConstraints(0,0,10,10,10,1));
		textEntryPanel.add(depositField, setConstraints(1,0,10,1,10,10));
		textEntryPanel.add(withdrawalLabel, setConstraints(0,1,10,10,10,1));
		textEntryPanel.add(withdrawalField, setConstraints(1,1,10,1,10,10));

		// Layout for the actionPanel
		actionPanel = new JPanel(new GridBagLayout());
		actionPanel.add(notificationLabel, setConstraints(0,0,10,10,50,10));
		actionPanel.add(depositButton, setConstraints(0,1,10,10,10,10));
		actionPanel.add(withdrawalButton, setConstraints(1,1,10,10,10,10));
		actionPanel.add(exitButton, setConstraints(2,1,10,10,10,10));


		// Layout for the main JFrame
		this.setLayout(new GridBagLayout());
		this.add(accountDetailPanel, setConstraints(0,0,10,10,10,10));
		this.add(textEntryPanel, setConstraints(0,1,10,10,10,10));
		this.add(actionPanel, setConstraints(0,2,10,10,10,10));
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * Actions for button clicks
	 */
	@Override
	public void actionPerformed(ActionEvent buttonEvent) {
		if (buttonEvent.getSource() == depositButton) {
			Double amount = ((Number) depositField.getValue()).doubleValue();
			try {
				// Deposit the requested amount
				this.account.deposit(amount);	
				this.notificationLabel.setText("Deposited $" + amount);
			    this.notificationTimer.start();
				this.depositField.setValue(null);
			} catch(Exception e) {
				JOptionPane optionPane = new JOptionPane(e.getMessage(), JOptionPane.WARNING_MESSAGE);
				JDialog dialog = optionPane.createDialog("Warning");
				dialog.setVisible(true);
			}		
			// Updated the account balance display field
			this.accountBalanceDisplayLabel.setText(Double.toString(this.account.getBalance()));
		} else if (buttonEvent.getSource() == withdrawalButton) {
			Double amount = ((Number) withdrawalField.getValue()).doubleValue();
			try {
				// Withdraw the requested amount
				this.account.withdrawal(amount);
				this.notificationLabel.setText("Withdrew $" + amount);
			    this.notificationTimer.start();
				this.withdrawalField.setValue(null);
			} catch(Exception e) {
				JOptionPane optionPane = new JOptionPane(e.getMessage(), JOptionPane.WARNING_MESSAGE);
			    JDialog dialog = optionPane.createDialog("Warning");
			    dialog.setVisible(true);
			}
			// Update the account balance display field
			this.accountBalanceDisplayLabel.setText(Double.toString(this.account.getBalance()));
		} else if (buttonEvent.getSource() == exitButton) {
			// Build the string to display on the exit confirmation
			exitStringOut.printf("Final Balance: %.2f\n\n", this.account.getBalance());
			exitStringOut.printf("Are you sure you'd like to exit?");
			// Use a confirmation dialog to display the current balance and confirm exit
			int toExit = JOptionPane.showConfirmDialog(this, exitStringStream.toString(), "Exit Confirmation", JOptionPane.YES_NO_OPTION);
			exitStringOut.close();
			if (toExit == JOptionPane.YES_OPTION) {
				this.dispose();
				System.exit(0);
			}
		} else if (buttonEvent.getSource() == manualBalanceButton) {
			// TODO: event for manual balance
		} else if (buttonEvent.getSource() == manualNameButton) {
			// TODO: event for name update
		}
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
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		return layoutConstraints;
	}



}

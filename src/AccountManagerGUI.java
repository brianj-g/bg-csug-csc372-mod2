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

import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

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
	private JPanel overridePanel;						// Contains manual update components
	private JLabel overridePanelLabel;					// Label for the manual overrides panel
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
	private JFormattedTextField accountBalanceField;	// Updates account balance
	private JButton manualBalanceButton;				// Creates event to manually update balance
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
		// Configure object
		this.account = account;
		this.setTitle("Bank Account Management");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a default size for buttons to prevent automatic resizing
		Dimension buttonSize = new Dimension(150, 30);
		
		// Configure administrative override components
		// In a production application, these would be restricted to certain user roles
		overridePanelLabel = new JLabel("Administrative Overrides", SwingConstants.CENTER);
		Font overrideFont = UIManager.getFont("Label.font");
		overridePanelLabel.setFont(new Font(overrideFont.getName(), Font.PLAIN, 14));
		manualBalanceButton = new JButton("Set New Balance");
		manualBalanceButton.addActionListener(this);
		manualBalanceButton.setPreferredSize(buttonSize);
		accountBalanceField = new JFormattedTextField(NumberFormat.getNumberInstance());
		accountBalanceField.setColumns(15);
		accountBalanceField.setEditable(true);
		accountBalanceField.setPreferredSize(new Dimension(150, 25));
		
		// Configure account ID components
		accountIDLabel = new JLabel("Account ID: ");
		accountIDLabel.setFont(new Font(overrideFont.getName(), Font.BOLD, 13));
		accountIDDisplayLabel = new JLabel(Integer.toString(account.getAccountID()));
		
		// Configure account name components
		accountNameLabel = new JLabel("Account Holder: ");
		accountNameLabel.setFont(new Font(overrideFont.getName(), Font.BOLD, 13));
		accountNameDisplayLabel = new JLabel(account.getFirstName() + " " + account.getLastName());
		
		// Configure account balance components
		accountBalanceLabel = new JLabel("Account Balance: ");
		accountBalanceLabel.setFont(new Font(overrideFont.getName(), Font.BOLD, 13));
		accountBalanceDisplayLabel = new JLabel(String.format("$%.2f",  account.getBalance()));
		
		// Configure withdrawal and deposit components
		withdrawalLabel = new JLabel("Withdrawal amount: ");
		withdrawalLabel.setFont(new Font(overrideFont.getName(), Font.BOLD, 13));
		depositLabel = new JLabel("Deposit amount: ");
		depositLabel.setFont(new Font(overrideFont.getName(), Font.BOLD, 13));
		depositButton = new JButton("Submit Deposit");
		depositButton.addActionListener(this);
		depositButton.setPreferredSize(buttonSize);
		withdrawalButton = new JButton("Submit Withdrawal");
		withdrawalButton.addActionListener(this);
		withdrawalButton.setPreferredSize(buttonSize);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		exitButton.setPreferredSize(buttonSize);
		withdrawalField = new JFormattedTextField(NumberFormat.getNumberInstance());
		withdrawalField.setEditable(true);
		withdrawalField.setColumns(15);
		withdrawalField.setPreferredSize(new Dimension(125, 25));
		depositField = new JFormattedTextField(NumberFormat.getNumberInstance());
		depositField.setEditable(true);
		depositField.setColumns(15);
		depositField.setPreferredSize(new Dimension(125, 25));
		
		// Configure notification label
		notificationLabel = new JLabel(" ");
		notificationLabel.setPreferredSize(new Dimension(300, 25));
		
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
		accountDetailPanel.add(accountIDLabel, setConstraints(0,0,10,10,10,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.0));
		accountDetailPanel.add(accountIDDisplayLabel, setConstraints(1,0,10,1,10,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 1.0));
		accountDetailPanel.add(accountNameLabel, setConstraints(0,1,10,10,10,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.0));
		accountDetailPanel.add(accountNameDisplayLabel, setConstraints(1,1,10,1,10,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 1.0));
		accountDetailPanel.add(accountBalanceLabel, setConstraints(0,2,10,10,10,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.0));
		accountDetailPanel.add(accountBalanceDisplayLabel, setConstraints(1,2,10,1,10,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 1.0));
		
		// Layout for the overridePanel
		overridePanel = new JPanel(new GridBagLayout());
		GridBagConstraints labelConst = new GridBagConstraints();
		labelConst.gridx = 0;
		labelConst.gridy = 0;
		labelConst.anchor = GridBagConstraints.CENTER;
		labelConst.insets = new Insets(10,10,10,10);
		overridePanel.add(overridePanelLabel, labelConst);
		overridePanel.add(manualBalanceButton, setConstraints(0,1,10,10,10,1,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, 0.5));
		overridePanel.add(accountBalanceField, setConstraints(1,1,10,1,10,10,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, 0.5));
		Border border = BorderFactory.createLineBorder(java.awt.Color.BLACK, 1);
		overridePanel.setBorder(border);
		
		
		// Layout for the textInputPanel
		textEntryPanel = new JPanel(new GridBagLayout());
		textEntryPanel.add(depositLabel, setConstraints(0,0,10,10,10,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.0));
		textEntryPanel.add(depositField, setConstraints(1,0,10,1,10,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 1.0));
		textEntryPanel.add(withdrawalLabel, setConstraints(0,1,10,10,10,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.0));
		textEntryPanel.add(withdrawalField, setConstraints(1,1,10,1,10,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 1.0));

		// Layout for the actionPanel
		actionPanel = new JPanel(new GridBagLayout());
		actionPanel.add(notificationLabel, setConstraints(0,0,10,10,50,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.5));
		actionPanel.add(depositButton, setConstraints(1,1,10,10,10,10,GridBagConstraints.WEST,GridBagConstraints.NONE, 0.0));
		actionPanel.add(withdrawalButton, setConstraints(2,1,10,10,10,10,GridBagConstraints.WEST,GridBagConstraints.NONE, 0.0));
		actionPanel.add(exitButton, setConstraints(0,1,10,10,10,10,GridBagConstraints.WEST,GridBagConstraints.NONE, 0.0));

		// Layout for the main JFrame
		this.setLayout(new GridBagLayout());
		this.add(accountDetailPanel, setConstraints(0,0,10,10,10,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.5));
		this.add(overridePanel, setConstraints(1,0,10,10,10,10,GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL, 0.5));
		this.add(textEntryPanel, setConstraints(0,1,10,10,10,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.5));
		this.add(actionPanel, setConstraints(0,2,10,10,10,10,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, 0.5));
		
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
				this.notificationLabel.setText("Deposited $" + String.format("%.2f", amount));
			    this.notificationTimer.start();
				this.depositField.setValue(null);
			} catch(Exception e) {
				JOptionPane optionPane = new JOptionPane(e.getMessage(), JOptionPane.WARNING_MESSAGE);
				JDialog dialog = optionPane.createDialog("Warning");
				dialog.setVisible(true);
			}		
			// Updated the account balance display field
			this.accountBalanceDisplayLabel.setText(String.format("$%.2f",  account.getBalance()));
		} else if (buttonEvent.getSource() == withdrawalButton) {
			Double amount = ((Number) withdrawalField.getValue()).doubleValue();
			try {
				// Withdraw the requested amount
				this.account.withdrawal(amount);
				this.notificationLabel.setText("Withdrew $" + String.format("%.2f", amount));
			    this.notificationTimer.start();
				this.withdrawalField.setValue(null);
			} catch(Exception e) {
				JOptionPane optionPane = new JOptionPane(e.getMessage(), JOptionPane.WARNING_MESSAGE);
			    JDialog dialog = optionPane.createDialog("Warning");
			    dialog.setVisible(true);
			}
			// Update the account balance display field
			this.accountBalanceDisplayLabel.setText(String.format("$%.2f",  account.getBalance()));
		} else if (buttonEvent.getSource() == exitButton) {
			// Build the string to display on the exit confirmation
			exitStringOut.printf("Final Balance: $%.2f\n\n", this.account.getBalance());
			exitStringOut.printf("Are you sure you'd like to exit?");
			// Use a confirmation dialog to display the current balance and confirm exit
			int toExit = JOptionPane.showConfirmDialog(this, exitStringStream.toString(), "Exit Confirmation", JOptionPane.YES_NO_OPTION);
			exitStringOut.close();
			if (toExit == JOptionPane.YES_OPTION) {
				this.dispose();
				System.exit(0);
			}
		} else if (buttonEvent.getSource() == manualBalanceButton) {
			Double amount = ((Number) accountBalanceField.getValue()).doubleValue();
			try {
				// Sets a new balance (erases the existing)
				this.account.setBalance(amount);
				this.notificationLabel.setText("Admin changed balance to $" + String.format("%.2f",  amount));
				this.notificationTimer.start();
				this.accountBalanceField.setValue(null);
			} catch(Exception e) {
				JOptionPane optionPane = new JOptionPane(e.getMessage(), JOptionPane.WARNING_MESSAGE);
				JDialog dialog = optionPane.createDialog("Warning");
				dialog.setVisible(true);
			}
			this.accountBalanceDisplayLabel.setText(String.format("$%.2f",  account.getBalance()));
		}
	}
		
	/**
	 * Utility method for setting grid bag constraints
	 * 
	 * @param x Sets gridx
	 * @param y Sets gridy
	 * @param t Sets top inset
	 * @param l Sets left inset
	 * @param b Sets bottom inset
	 * @param r Sets right inset
	 * @param j Sets anchor value
	 * @param f Sets fill
	 * @param wx Sets weight for x
	 * @return GridBagConstraints 
	 */
	private GridBagConstraints setConstraints(int x, int y, int t, int l, int b, int r, int j, int f, Double wx) {
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		layoutConstraints.gridx = x;
		layoutConstraints.gridy = y;
		layoutConstraints.insets = new Insets(t,l,b,r);
		layoutConstraints.anchor = j;
		layoutConstraints.fill = f;
		layoutConstraints.weightx = wx;
		
		return layoutConstraints;
	}
}

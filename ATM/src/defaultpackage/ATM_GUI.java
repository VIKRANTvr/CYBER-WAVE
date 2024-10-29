package defaultpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_GUI extends JFrame {
	private JTextField customerNumberField, pinField;
	private JLabel messageLabel;
	private JButton loginButton;

	Menu atmMenu = new Menu();

	public ATM_GUI() {
		setTitle("ATM Management System");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));

		JLabel customerNumberLabel = new JLabel("Customer Number:");
		customerNumberField = new JTextField(15);
		JLabel pinLabel = new JLabel("PIN:");
		pinField = new JPasswordField(15);
		loginButton = new JButton("Login");
		messageLabel = new JLabel();

		loginButton.addActionListener(new LoginAction());

		panel.add(customerNumberLabel);
		panel.add(customerNumberField);
		panel.add(pinLabel);
		panel.add(pinField);
		panel.add(new JLabel(""));
		panel.add(loginButton);
		panel.add(messageLabel);

		add(panel);
		setVisible(true);
	}

	private class LoginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int customerNumber;
			int pin;

			try {
				customerNumber = Integer.parseInt(customerNumberField.getText());
				pin = Integer.parseInt(pinField.getText());

				atmMenu.setCustomerNumber(customerNumber);
				atmMenu.setPinNumber(pin);

				String loginResult = atmMenu.validateLogin();
				messageLabel.setText(loginResult);

				if (loginResult.equals("Login successful")) {
					showAccountOptions();
				}
			} catch (NumberFormatException ex) {
				messageLabel.setText("Please enter valid numbers for Customer Number and PIN.");
			}
		}
	}

	private void showAccountOptions() {
		JFrame accountFrame = new JFrame("Account Options");
		accountFrame.setSize(400, 300);
		accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel accountPanel = new JPanel();
		accountPanel.setLayout(new GridLayout(2, 1));

		JButton checkAccountButton = new JButton("Checking Account");
		JButton savingAccountButton = new JButton("Saving Account");

		checkAccountButton.addActionListener(e -> showAccountActions("Checking"));
		savingAccountButton.addActionListener(e -> showAccountActions("Saving"));

		accountPanel.add(checkAccountButton);
		accountPanel.add(savingAccountButton);

		accountFrame.add(accountPanel);
		accountFrame.setVisible(true);
	}

	private void showAccountActions(String accountType) {
		JFrame actionFrame = new JFrame(accountType + " Account Options");
		actionFrame.setSize(400, 300);
		actionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new GridLayout(3, 1));

		JButton checkBalanceButton = new JButton("Check Balance");
		JButton withdrawButton = new JButton("Withdraw");
		JButton depositButton = new JButton("Deposit");

		checkBalanceButton.addActionListener(e -> showBalanceOptions(accountType));
		withdrawButton.addActionListener(e -> handleWithdrawal(accountType));
		depositButton.addActionListener(e -> handleDeposit(accountType));

		actionPanel.add(checkBalanceButton);
		actionPanel.add(withdrawButton);
		actionPanel.add(depositButton);

		actionFrame.add(actionPanel);
		actionFrame.setVisible(true);
	}

	private void showBalanceOptions(String accountType) {
		double balance = accountType.equals("Checking") ? atmMenu.getCheckingBalance() : atmMenu.getSavingBalance();
		showMessage(accountType + " Balance: " + atmMenu.moneyFormat.format(balance));
	}

	private void handleWithdrawal(String accountType) {
		String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw from " + accountType + ":");
		if (input != null && !input.isEmpty()) {
			try {
				double amount = Double.parseDouble(input);
				String result = accountType.equals("Checking") ? atmMenu.getCheckingWithdrawalInput(amount)
						: atmMenu.getSavingWithdrawInput(amount);
				showMessage(result);
			} catch (NumberFormatException ex) {
				showMessage("Invalid amount entered.");
			}
		}
	}

	private void handleDeposit(String accountType) {
		String input = JOptionPane.showInputDialog(this, "Enter amount to deposit into " + accountType + ":");
		if (input != null && !input.isEmpty()) {
			try {
				double amount = Double.parseDouble(input);
				String result = accountType.equals("Checking") ? atmMenu.getCheckingDepositInput(amount)
						: atmMenu.getSavingDepositInput(amount);
				showMessage(result);
			} catch (NumberFormatException ex) {
				showMessage("Invalid amount entered.");
			}
		}
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public static void main(String[] args) {
		new ATM_GUI();
	}
}

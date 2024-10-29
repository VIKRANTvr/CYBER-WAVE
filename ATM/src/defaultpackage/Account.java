package defaultpackage;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {

	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

	private int customerNumber;
	private int pinNumber;
	private double checkingBalance = 0;
	private double savingBalance = 0;

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public double getSavingBalance() {
		return savingBalance;
	}

	public double calcCheckingWithdrawal(double amount) {
		checkingBalance -= amount;
		return checkingBalance;
	}

	public double calcSavingWithdrawal(double amount) {
		savingBalance -= amount;
		return savingBalance;
	}

	public double calcCheckingDeposit(double amount) {
		checkingBalance += amount;
		return checkingBalance;
	}

	public double calcSavingDeposit(double amount) {
		savingBalance += amount;
		return savingBalance;
	}

	public String getCheckingWithdrawalInput(double amount) {
		if (amount <= checkingBalance) {
			calcCheckingWithdrawal(amount);
			return "New checking account balance: " + moneyFormat.format(checkingBalance);
		} else {
			return "Insufficient funds! Balance cannot be negative.";
		}
	}

	public String getSavingWithdrawInput(double amount) {
		if (amount <= savingBalance) {
			calcSavingWithdrawal(amount);
			return "New saving account balance: " + moneyFormat.format(savingBalance);
		} else {
			return "Insufficient funds! Balance cannot be negative.";
		}
	}

	public String getCheckingDepositInput(double amount) {
		calcCheckingDeposit(amount);
		return "New checking account balance: " + moneyFormat.format(checkingBalance);
	}

	public String getSavingDepositInput(double amount) {
		calcSavingDeposit(amount);
		return "New saving account balance: " + moneyFormat.format(savingBalance);
	}
}

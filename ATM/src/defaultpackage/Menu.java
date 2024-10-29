package defaultpackage;

import java.util.HashMap;
import java.util.Map.Entry;

public class Menu extends Account {
	private HashMap<Integer, Integer> data = new HashMap<>();

	public Menu() {
		data.put(123456, 123);
		data.put(555555, 555);
	}

	public String validateLogin() {
		for (Entry<Integer, Integer> entry : data.entrySet()) {
			if (entry.getKey().equals(getCustomerNumber()) && entry.getValue().equals(getPinNumber())) {
				return "Login successful";
			}
		}
		return "Invalid customer number or PIN";
	}

	public String getCheckingWithdrawalInput(double amount) {
		if (amount > getCheckingBalance()) {
			return "Insufficient funds! Balance cannot be negative.";
		} else {
			calcCheckingWithdrawal(amount);
			return "New Checking Account balance: " + moneyFormat.format(getCheckingBalance());
		}
	}

	public String getSavingWithdrawInput(double amount) {
		if (amount > getSavingBalance()) {
			return "Insufficient funds! Balance cannot be negative.";
		} else {
			calcSavingWithdrawal(amount);
			return "New Saving Account balance: " + moneyFormat.format(getSavingBalance());
		}
	}

	public String getCheckingDepositInput(double amount) {
		calcCheckingDeposit(amount);
		return "New Checking Account balance: " + moneyFormat.format(getCheckingBalance());
	}

	public String getSavingDepositInput(double amount) {
		calcSavingDeposit(amount);
		return "New Saving Account balance: " + moneyFormat.format(getSavingBalance());
	}
}

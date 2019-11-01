package com.priceminister.account.implementation;

import com.priceminister.account.*;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalAmountException;


public class CustomerAccount implements Account {

	private Double balance;
	
	/**
	 * Default constructor
	 */
	public CustomerAccount() {	
		this.balance = 0D; 
	}
	
	/**
	 * Overloaded constructor
	 * @param balance
	 */
	public CustomerAccount(Double balance) {
		this.balance = balance;
	}

    public void add(Double addedAmount) throws IllegalAmountException {
		if(addedAmount < 0 ) throw new IllegalAmountException();
		balance += addedAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException, IllegalAmountException {
    	if (withdrawnAmount <0) throw new IllegalAmountException();
    	Double expectedBalance = balance - withdrawnAmount;
    	if (!rule.withdrawPermitted(expectedBalance)) throw new IllegalBalanceException(expectedBalance);
    	return expectedBalance;
    }

}

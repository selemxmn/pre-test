package com.priceminister.account.exceptions;

public class IllegalAmountException extends Exception {

	private static final long serialVersionUID = -2519673017303539602L;
	
	public String toString() {
		return "Cannot add an illegal amount to the balance";
	 }
	
}
